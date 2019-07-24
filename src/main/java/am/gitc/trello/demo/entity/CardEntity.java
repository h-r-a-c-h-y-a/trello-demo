package am.gitc.trello.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "cards")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @NotBlank
    @Size(max = 100)
    private String title;

    private String description;

    @Column(name = "file_url")
    private String fileUrl;

    private String comment;

    @Size(max = 100)
    private String history;
}
