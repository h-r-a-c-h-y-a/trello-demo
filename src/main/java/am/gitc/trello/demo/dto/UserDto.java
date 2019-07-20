package am.gitc.trello.demo.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
public class UserDto {

    private Integer id;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "please provide password with length minimum 8 character")
    @NotBlank(message = "please provide password with length minimum 8 character")
    @Size(min = 8, message = "please provide password with length minimum 8 character")
    private String password;

    @NotNull(message = "please provide your first and last name")
    @NotBlank(message = "please provide your first and last name")
    @Size(min = 5, message = "")
    private String fullName;

    @NotNull
    @NotBlank
    private String userName;

    private String initial;

    private String imageUrl;

    private String aboutMe;

    public void setInitial(String fullName){
        this.initial = "" + fullName.split(" ")[0].toUpperCase().charAt(0) + "" + fullName.split(" ")[1].toUpperCase().charAt(0);
    }
}
