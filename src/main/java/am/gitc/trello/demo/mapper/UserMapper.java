package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.UserDto;
import am.gitc.trello.demo.entity.UserEntity;

/**
 * Created by User on 20.07.2019.
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper extends Mapper<UserEntity, UserDto> {
}
