package am.gitc.trello.demo.mail.controller;

import am.gitc.trello.demo.mail.dto.Protocol;
import am.gitc.trello.demo.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailReceiverController {

    private final EmailService emailService;

    @Autowired
    public EmailReceiverController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/api/receiver_mail_messages")
    public ResponseEntity receiverMessages() {
        try {
            this.emailService.printMessages(Protocol.IMAP);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}

