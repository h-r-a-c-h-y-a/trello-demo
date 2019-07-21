package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.TaskBarDto;
import am.gitc.trello.demo.entity.TaskBarEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-21T15:05:55+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class TaskBarMapperImpl implements TaskBarMapper {

    @Override
    public TaskBarDto toDto(TaskBarEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TaskBarDto taskBarDto = new TaskBarDto();

        taskBarDto.setId( (int) entity.getId() );
        taskBarDto.setTitle( entity.getTitle() );

        return taskBarDto;
    }

    @Override
    public TaskBarEntity toEntity(TaskBarDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskBarEntity taskBarEntity = new TaskBarEntity();

        if ( dto.getId() != null ) {
            taskBarEntity.setId( dto.getId().byteValue() );
        }
        taskBarEntity.setTitle( dto.getTitle() );

        return taskBarEntity;
    }

    @Override
    public List<TaskBarDto> toDtoList(List<TaskBarEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<TaskBarDto> list = new ArrayList<TaskBarDto>( entity.size() );
        for ( TaskBarEntity taskBarEntity : entity ) {
            list.add( toDto( taskBarEntity ) );
        }

        return list;
    }

    @Override
    public List<TaskBarEntity> toEntityList(List<TaskBarDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<TaskBarEntity> list = new ArrayList<TaskBarEntity>( dto.size() );
        for ( TaskBarDto taskBarDto : dto ) {
            list.add( toEntity( taskBarDto ) );
        }

        return list;
    }
}
