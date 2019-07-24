package am.gitc.trello.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @NotBlank
    private String title;

    @NotBlank
    private Visibility visibility;

    @Column(name = "background_image_url")
    private String backgroundImageUrl;
}
