package paysecure.common.db.mysql.repository.configSystemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfigUpdates;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ServiceConfigUpdatesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<ServiceConfigUpdates> findAll() {
        return jdbcTemplate.query("SELECT * FROM service_config_updates",
                new BeanPropertyRowMapper<>(ServiceConfigUpdates.class));
    }

    public List<ServiceConfigUpdates> findByNameContaining(String namePart) {
        String sql = "SELECT * FROM service_config_updates WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfigUpdates.class), "%" + namePart + "%");
    }

    public Optional<ServiceConfigUpdates> findByConfigId(Long configId) {
        String sql = "SELECT * FROM service_config_updates WHERE config_id = ?";
        List<ServiceConfigUpdates> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfigUpdates.class), configId);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<ServiceConfigUpdates> findByConfigIdIn(List<Long> configIds) {
        if (configIds == null || configIds.isEmpty()) return Collections.emptyList();
        String inSql = String.join(",", configIds.stream().map(id -> "?").collect(Collectors.toList()));
        String sql = "SELECT * FROM service_config_updates WHERE config_id IN (" + inSql + ")";
        return jdbcTemplate.query(sql, configIds.toArray(), new BeanPropertyRowMapper<>(ServiceConfigUpdates.class));
    }


    public int insert(ServiceConfigUpdates update) {
        String sql = "INSERT INTO service_config_updates " +
                "(config_id, name, description, value, status, updated_at, updated_by, version) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                update.getConfigId(),
                update.getName(),
                update.getDescription(),
                update.getValue(),
                update.getStatus(),
                update.getUpdatedAt(),
                update.getUpdatedBy(),
                update.getVersion());
    }
    public int update(ServiceConfigUpdates update) {
        String sql = "UPDATE service_config_updates SET " +
                "name = ?, description = ?, value = ?, status = ?, updated_at = ?, updated_by = ?, version = ? " +
                "WHERE config_id = ?";
        return jdbcTemplate.update(sql,
                update.getName(),
                update.getDescription(),
                update.getValue(),
                update.getStatus(),
                update.getUpdatedAt(),
                update.getUpdatedBy(),
                update.getVersion(),
                update.getConfigId());
    }
    public int deleteByConfigId(Long configId) {
        return jdbcTemplate.update("DELETE FROM service_config_updates WHERE config_id = ?", configId);
    }
}