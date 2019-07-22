package am.gitc.trello.demo.repository;

import am.gitc.trello.demo.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 20.07.2019.
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, Short> {
}
