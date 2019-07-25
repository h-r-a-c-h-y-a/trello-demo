package am.gitc.trello.demo.entity;

import am.gitc.trello.demo.entity.userModel.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "please provide password with length minimum 8 character")
    @Size(min = 8, message = "please provide password with length minimum 8 character")
    private String password;


    @NotBlank(message = "please provide your first and last name")
    @Size(min = 5, message = "")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank
    @Column(name = "user_name")
    private String userName;

    private String initial ;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "activation_code")
    private String activationCode;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"))
//    private Set<RoleEnitiy> roles;

    public void setInitial() {
        this.initial = "" + this.fullName.split(" ")[0].toUpperCase().charAt(0) + "" + this.fullName.split(" ")[1].toUpperCase().charAt(0);
    }
}
