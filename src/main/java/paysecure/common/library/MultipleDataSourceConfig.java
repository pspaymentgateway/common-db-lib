package paysecure.common.library;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class MultipleDataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource primaryDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "primaryJdbcTemplate")
    @Primary
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("loggingPrimaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "dashbrdDataSource")
    @ConfigurationProperties(prefix = "dashboard.datasource.hikari")
    public HikariDataSource dashbrdDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "dashbrdJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("loggingDashbrdDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}