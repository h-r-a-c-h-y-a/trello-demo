package am.gitc.trello.demo.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
//@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @NotBlank
    private String title;

    @NotBlank
    private Visibility visibility;

    private String backgroundImageUrl;

    @Transient
    private MultipartFile backgroundImage;

//    public MultipartFile setFile(String backgroundImage){
//        if (backgroundImageUrl != null)
//        try {
//            this.backgroundImage.
//        }
//    }
}
