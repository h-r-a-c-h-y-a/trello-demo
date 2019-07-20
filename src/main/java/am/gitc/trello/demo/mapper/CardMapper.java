package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.CardDto;
import am.gitc.trello.demo.entity.CardEntity;

/**
 * Created by User on 20.07.2019.
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface CardMapper extends Mapper<CardEntity, CardDto> {
}
