package am.gitc.trello.demo.mail.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleMailDto extends MailDto {

    @Builder
    public SimpleMailDto(String subject, String text, String[] to, String[] cc, String[] bcc) {
        super(subject, text, to, cc, bcc);
    }
}
