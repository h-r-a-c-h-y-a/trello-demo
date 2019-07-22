package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.CardEntity;

/**
 * Created by User on 20.07.2019.
 */
public interface CardService {

    CardEntity createCard(CardEntity cardEntity);

    void deleteCard(short id);

<<<<<<< HEAD
    CardEntity updateCard(short id, CardEntity cardEntity);
=======
    CardEntity updateCard(CardEntity cardEntity);
>>>>>>> 5f4712a003b5b828bfdc6080f0fa3cbc366286d0
}
