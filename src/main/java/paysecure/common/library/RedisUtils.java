package paysecure.common.library;

import java.time.Duration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    // RedisTemplate is injected by microservice
    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <T> T get(String key, Class<T> type) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public <T> List<T> getAll(String key, Class<T> type) {
        return (List<T>) redisTemplate.opsForValue().get(key);
    }


    public void set(String key, Object value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
