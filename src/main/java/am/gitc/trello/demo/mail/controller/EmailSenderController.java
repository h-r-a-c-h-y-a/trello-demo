package am.gitc.trello.demo.mail.controller;

import am.gitc.trello.demo.mail.dto.MimeMailDto;
import am.gitc.trello.demo.mail.dto.SimpleMailDto;
import am.gitc.trello.demo.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.File;
import java.util.HashMap;

@RestController
public class EmailSenderController {

    private final EmailService emailService;

    @Autowired
    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/api/send_simple_mail")
    public ResponseEntity sendSimpleMail() {
        SimpleMailDto mailDto = SimpleMailDto.builder()
                .to(new String[]{"hrachxach90@mail.ru"})
                .cc(new String[]{"ani.gevorgyan.2002@list.ru", "hrach.matevosyan96@mail.ru", "astghikaramyan@gmail.com", "maria.eghiazaryan1988@gmail.com"})
                .subject("Test Email")
                .text("Test email body")
                .build();
        this.emailService.sendSimpleMessage(mailDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/send_mime_mail")
    public ResponseEntity sendMimeMail() {
        try {
            MimeMailDto mailDto = MimeMailDto.builder()
                    .to(new String[]{"hrachxach90@mail.ru"})
                    .subject("Test Email")
                    .cc(new String[]{"ani.gevorgyan.2002@list.ru", "hrach.matevosyan96@mail.ru", "astghikaramyan@gmail.com", "maria.eghiazaryan1988@gmail.com"})
                    .text("<h1 style=\"color:red\">Test Email Body</h1>")
                    .build();
            this.emailService.sendMimeMessage(mailDto, true);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/send_mime_mail_with_attachment")
    public ResponseEntity sendMimeMailWithAttachments() {
        try {
            MimeMailDto mailDto = MimeMailDto.builder()
                    .to(new String[]{"hrachxach90@mail.ru"})
                    .subject("Test Email")
                    .cc(new String[]{"ani.gevorgyan.2002@list.ru", "hrach.matevosyan96@mail.ru", "astghikaramyan@gmail.com", "maria.eghiazaryan1988@gmail.com"})
                    .text("<h1 style=\"color:red\">Test Email Body</h1>")
                    .attachments(new HashMap<String, File>() {{
                        put("post index", new File("src\\main\\resources\\files\\почтовые индексы Армении.txt"));
                    }})
                    .build();
            this.emailService.sendMimeMessage(mailDto, true);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
