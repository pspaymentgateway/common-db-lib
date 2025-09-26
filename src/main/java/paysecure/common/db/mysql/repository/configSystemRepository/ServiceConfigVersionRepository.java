package paysecure.common.db.mysql.repository.configSystemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfigVersion;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceConfigVersionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiceConfigVersion> findAll() {
        String sql = "SELECT * FROM service_config_version ORDER BY config_id, version DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfigVersion.class));
    }

    public int findMaxVersionForUpdate(Long configId) {
        String sql = "SELECT COALESCE(MAX(version), 0) FROM service_config_version WHERE config_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, configId);
    }

    public List<ServiceConfigVersion> findByConfigIdOrderByVersionDesc(Long configId) {
        String sql = "SELECT * FROM service_config_version WHERE config_id = ? ORDER BY version DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfigVersion.class), configId);
    }

    public Optional<ServiceConfigVersion> findByConfigIdAndVersion(Long configId, Integer version) {
        String sql = "SELECT * FROM service_config_version WHERE config_id = ? AND version = ?";
        List<ServiceConfigVersion> results = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(ServiceConfigVersion.class),
                configId, version);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public int update(ServiceConfigVersion version) {
        String sql = "UPDATE service_config_version SET " +
                "value = ?, " +
                "status = ?, " +
                "updated_by = ?, " +
                "updated_at = ? " +
                "WHERE config_id = ? AND version = ?";
        return jdbcTemplate.update(sql,
                version.getValue(),
                version.getStatus(),
                version.getUpdatedBy(),
                version.getUpdatedAt(),
                version.getConfigId(),
                version.getVersion());
    }

    public int insert(ServiceConfigVersion version) {
        String sql = "INSERT INTO service_config_version " +
                "(config_id, version, value, status, created_at, updated_by, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                version.getConfigId(),
                version.getVersion(),
                version.getValue(),
                version.getStatus(),
                version.getCreatedAt(),
                version.getUpdatedBy(),
                version.getUpdatedAt());
    }

    public int deleteByConfigId(Long configId) {
        String sql = "DELETE FROM service_config_version WHERE config_id = ?";
        return jdbcTemplate.update(sql, configId);
    }
}
