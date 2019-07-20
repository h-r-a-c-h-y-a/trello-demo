package am.gitc.trello.demo.mapper;

import java.util.List;

/**
 * Created by User on 20.07.2019.
 */
interface Mapper<Entity, Dto> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

    List<Dto> toDtoList(List<Entity> entity);

    List<Entity> toEntityList(List<Dto> dto);
}

