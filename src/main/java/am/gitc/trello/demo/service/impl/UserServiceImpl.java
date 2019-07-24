package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.repository.UserRepository;
import am.gitc.trello.demo.service.UserService;
import am.gitc.trello.demo.service.redis.RedisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 20.07.2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisImpl<UserEntity> redis;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RedisImpl<UserEntity> redis) {
        this.userRepository = userRepository;
        this.redis = redis;
    }


    @Override
    public void register(UserEntity user, MultipartFile file) throws IOException {
        String filePath = "images";
        if ("".equals(file.getOriginalFilename())) {
            user.setImageUrl(filePath + file.getOriginalFilename());
        } else {
            user.setImageUrl(filePath + user.getId());
        }
        this.userRepository.save(user);
        this.redis.deleteAll("allUsers");
        this.redis.add("" + user.getId(), user, 80000);
        file.transferTo(new File(filePath + ("".equals(file.getOriginalFilename()) ? user.getId() : file.getOriginalFilename())));
    }

    @Override
    public Optional<UserEntity> login(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean isExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public void delete(int id) {
        this.redis.deleteKey("" + id);
        this.userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> getUser(int id) {
        Optional<UserEntity> entityOptional = this.redis.getKey("" + id);
        if (!entityOptional.isPresent())
            entityOptional = this.userRepository.findById(id);
        if (entityOptional.isPresent()) {
            this.redis.add("" + id, entityOptional.get(), 80000);
            return entityOptional;
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> entities = this.redis.getAll("allUsers");
        if (entities != null) {
            return entities;
        }
        entities = this.userRepository.findAll();
        this.redis.addAll("allUsers", entities, 80000);
        return entities;
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
