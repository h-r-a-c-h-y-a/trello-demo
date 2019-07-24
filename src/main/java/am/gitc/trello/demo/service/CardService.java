package am.gitc.trello.demo.service;

import am.gitc.trello.demo.entity.CardEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by User on 20.07.2019.
 */
public interface CardService {

    CardEntity createCard(CardEntity cardEntity);

    void deleteCard(short id);

    CardEntity updateCard(CardEntity cardEntity);

    CardEntity attachFile(short id, MultipartFile file) throws IOException;
}
