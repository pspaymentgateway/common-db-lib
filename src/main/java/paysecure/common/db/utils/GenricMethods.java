package paysecure.common.db.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.configSystemService.ServiceConfigService;
import paysecure.common.db.mysql.model.configSystemModels.EntityRollout;
import paysecure.common.db.mysql.model.configSystemModels.RolloutConfig;
import paysecure.common.db.redis.RedisUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GenricMethods {

    private static final Logger logger = LoggerFactory.getLogger(GenricMethods.class);
    private static final String REDIS_PREFIX = "CONFIG:";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ServiceConfigService serviceConfigService;

    public String getFromRedisOrDb(String configName) {
        String redisKey = REDIS_PREFIX + configName;
        String cachedValue = redisUtils.get(redisKey, String.class);
        if (cachedValue != null) {
            return cachedValue;
        }

        try {
            Object configValue = serviceConfigService.getServiceConfigValue(configName);
            if (configValue == null) {
                throw new RuntimeException("Approved configuration not found for: " + configName);
            }
            String json = configValue instanceof String
                    ? (String) configValue
                    : objectMapper.writeValueAsString(configValue);
            redisUtils.set(redisKey, json, Duration.ofMinutes(60));
            return json;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching config from DB: " + configName, e);
        }
    }

    public boolean isFeatureEnabled(String featureName, String entityId) {
        Object config = serviceConfigService.getServiceConfigValue(featureName);

        logger.info("CONFIG_NAME_ROLLOUT: type={} | value={}", (config == null ? "null" : config.getClass().getName()),config);

        if (config == null) {
            logger.info("CONFIG_NOT_FOUND", featureName);
            return false;
        }
        if (entityId == null || entityId.trim().isEmpty()) {
            System.err.println("Entity ID is null or empty for feature: " + featureName);
            return false;
        }
        try {
            RolloutConfig rolloutConfig;
            if (config instanceof String) {
                String jsonString = (String) config;
                rolloutConfig = objectMapper.readValue(jsonString, RolloutConfig.class);
            } else {
                String json = objectMapper.writeValueAsString(config);
                rolloutConfig = objectMapper.readValue(json, RolloutConfig.class);
            }

            return evaluateRollout(featureName, entityId, rolloutConfig);

        } catch (Exception e) {
            logger.info("INVALID_ROLLOUT_CONFIG: featureName={} | errorMsg={}", featureName , e.getMessage());
            return false;
        }
    }

    private boolean evaluateRollout(String featureName, String entityId, RolloutConfig config) {
        String id = entityId.toLowerCase();

        if (config.getDisableAny() != null &&
                config.getDisableAny().stream().map(String::toLowerCase).anyMatch(id::equals)) {
            logger.info("Entity is in disableAny list");
            return false;
        }

        if (Boolean.TRUE.equals(config.getEnableAll())) {
            Integer rollout = config.getEnableAllRollout();
            int bucket = getBucket(featureName + ":" + id);
            logger.info("Global rollout={} | bucket={}", rollout,bucket);
            return rollout == null || bucket < rollout;
        }

        if (config.getEntities() != null) {
            Optional<EntityRollout> match = config.getEntities()
                    .stream()
                    .filter(e -> id.equalsIgnoreCase(e.getId()))
                    .findFirst();
            if (match.isPresent()) {
                int rollout = match.get().getRollout();
                int bucket = getBucket(featureName + ":" + id);
                logger.info("CONFIG_ENTITY: entity={} | rollout={} | bucket={}", id,rollout,bucket);
                return bucket < rollout;
            } else {
                logger.info("Entity not found in config entities");
            }
        }

        return false;
    }

    private int getBucket(String key) {
        int bucket = ThreadLocalRandom.current().nextInt(100);
        return bucket;
    }
}
