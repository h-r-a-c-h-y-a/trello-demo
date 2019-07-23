package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.CardEntity;

/**
 * Created by User on 20.07.2019.
 */
public interface CardService {

    CardEntity createCard(CardEntity cardEntity);

    void deleteCard(short id);

    CardEntity updateCard(CardEntity cardEntity);
}
