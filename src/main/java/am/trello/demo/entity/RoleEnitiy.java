package am.trello.demo.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "roles")
public class RoleEnitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(unique = true)
    private String role;
}
