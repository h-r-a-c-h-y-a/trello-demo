package am.gitc.trello.demo.mail.service.impl;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.mail.dto.MimeMailDto;
import am.gitc.trello.demo.mail.dto.Protocol;
import am.gitc.trello.demo.mail.dto.SimpleMailDto;
import am.gitc.trello.demo.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleMessage(SimpleMailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getTo());
        message.setCc(mailDto.getCc());
        message.setBcc(mailDto.getBcc());
        message.setSubject(mailDto.getSubject());
        message.setSentDate(new Date());
        message.setText(mailDto.getText());
        this.mailSender.send(message);
    }

    @Override
    public void sendMimeMessage(MimeMailDto mimeMailDto, boolean isHTML) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper;
        if (mimeMailDto.isMultiPart()) {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            for (Map.Entry<String, File> fileEntry : mimeMailDto.getAttachments().entrySet()) {
                helper.addAttachment(fileEntry.getKey(), new FileSystemResource(fileEntry.getValue()));
            }
        } else {
            helper = new MimeMessageHelper(message, "UTF-8");
        }
        helper.setTo(mimeMailDto.getTo());
        helper.setCc(mimeMailDto.getCc());
        helper.setBcc(mimeMailDto.getBcc());
        helper.setSubject(mimeMailDto.getSubject());
        helper.setText(mimeMailDto.getText(), isHTML);
        this.mailSender.send(message);
    }

    @Override
    public void printMessages(Protocol protocolType) {
        Properties properties = getServerProperties(protocolType);
        Session session = Session.getInstance(properties);
        try {
            Store store = session.getStore(protocolType.getProtocol());
            store.connect();
            Folder inboxFolder = store.getFolder("INBOX");
            inboxFolder.open(Folder.READ_ONLY);
            Message[] messages = inboxFolder.getMessages();
            if (messages != null) {
                for (int i = 0; i < messages.length; i++) {
                    Message message = messages[i];
                    Address[] fromAddress = message.getFrom();
                    String from = fromAddress[0].toString();
                    String subject = message.getSubject();

                    Address[] toList = message.getRecipients(Message.RecipientType.TO);
                    String to = toList[0].toString();
                    Address[] ccList = message.getRecipients(Message.RecipientType.CC);
                    Address[] bccList = message.getRecipients(Message.RecipientType.BCC);
                    String sentDate = message.getSentDate().toString();

                    String contentType = message.getContentType();
                    String messageContent = "";

                    if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                        try {
                            Object content = message.getContent();
                            if (content != null) {
                                messageContent = content.toString();
                            }
                        } catch (Exception ex) {
                            messageContent = "[Error downloading content]";
                            ex.printStackTrace();
                        }
                    }

                    StringBuilder sbForBCC = new StringBuilder();
                    StringBuilder sbForCC = new StringBuilder();
                    if (ccList != null && ccList.length > 1) {
                        for (int j = 0; j < ccList.length; j++) {
                            sbForCC.append(ccList[0]).append(", ");
                        }
                    }
                    if (bccList != null && bccList.length > 1) {
                        for (int j = 0; j < bccList.length; j++) {
                            sbForBCC.append(bccList[0]).append(", ");
                        }
                    }
                    System.out.println("Message #" + (i + 1) + ":");
                    System.out.println("\t From: " + from);
                    System.out.println("\t To: " + to);
                    System.out.println("\t CC: " + (ccList != null && ccList.length > 1 ? sbForCC.toString() : ccList != null ? ccList[0].toString() : "empty list"));
                    System.out.println("\t BCC: " + (bccList != null && bccList.length > 1 ? sbForBCC.toString() : bccList != null ? bccList[0].toString() : "empty list"));
                    System.out.println("\t Subject: " + subject);
                    System.out.println("\t Sent Date: " + sentDate);
                    System.out.println("\t Message: " + messageContent);
                }
                inboxFolder.close(false);
                store.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateEmail(UserEntity entity) throws MessagingException {
        String text = String.format("<h1 style = \"background-color:rgb(183, 255, 255); color:black\" >Hello %s!! " +
                "For activation your account <a href='http://localhost:8080/activate/%s' style = \"background-color:rgb(157, 243, 217); color:rgb(16, 12, 122)\"><i>please click here</i></a> " +
                "</br> Enjoy using the product!</h1>", entity.getFullName(), entity.getActivationCode());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(entity.getEmail());
        helper.setSubject("Trello. Activation Code!");
        helper.setText(text, true);
        this.mailSender.send(message);
    }

    private static Properties getServerProperties(Protocol protocolType) {
        Properties properties = new Properties();
        properties.put(String.format("spring.mail.%s.host", protocolType.getProtocol()), protocolType.getHost());
        properties.put(String.format("spring.mail.%s.port", protocolType.getProtocol()), protocolType.getPort());
        properties.setProperty(String.format("spring.mail.%s.ssl.enable", protocolType.getProtocol()), String.valueOf(true));
        properties.setProperty(String.format("spring.mail.properties.mail.%s.auth=true", protocolType.getProtocol()), String.valueOf(true));
        properties.setProperty("spring.mail.username", Protocol.USER_NAME);
        properties.setProperty("spring.mail.password", Protocol.PASSWORD);
        return properties;
    }

}
