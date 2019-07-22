package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by User on 20.07.2019.
 */
public interface UserService {

    boolean isExist(String email);

    boolean isExist(int id);

    void register(UserEntity entity);

    boolean hasActiveCode(String activeCode);

    void login(String email, String password);

    void delete(int id);

    Optional<UserEntity> getUser(int id);

    Optional<UserEntity> getUser(String email, String password);

    List<UserEntity> getAll();

}
