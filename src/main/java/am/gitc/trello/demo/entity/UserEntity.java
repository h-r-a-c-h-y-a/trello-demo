package am.gitc.trello.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "please provide password with length minimum 8 character")
    @NotBlank(message = "please provide password with length minimum 8 character")
    @Size(min = 8, message = "please provide password with length minimum 8 character")
    private String password;

    @NotNull(message = "please provide your first and last name")
    @NotBlank(message = "please provide your first and last name")
    @Size(min = 5, message = "")
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @NotBlank
    @Column(name = "user_name")
    private String userName;

    private String initial ;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "about_me")
    private String aboutMe;

    public void setInitial(String fullName){
        this.initial = "" + fullName.split(" ")[0].toUpperCase().charAt(0) + "" + fullName.split(" ")[1].toUpperCase().charAt(0);
    }
}
