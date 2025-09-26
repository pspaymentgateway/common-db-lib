


package paysecure.common.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import paysecure.common.library.CacheableQueryService;
import paysecure.common.library.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class AppConfiguration {

    // @Bean
    // public RedisUtils redisUtils(@Qualifier("customRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
    //     return new RedisUtils(redisTemplate);
    // }

    // For single Redis - no headache to give Qualifire name
    @Bean
    public RedisUtils redisUtils(RedisTemplate<String, Object> redisTemplate) {
        return new RedisUtils(redisTemplate);
    }

    // Cacheable service for primary DB
    @Bean(name = "primaryCacheService")
    public CacheableQueryService primaryCacheableQueryService(
            @Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate,
            RedisUtils redisUtils
    ) {
        return new CacheableQueryService(jdbcTemplate, redisUtils);
    }

    // Cacheable service for secondary DB
    @Bean(name = "dashboardCacheService")
    public CacheableQueryService secondaryCacheableQueryService(
            @Qualifier("dashbrdJdbcTemplate") JdbcTemplate jdbcTemplate,
            RedisUtils redisUtils
    ) {
        return new CacheableQueryService(jdbcTemplate, redisUtils);
    }
}

// HOW TO USE

// @Service
// public class MyService {

//     private final CacheableQueryService primaryCache;
//     private final CacheableQueryService secondaryCache;

//     public MyService(
//         @Qualifier("primaryCacheService") CacheableQueryService primaryCache,
//         @Qualifier("secondaryCacheService") CacheableQueryService secondaryCache
//     ) {
//         this.primaryCache = primaryCache;
//         this.secondaryCache = secondaryCache;
//     }

//     public void doSomething() {
//         // Query from primary DB
//         var users = primaryCache.findAllWithCache("users_key", "SELECT * FROM users", null, new UserRowMapper(), Duration.ofMinutes(10), User.class);

//         // Query from secondary DB
//         var orders = secondaryCache.findAllWithCache("orders_key", "SELECT * FROM orders", null, new OrderRowMapper(), Duration.ofMinutes(10), Order.class);
//     }
// }

