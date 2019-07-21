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
public class RoleDto {

    private Integer id;

    private String role;

}
