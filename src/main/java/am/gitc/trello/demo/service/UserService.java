package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by User on 20.07.2019.
 */
public interface UserService {

    boolean isExist(String email);

    void register(UserEntity entity);

    boolean hasActiveCode(String activeCode);

    Optional<UserEntity> login(String email, String password);

    void delete(int id);

    Optional<UserEntity> getUser(int id);

    List<UserEntity> getAll();

}
