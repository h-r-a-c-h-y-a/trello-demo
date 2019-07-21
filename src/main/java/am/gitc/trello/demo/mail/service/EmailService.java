package am.gitc.trello.demo.mail.service;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.mail.dto.MimeMailDto;
import am.gitc.trello.demo.mail.dto.Protocol;
import am.gitc.trello.demo.mail.dto.SimpleMailDto;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(SimpleMailDto mailDto);

    void sendMimeMessage(MimeMailDto mimeMailDto, boolean isHTML) throws MessagingException;

    void printMessages(Protocol protocolType);

    void validateEmail(UserEntity entity) throws MessagingException;

}
