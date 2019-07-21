package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.UserEntity;

/**
 * Created by User on 20.07.2019.
 */
public interface UserService {

    boolean isExist(String email);

    void register(UserEntity entity);

    boolean hasActiveCode(String activeCode);
}
