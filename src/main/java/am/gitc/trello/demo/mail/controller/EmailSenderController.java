package am.gitc.trello.demo.mail.controller;

import am.gitc.trello.demo.exception.EmailException;
import am.gitc.trello.demo.mail.dto.MimeMailDto;
import am.gitc.trello.demo.mail.dto.SimpleMailDto;
import am.gitc.trello.demo.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.File;
import java.util.HashMap;

@Slf4j
@RestController
public class EmailSenderController {

    private final EmailService emailService;

    @Autowired
    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/api/send_simple_mail")
    public String sendSimpleMail(Model model) {
        SimpleMailDto mailDto = SimpleMailDto.builder()
                .to(new String[]{"hrachxach90@mail.ru"})
                .subject("Test Email")
                .text("Test email body")
                .build();
        this.emailService.sendSimpleMessage(mailDto);
        model.addAttribute("MessageSuccess","Message successfully sending");
        return "home";
    }

    @GetMapping("/api/send_mime_mail")
    public String sendMimeMail(Model model) {
        try {
            MimeMailDto mailDto = MimeMailDto.builder()
                    .to(new String[]{"hrachxach90@mail.ru"})
                    .subject("Test Email")
                    .text("<h1 style=\"color:red\">Test Email Body</h1>")
                    .build();
            this.emailService.sendMimeMessage(mailDto, true);
            model.addAttribute("MessageSuccess","Message successfully sending");
        } catch (MessagingException e) {
            log.error("ERROR", e.getMessage());
           throw new EmailException(e);
        }
        return "home";
    }

    @GetMapping("/api/send_mime_mail_with_attachment")
    public String sendMimeMailWithAttachments(Model model) {
        try {
            MimeMailDto mailDto = MimeMailDto.builder()
                    .to(new String[]{"hrachxach90@mail.ru"})
                    .subject("Test Email")
                    .text("<h1 style=\"color:red\">Test Email Body</h1>")
                    .attachments(new HashMap<String, File>() {{
                        put("post index", new File("src\\main\\resources\\files\\почтовые индексы Армении.txt"));
                    }})
                    .build();
            this.emailService.sendMimeMessage(mailDto, true);
            model.addAttribute("MessageSuccess","Message successfully sending");
        } catch (MessagingException e) {
            log.error("ERROR", e.getMessage());
            throw new EmailException(e);
        }
        return "home";
    }
}
