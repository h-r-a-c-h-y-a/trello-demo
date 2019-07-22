package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.dto.UserDto;
import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.mail.service.EmailService;
import am.gitc.trello.demo.mapper.UserMapper;
import am.gitc.trello.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final UserMapper userMapper;

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
}
