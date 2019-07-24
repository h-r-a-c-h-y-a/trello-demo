package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.entity.CardEntity;
import am.gitc.trello.demo.repository.CardRepository;
import am.gitc.trello.demo.service.CardService;
import am.gitc.trello.demo.service.redis.RedisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by User on 20.07.2019.
 */

@Component
public class CardServiceImpl implements CardService{

    private CardRepository cardRepository;
    private RedisImpl<CardEntity> redis;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, RedisImpl<CardEntity> redis){
        this.cardRepository = cardRepository;
        this.redis = redis;
    }

    @Override
    public CardEntity createCard(CardEntity cardEntity) {
        return this.cardRepository.save(cardEntity);
    }

    @Override
    public void deleteCard(short id) {
        this.cardRepository.deleteById(id);
    }

    @Override
    public CardEntity updateCard(CardEntity cardEntity) {

        return this.cardRepository.save(cardEntity);
    }
}
