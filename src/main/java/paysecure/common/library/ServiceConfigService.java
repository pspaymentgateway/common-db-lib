package paysecure.common.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfigCompareDTO;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfigUpdates;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfigVersion;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfiguration;
import paysecure.common.db.mysql.repository.configSystemRepository.ServiceConfigRepository;
import paysecure.common.db.mysql.repository.configSystemRepository.ServiceConfigUpdatesRepository;
import paysecure.common.db.mysql.repository.configSystemRepository.ServiceConfigVersionRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import paysecure.common.library.RedisUtils;


@Service
public class ServiceConfigService {
    @Autowired
    private ServiceConfigUpdatesRepository stagingDao;
    @Autowired
    private ServiceConfigRepository configDao;
    @Autowired
    private ServiceConfigVersionRepository versionDao;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String REDIS_CONFIG_PREFIX = "CONFIG:";

    @Autowired
    private RedisUtils redisUtils;

    @Transactional
    public ServiceConfiguration createServiceConfig(String name, String description, String value, String createdBy) {
        ServiceConfiguration config = new ServiceConfiguration();
        config.setId(generateUnique8DigitId());
        config.setName(name);
        config.setDescription(description);
        config.setValue(value);
        config.setStatus("PENDING");
        config.setCreatedBy(createdBy);
        config.setUpdatedAt(LocalDateTime.now());
        config.setCreatedAt(LocalDateTime.now());
        config.setVersion(0);
        configDao.insert(config);
        ServiceConfigVersion version = new ServiceConfigVersion();
        version.setConfigId(config.getId());
        version.setValue(value);
        version.setVersion(0);
        version.setCreatedAt(LocalDateTime.now());
        version.setUpdatedAt(LocalDateTime.now());
        version.setStatus("PENDING");
        versionDao.insert(version);
        return config;
    }
    private Long generateUnique8DigitId() {
        Long id;
        do {
            id = ServiceConfiguration.generate8DigitId();
        } while (configDao.findById(id).isPresent());
        return id;
    }
    public Optional<ServiceConfiguration> getServiceConfigById(Long id) {
        return configDao.findById(id);
    }
    public Object getServiceConfigValue(String name) {
        String redisKey = REDIS_CONFIG_PREFIX + name;
        String cachedValue = redisUtils.get(redisKey, String.class);
        if (cachedValue != null) {
            try {
                return objectMapper.readValue(cachedValue, Object.class);
            } catch (JsonProcessingException e) {}
        }
        Optional<ServiceConfiguration> config = configDao.findByName(name);
        if (config.isPresent() && "APPROVED".equals(config.get().getStatus())) {
            String value = config.get().getValue();
            redisUtils.set(redisKey, value, Duration.ofMinutes(60));
            try {
                return objectMapper.readValue(value, Object.class);
            } catch (JsonProcessingException e) {
                return value;
            }
        }
        return null;
    }
    public List<ServiceConfiguration> listConfigs(String name, String status) {
        if (name != null && status != null) {
            return configDao.findByNameContainingAndStatus(name, status);
        } else if (name != null) {
            return configDao.findByNameContaining(name);
        } else if (status != null) {
            return configDao.findByStatus(status);
        } else {
            return configDao.findAll();
        }
    }

    public List<ServiceConfigUpdates> listConfigUpdates(String name) {
            if (name != null) {
                return stagingDao.findByNameContaining(name);
            } else {
                return stagingDao.findAll();
            }
        }

    public List<ServiceConfigVersion> listConfigVersions(String name) {
        if (name == null || name.isEmpty()) {
            return versionDao.findAll();  // You must implement this
        }

        Optional<ServiceConfiguration> configOpt = configDao.findByName(name);
        if (!configOpt.isPresent()) {
            return Collections.emptyList();
        }
        Long configId = configOpt.get().getId();
        return versionDao.findByConfigIdOrderByVersionDesc(configId);
    }


    @Transactional
    public ServiceConfigUpdates updateServiceConfigValue(Long id, String description, String value, String updatedBy) {
        Optional<ServiceConfiguration> configOpt = configDao.findById(id);
        if (!configOpt.isPresent()) {
            throw new RuntimeException("Configuration not found with id: " + id);
        }

        ServiceConfiguration existing = configOpt.get();
        int latestVersion = versionDao.findMaxVersionForUpdate(id);
        Optional<ServiceConfigVersion> latestVersionEntry = versionDao.findByConfigIdAndVersion(id, latestVersion);

        int versionToUse;
        if (latestVersionEntry.isPresent() && "APPROVED".equalsIgnoreCase(latestVersionEntry.get().getStatus())) {
            versionToUse = latestVersion + 1; // new version
        } else {
            versionToUse = latestVersion; // reuse version
        }

        ServiceConfigUpdates staging = stagingDao.findByConfigId(id)
                .orElse(new ServiceConfigUpdates());

        staging.setConfigId(id);
        staging.setName(existing.getName());
        staging.setDescription(description != null ? description : existing.getDescription());
        staging.setValue(value != null ? value : existing.getValue());
        staging.setStatus("PENDING");
        staging.setUpdatedAt(LocalDateTime.now());
        staging.setUpdatedBy(updatedBy);
        staging.setVersion(versionToUse);

        if (staging.getId() == null) {
            stagingDao.insert(staging);
        } else {
            stagingDao.update(staging);
        }

        Optional<ServiceConfigVersion> existingVersionOpt =
                versionDao.findByConfigIdAndVersion(id, versionToUse);

        if (!existingVersionOpt.isPresent()) {
            ServiceConfigVersion version = new ServiceConfigVersion();
            version.setConfigId(id);
            version.setValue(staging.getValue());
            version.setVersion(versionToUse);
            version.setCreatedAt(LocalDateTime.now());
            version.setUpdatedAt(LocalDateTime.now());
            version.setStatus("PENDING");
            version.setUpdatedBy(updatedBy);
            versionDao.insert(version);
        } else {
            ServiceConfigVersion version = existingVersionOpt.get();
            if (!"APPROVED".equalsIgnoreCase(version.getStatus())) {
                version.setValue(staging.getValue());
                version.setUpdatedBy(updatedBy);
                version.setUpdatedAt(LocalDateTime.now());
                versionDao.update(version);
            }
        }

        invalidateServiceConfigCache(existing.getName());
        return staging;
    }

    public Optional<ServiceConfiguration> getServiceConfigByName(String name) {
        return configDao.findByName(name);
    }

    @Transactional
        public void deleteServiceConfig(Long id) {
            Optional<ServiceConfiguration> configOpt = configDao.findById(id);
            if (configOpt.isPresent()) {
                ServiceConfiguration config = configOpt.get();
                redisUtils.delete(REDIS_CONFIG_PREFIX + config.getName());
                versionDao.deleteByConfigId(id);
                configDao.deleteById(id);
            } else {
                throw new RuntimeException("Configuration not found with id: " + id);
            }
        }
    public void invalidateServiceConfigCache(String name) {
        redisUtils.delete(REDIS_CONFIG_PREFIX + name);
    }
    public ServiceConfigCompareDTO compareServiceConfigVersionsByName(String name, Integer version1, Integer version2) {

        if (version1 == null || version2 == null) {
            throw new IllegalArgumentException("Both version1 and version2 must be provided.");
        }

        if (version1 >= version2) {
            throw new IllegalArgumentException("version1 must be less than version2.");
        }

        Optional<ServiceConfiguration> configOpt = configDao.findByName(name);
        if (!configOpt.isPresent()) {
            throw new RuntimeException("Configuration not found with name: " + name);
        }

        ServiceConfiguration config = configOpt.get();
        Long configId = config.getId();
        String currentValue = config.getValue();
        Integer currentVersion = config.getVersion();

        String v1Value = version1.equals(currentVersion)
                ? currentValue
                : versionDao.findByConfigIdAndVersion(configId, version1)
                .map(ServiceConfigVersion::getValue)
                .orElseThrow(() -> new RuntimeException("Version " + version1 + " not found for config: " + name));

        String v2Value = version2.equals(currentVersion)
                ? currentValue
                : versionDao.findByConfigIdAndVersion(configId, version2)
                .map(ServiceConfigVersion::getValue)
                .orElseThrow(() -> new RuntimeException("Version " + version2 + " not found for config: " + name));

        return new ServiceConfigCompareDTO(configId, name, version1, version2, v1Value, v2Value);
    }

    public List<Map<String, Object>> getConfigHistory(Long configId) {
            List<ServiceConfigVersion> versions = versionDao.findByConfigIdOrderByVersionDesc(configId);
            List<Map<String, Object>> history = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (ServiceConfigVersion version : versions) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("version", version.getVersion());
                entry.put("value", version.getValue());
                entry.put("status", version.getStatus());
                if (version.getCreatedAt() != null) {
                    entry.put("createdAt", version.getCreatedAt().format(formatter));
                }
                entry.put("updatedBy", version.getUpdatedBy());
                history.add(entry);
            }
            return history;
        }
        @Transactional
        public Map<String, Object> createRelease(List<Long> configIds, String userEmail) {
            String releaseId = UUID.randomUUID().toString();
            for (Long configId : configIds) {
                Optional<ServiceConfiguration> configOpt = configDao.findById(configId);
                Optional<ServiceConfigUpdates> stagingOpt = stagingDao.findByConfigId(configId);
                if (configOpt.isPresent()) {
                    ServiceConfiguration main = configOpt.get();
                    if (stagingOpt.isPresent()) {
                        ServiceConfigUpdates staged = stagingOpt.get();
                        main.setDescription(staged.getDescription());
                        main.setValue(staged.getValue());
                        main.setVersion(staged.getVersion());
                        main.setUpdatedAt(LocalDateTime.now());
                        main.setUpdatedBy(staged.getUpdatedBy());
                        stagingDao.deleteByConfigId(configId);
                    }
                    main.setStatus("APPROVED");
                    main.setUpdatedAt(LocalDateTime.now());
                    configDao.update(main);
                    Optional<ServiceConfigVersion> existingVersion =
                            versionDao.findByConfigIdAndVersion(main.getId(), main.getVersion());
                    if (!existingVersion.isPresent()) {
                        ServiceConfigVersion version = new ServiceConfigVersion();
                        version.setConfigId(main.getId());
                        version.setValue(main.getValue());
                        version.setVersion(main.getVersion());
                        version.setCreatedAt(LocalDateTime.now());
                        version.setStatus("APPROVED");
                        version.setUpdatedBy(main.getUpdatedBy());
                        versionDao.insert(version);
                    } else {
                        ServiceConfigVersion version = existingVersion.get();
                        version.setStatus("APPROVED");
                        version.setUpdatedBy(main.getUpdatedBy());
                        version.setUpdatedAt(LocalDateTime.now());
                        versionDao.update(version);
                    }
                    redisUtils.delete(REDIS_CONFIG_PREFIX + main.getName());
                    redisUtils.set(REDIS_CONFIG_PREFIX + main.getName(), main.getValue(), Duration.ofMinutes(60));
                }
            }
            Map<String, Object> release = new HashMap<>();
            release.put("id", releaseId);
            release.put("userEmail", userEmail);
            release.put("releaseId", "r-" + releaseId.substring(0, 8));
            release.put("releaseStatus", "COMPLETED");
            release.put("dateCreated", LocalDateTime.now());
            release.put("configIds", configIds);
            return release;
        }
        public ServiceConfiguration rollbackToPreviousVersion(Long configId) {
            Optional<ServiceConfiguration> configOpt = configDao.findById(configId);
            if (!configOpt.isPresent()) {
                throw new RuntimeException("Configuration not found with id: " + configId);
            }
            ServiceConfiguration currentConfig = configOpt.get();
            int currentVersion = currentConfig.getVersion();
            if (currentVersion <= 0) {
                return null;
            }
            int previousVersion = currentVersion - 1;
            Optional<ServiceConfigVersion> prevVersionOpt =
                    versionDao.findByConfigIdAndVersion(configId, previousVersion);
            if (!prevVersionOpt.isPresent()) {
                return null;
            }
            ServiceConfigVersion previous = prevVersionOpt.get();
            currentConfig.setValue(previous.getValue());
            currentConfig.setVersion(previous.getVersion());
            currentConfig.setStatus(previous.getStatus());
            currentConfig.setUpdatedAt(LocalDateTime.now());
            currentConfig.setUpdatedBy("rollback");
            configDao.update(currentConfig);
            return currentConfig;
        }
    }