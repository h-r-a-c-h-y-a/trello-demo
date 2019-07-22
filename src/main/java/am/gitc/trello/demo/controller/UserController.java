package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.dto.UserDto;
import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.mail.service.EmailService;
import am.gitc.trello.demo.mapper.UserMapper;
import am.gitc.trello.demo.service.UserService;
import am.gitc.trello.demo.validation.annotations.ValidPassword;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
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


    @GetMapping({"/", "/trello/users/login"})
    public ModelAndView goTologinPage(ModelAndView model){
        model.addObject("user", new UserEntity());
        model.setViewName("login");
        return model;
    }

    @GetMapping("/trello/users/register")
    public ModelAndView goToRegPage(ModelAndView model){
        model.addObject("user", new UserEntity());
        model.setViewName("register");
        return model;
    }

    @PostMapping("/trello/users/register")
    public ModelAndView registration(ModelAndView modelAndView, @ModelAttribute("userForm") UserEntity userEntity) {
        userEntity.setInitial(userEntity.getFullName());
        if (!this.userService.isExist(userEntity.getEmail())) {
            userEntity.setActivationCode(UUID.randomUUID().toString());
            this.userService.register(userEntity);
            try {
                this.emailService.validateEmail(userEntity);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            modelAndView.addObject("RegisterFailed", "User with email: " + userEntity.getEmail() + " is already exist");
            modelAndView.setViewName("register");
            return modelAndView;
        }
        modelAndView.setViewName("register");
        modelAndView.addObject("CodeMessage", "An activation link has been sent to your mail. Please confirm by clicking on this link.");
        return modelAndView;
    }


    @GetMapping("/trello/users/activate/{activation_code}")
    public ModelAndView activateAccount(ModelAndView modelAndView,
                                        @PathVariable("activation_code") String activationCode) {
        boolean active = this.userService.hasActiveCode(activationCode);
        if (!active) {
            modelAndView.setViewName("register");
            modelAndView.addObject("ActivationFailed", "Activation code not found");
        }
        modelAndView.setViewName("login");
        modelAndView.addObject("RegisterSuccess", "Successfully registered!");
        return modelAndView;
    }


    @GetMapping("/trello/users/{userId}")
    public ModelAndView getUserById(ModelAndView modelAndView,
                                    @PathVariable("userId") Integer userId) {
        Optional<UserEntity> userEntity = this.userService.getUser(userId);
        if (!userEntity.isPresent()) {
            logger.warn("Users with id = {} not found.", userId);
            modelAndView.addObject("NotFound", "Users with id = " + userId + "not found.");
            return modelAndView;
        }
        modelAndView.addObject("user", this.userMapper.toDto(userEntity.get()));
        return modelAndView;
    }


    @GetMapping("/trello/users")
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        logger.info("Fetching all users.");
        modelAndView.addObject("users", this.userMapper.toDtoList(this.userService.getAll()));
        return modelAndView;
    }


    @PostMapping("/trello/users/login")
    public ModelAndView login(ModelAndView modelAndView,
                              @ModelAttribute UserEntity userEntity) {
        Optional<UserEntity> entity = userService.login(userEntity.getEmail(), userEntity.getPassword());
        if (entity.isPresent()) {

            modelAndView.addObject("user", entity);
            return modelAndView;
        }
        modelAndView.addObject("LoginFailed", "password or email are wrong, please enter again");
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @DeleteMapping("/trello/users/{user_id}")
    public ModelAndView deleteUserById(ModelAndView modelAndView,
                                       Integer userId) {
        this.userService.delete(userId);
        modelAndView.addObject("DeleteUser", "Your request has been successfully accepted!");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @Data
    @EqualsAndHashCode
    private static class UserData {

        @Email(message = "please enter valid email")
        private String email;

        @ValidPassword
        private String password;
    }
}
