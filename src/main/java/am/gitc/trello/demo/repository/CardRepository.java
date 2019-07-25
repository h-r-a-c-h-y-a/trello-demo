package am.gitc.trello.demo.repository;

import am.gitc.trello.demo.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 20.07.2019.
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update CardEntity cardEntity set cardEntity.fileUrl = :file_url where cardEntity.id = :id")
    CardEntity updateFileUrl(@Param("id") short id, @Param("file_url") String fileUrl);

    CardEntity findById(short id);

    void deleteById(short id);
}
