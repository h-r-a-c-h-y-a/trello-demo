package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.CardDto;
import am.gitc.trello.demo.entity.CardEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-21T21:37:06+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto toDto(CardEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setId( (int) entity.getId() );
        cardDto.setTitle( entity.getTitle() );
        cardDto.setDescription( entity.getDescription() );
        cardDto.setComment( entity.getComment() );
        cardDto.setHistory( entity.getHistory() );

        return cardDto;
    }

    @Override
    public CardEntity toEntity(CardDto dto) {
        if ( dto == null ) {
            return null;
        }

        CardEntity cardEntity = new CardEntity();

        if ( dto.getId() != null ) {
            cardEntity.setId( dto.getId().shortValue() );
        }
        cardEntity.setTitle( dto.getTitle() );
        cardEntity.setDescription( dto.getDescription() );
        cardEntity.setComment( dto.getComment() );
        cardEntity.setHistory( dto.getHistory() );

        return cardEntity;
    }

    @Override
    public List<CardDto> toDtoList(List<CardEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<CardDto> list = new ArrayList<CardDto>( entity.size() );
        for ( CardEntity cardEntity : entity ) {
            list.add( toDto( cardEntity ) );
        }

        return list;
    }

    @Override
    public List<CardEntity> toEntityList(List<CardDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<CardEntity> list = new ArrayList<CardEntity>( dto.size() );
        for ( CardDto cardDto : dto ) {
            list.add( toEntity( cardDto ) );
        }

        return list;
    }
}
