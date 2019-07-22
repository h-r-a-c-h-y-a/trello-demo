package am.gitc.trello.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserDto {

    private Integer id;

    private String email;


    private String password;

    @JsonProperty(value = "full_name")
    private String fullName;

    @JsonProperty(value = "user_name")
    private String userName;

    private String initial;

    private String activationCode;

    @JsonProperty(value = "image_url")
    private String imageUrl;

    @JsonProperty(value = "about_me")
    private String aboutMe;

    public void setInitial() {
        this.initial = "" + this.fullName.split(" ")[0].toUpperCase().charAt(0) + "" + this.fullName.split(" ")[1].toUpperCase().charAt(0);
    }
}
