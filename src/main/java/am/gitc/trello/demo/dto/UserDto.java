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
@Table(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "initial")
    private String initial;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "about_me")
    private String aboutMe;
}
