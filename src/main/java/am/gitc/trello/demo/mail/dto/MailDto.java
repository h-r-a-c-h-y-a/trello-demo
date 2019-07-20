package am.gitc.trello.demo.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class MailDto {

    private String subject;
    private String text;

    protected String[] to;
    protected String[] cc;
    protected String[] bcc;
}
