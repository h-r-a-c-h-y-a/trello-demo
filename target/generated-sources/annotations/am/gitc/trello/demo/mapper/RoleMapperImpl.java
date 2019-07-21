package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.RoleDto;
import am.gitc.trello.demo.entity.RoleEnitiy;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-20T16:14:50+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toDto(RoleEnitiy entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( (int) entity.getId() );
        roleDto.setRole( entity.getRole() );

        return roleDto;
    }

    @Override
    public RoleEnitiy toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEnitiy roleEnitiy = new RoleEnitiy();

        if ( dto.getId() != null ) {
            roleEnitiy.setId( dto.getId().byteValue() );
        }
        roleEnitiy.setRole( dto.getRole() );

        return roleEnitiy;
    }

    @Override
    public List<RoleDto> toDtoList(List<RoleEnitiy> entity) {
        if ( entity == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entity.size() );
        for ( RoleEnitiy roleEnitiy : entity ) {
            list.add( toDto( roleEnitiy ) );
        }

        return list;
    }

    @Override
    public List<RoleEnitiy> toEntityList(List<RoleDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<RoleEnitiy> list = new ArrayList<RoleEnitiy>( dto.size() );
        for ( RoleDto roleDto : dto ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }
}
