package am.gitc.trello.demo.service.redis;

import am.gitc.trello.demo.exception.RedisException;
import am.gitc.trello.demo.util.JsonUtil;
import am.gitc.trello.demo.util.Validator;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RedisImpl<T> implements Redis<T> {

    private final JedisPool jedisPool;


    @Autowired
    public RedisImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void add(String key, T value, int titleSeconds) {
        try(Jedis redis = jedisPool.getResource()){
            String entityJson = JsonUtil.serialize(value);
            redis.set(key,entityJson);
            redis.expire(key,titleSeconds);
        }catch (Exception e) {
            log.error("ERROR", e.getMessage());
            throw new RedisException(e);
        }
    }

    @Override
    public Optional<T> getKey(String key){
        try(Jedis redis = jedisPool.getResource()){
            String entityJson = redis.get(key);
            if (!Validator.isEmpty(entityJson)){
                return Optional.of(JsonUtil.deserialize(entityJson, new TypeReference<T>() {
                }));
            }
        }catch (Exception e){
            log.error("ERROR", e.getMessage());
            throw new RedisException(e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteKey(String key) {
        try (Jedis redis = jedisPool.getResource()) {
            String entityJson = redis.get(key);
            if (!Validator.isEmpty(entityJson)){
                redis.del(key);
            }
        }catch (Exception e){
            log.error("ERROR", e.getMessage());
            throw new RedisException(e);
        }
    }

    @Override
    public List<T> getAll(String key){
        try(Jedis jedis = jedisPool.getResource()) {
            String entityJson = jedis.get(key);
            if (!Validator.isEmpty(entityJson)){
                return JsonUtil.deserialize(entityJson, new TypeReference<List<T>>() {
                });
            }
        }catch (Exception e){
            log.error("ERROR", e.getMessage());
            throw new RedisException(e);
        }
        return null;
    }

    @Override
    public void addAll(String key, List<T> entities, int titleSeconds) {
        try(Jedis redis = jedisPool.getResource()){
            String entityJson = JsonUtil.serialize(entities);
            redis.set(key,entityJson);
            redis.expire(key,titleSeconds);
        } catch (Exception e) {
            log.error("ERROR", e.getMessage());
            throw new RedisException(e);
        }
    }

    @Override
    public void deleteAll(String key) {
        try (Jedis redis = jedisPool.getResource()) {
                redis.del(key);
        }catch (Exception e){
            log.error("ERROR", e.getMessage());
            throw new RedisException(e);
        }
    }
}
