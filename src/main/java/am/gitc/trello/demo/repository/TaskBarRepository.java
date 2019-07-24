package am.gitc.trello.demo.repository;

import am.gitc.trello.demo.entity.CardEntity;
import am.gitc.trello.demo.entity.TaskBarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskBarRepository extends JpaRepository<TaskBarEntity, Integer> {

    void deleteById(short deleteID);
}
