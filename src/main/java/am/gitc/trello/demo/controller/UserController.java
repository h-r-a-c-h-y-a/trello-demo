package am.gitc.trello.demo.controller;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.mail.service.EmailService;
import am.gitc.trello.demo.service.UserService;
import am.gitc.trello.demo.validation.annotations.ValidPassword;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    //changing
    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;

    }


    @GetMapping({"/", "/trello/users/login"})
    public ModelAndView goTologinPage(ModelAndView model) {
        model.addObject("userForm", new UserEntity());
        model.setViewName("login");
        return model;
    }

    @GetMapping("/trello/users/register")
    public ModelAndView goToRegPage(ModelAndView model) {
        model.addObject("userForm", new UserEntity());
        model.setViewName("register");
        return model;
    }

    @PostMapping("/trello/users/register")
    public ModelAndView registration(ModelAndView modelAndView,
                                     @ModelAttribute("userForm") @Valid UserEntity userEntity,
                                     @RequestParam("file") MultipartFile file) {
        userEntity.setInitial();
        if (!this.userService.isExist(userEntity.getEmail())) {
            userEntity.setActivationCode(UUID.randomUUID().toString());
            try {
                this.userService.register(userEntity, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public void activateAccount(ModelAndView modelAndView,
                                @PathVariable("activation_code") String activationCode,
                                HttpServletRequest req,
                                HttpServletResponse resp) {
        try {
            boolean active = this.userService.hasActiveCode(activationCode);
            if (!active) {
                modelAndView.addObject("ActivationFailed", "Activation code not found");
                req.getRequestDispatcher("/trello/users/register").forward(req, resp);
                return;
            }
            modelAndView.addObject("RegisterSuccess", "Successfully registered!");
            req.getRequestDispatcher("/trello/users/login").forward(req, resp);
        }   catch (IOException | ServletException e) {
            e.printStackTrace();
        }
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
        modelAndView.addObject("user", userEntity.get());
        return modelAndView;
    }


    @GetMapping("/trello/users")
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        logger.info("Fetching all users.");
        modelAndView.addObject("users", this.userService.getAll());
        return modelAndView;
    }


    @PostMapping("/trello/users/login")
    public ModelAndView login(ModelAndView modelAndView,
                              @ModelAttribute UserEntity userEntity) {
        Optional<UserEntity> entity = userService.login(userEntity.getEmail(), userEntity.getPassword());
        if (entity.isPresent()) {

            modelAndView.addObject("userForm", entity);
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

}
