package am.gitc.trello.demo.service.redis;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.util.JsonUtil;
import am.gitc.trello.demo.util.Validator;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

@Repository
public class UserRedis {
    private static Logger logger = LoggerFactory.getLogger(UserRedis.class);
    private final JedisPool jedisPool;

    @Autowired
    public UserRedis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void addUser(String key, UserEntity value, int ttlSeconds) {
        try (Jedis redis = jedisPool.getResource()) {
            String jsonUserValue = JsonUtil.serialize(value);
            redis.set(key, jsonUserValue);
            redis.expire(key, ttlSeconds);
        } catch (Exception ex) {
            logger.error("Failed to add user", ex);
        }
    }

    public Optional<UserEntity> getUser(String key) {
        try (Jedis redis = jedisPool.getResource()) {
            String userInfoJson = redis.get(key);
            if (!Validator.isEmpty(userInfoJson)) {
                return Optional.of(JsonUtil.deserialize(userInfoJson, new TypeReference<UserEntity>() {
                }));
            }
        } catch (Exception ex) {
            logger.error("Failed to get user", ex);
        }
        return Optional.empty();
    }

    public void delete(String key, int ttlSeconds) {
        try (Jedis redis = jedisPool.getResource()) {
            redis.del(key);
            redis.expire(key, ttlSeconds);
        } catch (Exception ex) {
            logger.error("Failed to delete user", ex);
        }
    }
}
