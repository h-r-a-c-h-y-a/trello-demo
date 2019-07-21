package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.repository.CardRepository;
import am.gitc.trello.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by User on 20.07.2019.
 */

@Component
public class CardServiceImpl implements CardService{


    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }


    //modelandview-i hamar e taki grvac@
//    public CardEntity createCard(String title){
//        CardEntity cardEntity = new CardEntity();
//        cardEntity.setTitle(title);
//        cardRepository.save(cardEntity);
//        return cardEntity;
//    }
//
//    public void deleteCard(int id){
//        this.cardRepository.deleteById(id);
//    }
//
//    public CardEntity updateCardChangingTitle(int id, String title){
//        CardEntity cardEntity = this.cardRepository.getOne(id);
//        cardEntity.setTitle(title);
//        this.cardRepository.save(cardEntity);
//        return cardEntity;
//    }
//
//    public String addComment(int id, String comment){
//        CardEntity cardEntity = this.cardRepository.getOne(id);
//        cardEntity.setComment(comment);
//        this.cardRepository.save(cardEntity);
//        return comment;
//    }
//
//    public String addDescription(int id, String description){
//        CardEntity cardEntity = this.cardRepository.getOne(id);
//        cardEntity.setComment(description);
//        this.cardRepository.save(cardEntity);
//        return description;
//    }
//
//    public void deleteComment(int id, String comment){
//        CardEntity cardEntity = this.cardRepository.getOne(id);
//        cardEntity.setComment(comment);
//        this.cardRepository.save(cardEntity);
//    }
}
