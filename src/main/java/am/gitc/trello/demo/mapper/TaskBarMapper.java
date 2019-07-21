package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.TaskBarDto;
import am.gitc.trello.demo.entity.TaskBarEntity;
import org.springframework.core.task.TaskDecorator;

/**
 * Created by User on 20.07.2019.
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface TaskBarMapper extends Mapper<TaskBarEntity, TaskBarDto> {
}
