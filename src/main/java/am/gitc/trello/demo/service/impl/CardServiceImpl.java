package am.gitc.trello.demo.service.impl;

import am.gitc.trello.demo.entity.CardEntity;
import am.gitc.trello.demo.repository.CardRepository;
import am.gitc.trello.demo.service.CardService;
import am.gitc.trello.demo.service.redis.impl.RedisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    @Override
    public CardEntity attachFile(short id, MultipartFile file) throws IOException {
        String filePath = CardServiceImpl.class.getResource("/files").getPath() + "/" + id;
        CardEntity entity = this.cardRepository.updateFileUrl(id, "/files/" + id);
        byte [] content = file.getBytes();
        try(OutputStream out = new FileOutputStream(filePath)) {
            out.write(content);
        }
        return entity;
    }
}
