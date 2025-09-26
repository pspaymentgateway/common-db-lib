package paysecure.common.db.mysql.repository.configSystemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import paysecure.common.db.mysql.model.configSystemModels.ServiceConfiguration;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceConfigRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiceConfiguration> findAll() {
        return jdbcTemplate.query("SELECT * FROM service_configuration",
                new BeanPropertyRowMapper<>(ServiceConfiguration.class));
    }

    public Optional<ServiceConfiguration> findById(Long id) {
        String sql = "SELECT * FROM service_configuration WHERE id = ?";
        List<ServiceConfiguration> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfiguration.class), id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public Optional<ServiceConfiguration> findByName(String name) {
        String sql = "SELECT * FROM service_configuration WHERE name = ?";
        List<ServiceConfiguration> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfiguration.class), name);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<ServiceConfiguration> findByStatus(String status) {
        String sql = "SELECT * FROM service_configuration WHERE status = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfiguration.class), status);
    }

    public List<ServiceConfiguration> findByNameContaining(String namePart) {
        String sql = "SELECT * FROM service_configuration WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfiguration.class), "%" + namePart + "%");
    }

    public List<ServiceConfiguration> findByNameContainingAndStatus(String namePart, String status) {
        String sql = "SELECT * FROM service_configuration WHERE name LIKE ? AND status = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceConfiguration.class), "%" + namePart + "%", status);
    }

    public int insert(ServiceConfiguration config) {
        String sql = "INSERT INTO service_configuration " +
                "(id, name, description, value, status, updated_at, updated_by, version, created_by, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                config.getId(),
                config.getName(),
                config.getDescription(),
                config.getValue(),
                config.getStatus(),
                config.getUpdatedAt(),
                config.getUpdatedBy(),
                config.getVersion(),
                config.getCreatedBy(),
                config.getCreatedAt());
    }

    public int update(ServiceConfiguration config) {
        String sql = "UPDATE service_configuration SET " +
                "name=?, description=?, value=?, status=?, updated_at=?, updated_by=?, version=? " +
                "WHERE id=?";
        return jdbcTemplate.update(sql,
                config.getName(),
                config.getDescription(),
                config.getValue(),
                config.getStatus(),
                config.getUpdatedAt(),
                config.getUpdatedBy(),
                config.getVersion(),
                config.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM service_configuration WHERE id = ?", id);
    }
}
