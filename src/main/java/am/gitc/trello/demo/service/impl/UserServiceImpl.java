package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.repository.UserRepository;
import am.gitc.trello.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by User on 20.07.2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean isExist(String email) {
        return this.repo.existsByEmail(email);
    }

    @Override
    public void register(UserEntity entity) {
        this.repo.save(entity);
    }

    @Override
    public boolean hasActiveCode(String activeCode) {
        UserEntity entity = this.repo.findByActivationCode(activeCode);
        if (entity != null) {
            entity.setActivationCode(null);
            this.repo.save(entity);
            return true;
        }
        return false;
    }
}
