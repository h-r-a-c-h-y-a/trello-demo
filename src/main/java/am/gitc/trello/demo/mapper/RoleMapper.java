package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.RoleDto;
import am.gitc.trello.demo.entity.RoleEnitiy;

/**
 * Created by User on 20.07.2019.
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface RoleMapper extends Mapper<RoleEnitiy, RoleDto> {
}
