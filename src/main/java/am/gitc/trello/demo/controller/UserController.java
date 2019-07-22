package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.dto.UserDto;
import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.mail.service.EmailService;
import am.gitc.trello.demo.mapper.UserMapper;
import am.gitc.trello.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, EmailService emailService, UserMapper userMapper) {
        this.userService = userService;
        this.emailService = emailService;
        this.userMapper = userMapper;
    }


    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody UserDto userDto) {
        userDto.setInitial();
        UserEntity entity = this.userMapper.toEntity(userDto);
        if (!this.userService.isExist(entity.getEmail())) {
            entity.setActivationCode(UUID.randomUUID().toString());
            this.userService.register(entity);
            try {
                this.emailService.validateEmail(entity);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            return ResponseEntity.ok("User with email: " + userDto.getEmail() + " is already exist");
        }
        return ResponseEntity.ok("An activation link has been sent to your mail. Please confirm by clicking on this link.");
    }



    @GetMapping("/activate/{activation_code}")
    public ResponseEntity activateAccount(@PathVariable("activation_code") String activationCode) {
        boolean active = this.userService.hasActiveCode(activationCode);
        if (!active) {
            return ResponseEntity.ok("Activation code not found");
        }
        return ResponseEntity.ok("User successfully registered");
    }


    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) {
        Optional<UserEntity> userEntity = this.userService.getUser(userId);
        if (!userEntity.isPresent()) {
            logger.warn("Users with id = {} not found.", userId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.userMapper.toDto(userEntity.get()));
    }


    @GetMapping("/api/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Fetching all users.");
        return ResponseEntity.ok(this.userMapper.toDtoList(this.userService.getAll()));
    }


    @GetMapping("/api/users/_search")
    public ResponseEntity<UserDto> getUserByEmailAndPassword(@PathParam("email") String email,
                                                             @PathParam("password") String password) {
        try {
            Optional<UserEntity> userEntity = this.userService.getUser(email, password);
            if (!userEntity.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(this.userMapper.toDto(userEntity.get()));
        } catch (Exception e) {
            logger.error("ERROR", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @DeleteMapping("/api/users/{user_id}")
    public ResponseEntity deleteUserById(@PathVariable("user_id") Integer userId) {
        this.userService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
