package paysecure.common.db.redis;

import java.time.Duration;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CacheableQueryService {

    private final JdbcTemplate jdbcTemplate;
    private final RedisUtils redisUtils;

    // Injected from microservice
    public CacheableQueryService(JdbcTemplate jdbcTemplate, RedisUtils redisUtils) {
        this.jdbcTemplate = jdbcTemplate;
        this.redisUtils = redisUtils;
    }

// ---------------------------------------------------------------------------------------

    // @Configuration
    // public class AppConfig {

    //     @Bean
    //     public RedisUtils redisUtils(@Qualifier("customRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
    //         return new RedisUtils(redisTemplate);
    //     }

    //     @Bean
    //     public CacheableQueryService cacheableQueryService(
    //             @Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate,
    //             RedisUtils redisUtils
    //     ) {
    //         return new CacheableQueryService(jdbcTemplate, redisUtils);
    //     }
    // }

// ---------------------------------------------------------------------------------------------

    /**
     * For queries returning multiple rows.
     */
    public <T> List<T> findAllWithCache(
            String cacheKey,
            String sqlQuery,
            Object[] params,
            RowMapper<T> rowMapper,
            Duration ttl,
            Class<T> type
    ) {
        // Try Redis
        List<T> cachedResult = redisUtils.getAll(cacheKey, type);
        if (cachedResult != null) {
            return cachedResult;
        }

        // Query DB
        List<T> dbResult = jdbcTemplate.query(sqlQuery, rowMapper, params);

        // Cache result
        if (cacheKey != null && dbResult != null) {
            redisUtils.set(cacheKey, dbResult, ttl);
        }

        return dbResult;
    }

    /**
     * For queries returning exactly one row (or null).
     */
    public <T> T findOneWithCache(
            String cacheKey,
            String sqlQuery,
            Object[] params,
            RowMapper<T> rowMapper,
            Duration ttl,
            Class<T> type
    ) {
        // Try Redis
        T cachedResult = redisUtils.get(cacheKey, type);
        if (cachedResult != null) {
            return cachedResult;
        }

        try {
            T dbResult = jdbcTemplate.queryForObject(sqlQuery, rowMapper, params);

            if (cacheKey != null && dbResult != null) {
                redisUtils.set(cacheKey, dbResult, ttl);
            }

            return dbResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // record not found
        }
    }

    public Boolean updateOne(String cacheKey, String sqlQuery, Object[] params) {
        try {
            boolean success = jdbcTemplate.update(sqlQuery, params) > 0;

            if (success && cacheKey != null) {
                redisUtils.delete(cacheKey);
            }

            return success;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteRedisKey(String cacheKey) {
        if (cacheKey != null) {
            redisUtils.delete(cacheKey);
        }
    }
}
