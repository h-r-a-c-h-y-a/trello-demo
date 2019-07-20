package am.gitc.trello.demo.mapper;

import am.gitc.trello.demo.dto.UserDto;
import am.gitc.trello.demo.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-20T16:11:06+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setInitial( entity.getInitial() );
        if ( entity.getId() != null ) {
            userDto.setId( entity.getId().intValue() );
        }
        userDto.setEmail( entity.getEmail() );
        userDto.setPassword( entity.getPassword() );
        userDto.setFullName( entity.getFullName() );
        userDto.setUserName( entity.getUserName() );
        userDto.setImageUrl( entity.getImageUrl() );
        userDto.setAboutMe( entity.getAboutMe() );

        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setInitial( dto.getInitial() );
        if ( dto.getId() != null ) {
            userEntity.setId( dto.getId().longValue() );
        }
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPassword( dto.getPassword() );
        userEntity.setFullName( dto.getFullName() );
        userEntity.setUserName( dto.getUserName() );
        userEntity.setImageUrl( dto.getImageUrl() );
        userEntity.setAboutMe( dto.getAboutMe() );

        return userEntity;
    }

    @Override
    public List<UserDto> toDtoList(List<UserEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entity.size() );
        for ( UserEntity userEntity : entity ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dto.size() );
        for ( UserDto userDto : dto ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }
}
