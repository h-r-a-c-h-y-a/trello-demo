package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.TrelloDemoApplication;
import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.repository.UserRepository;
import am.gitc.trello.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void register(UserEntity user, MultipartFile file) throws IOException {
        String filePath = "images";
        if ("".equals(file.getOriginalFilename())) {
            user.setImageUrl(filePath + file.getOriginalFilename());
        }else {
            user.setImageUrl(filePath + user.getId());
        }
        this.userRepository.save(user);
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
        this.userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> getUser(int id) {
        return this.userRepository.findById(id);
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
