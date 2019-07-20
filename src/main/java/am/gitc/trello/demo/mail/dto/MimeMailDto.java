package am.gitc.trello.demo.mail.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.util.Map;


@Data
@EqualsAndHashCode(callSuper = true)
public class MimeMailDto extends MailDto {

    private Map<String, File> attachments;

    public boolean isMultiPart() {
        return attachments != null && attachments.size() > 0;
    }

    @Builder
    public MimeMailDto(String subject, String text, String[] to, String[] cc, String[] bcc, Map<String, File> attachments) {
        super(subject, text, to, cc, bcc);
        this.attachments = attachments;
    }
}
