package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.repository.UserRepository;
import am.gitc.trello.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by User on 20.07.2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void register(UserEntity user) {
        this.userRepository.save(user);
    }

    @Override
    public void login(String email, String password) {

    }

    @Override
    public boolean isExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean isExist(int id) {
        return this.userRepository.existsById(id);

    }

    @Override
    public void delete(int id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> getUser(int id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUser(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<UserEntity> getAll() {
        return this.userRepository.findAll();

    }


    @Override
    public boolean hasActiveCode(String activeCode) {
        UserEntity entity = this.userRepository.findByActivationCode(activeCode);
        if (entity != null) {
            entity.setActivationCode(null);
            this.userRepository.save(entity);
            return true;
        }
        return false;
    }
}
