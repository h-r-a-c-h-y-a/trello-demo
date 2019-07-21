package am.gitc.trello.demo.repository;

import am.gitc.trello.demo.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 20.07.2019.
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    @Query(value = "update cards c set c.title = :title where c.id = :id", nativeQuery = true)
    CardEntity updateCardChangingTitle(int id, String title);

    @Query(value = "update cards c set c.comment = :comment where c.id = :id", nativeQuery = true)
    String updateCarddAddingComment(int id, String comment);

    @Query(value = "update cards c set c.description = :description where c.id = :id", nativeQuery = true)
    String updateCardAddingDescription(int id, String description);

    @Query(value = "update cards c set c.comment = :comment where c.id = :id", nativeQuery = true)
    String updateCardDeletingComment(int id, String comment);

}
