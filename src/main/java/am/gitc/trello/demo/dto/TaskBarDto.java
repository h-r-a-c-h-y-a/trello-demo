package am.gitc.trello.demo.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by User on 20.07.2019.
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TaskBarDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String title;

}