package am.gitc.trello.demo.repository;

import am.gitc.trello.demo.entity.UserEntity;
import am.gitc.trello.demo.entity.userModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmail(String email);

    List<UserEntity> findAllByRole(Role userType);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    UserEntity  findByActivationCode(String activeCode);
}
