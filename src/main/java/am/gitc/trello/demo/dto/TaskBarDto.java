package am.gitc.trello.demo.dto;

import lombok.*;

import javax.persistence.*;

/**
 * Created by User on 20.07.2019.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "task_bar")
public class TaskBarDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

}
