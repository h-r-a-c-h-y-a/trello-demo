package am.gitc.trello.demo.service.redis;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Redis<T> {


    void add(String key, T value, int titleSeconds);


    Optional<T> getKey(String key) throws IOException;

    void deleteKey(String key);

    List<T> getAll(String key) throws IOException;

    void addAll(String key, List<T> entities, int titleSeconds);

    void deleteAll(String key);
}
