package fr.apside.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.apside.demo.domain.User;
import fr.apside.demo.web.dto.UserDto;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
