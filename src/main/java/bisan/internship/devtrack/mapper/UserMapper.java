/*
let's create a User mapper class
to map User entity to UserDTO, and UserDTO to User entity.

so whenever we build the rest Services, then we have to convert UserDTO into User JPA entity, and User JPA entity into UserDTO!
-> so instead of writing the same logic in all the classes,
-> let's create a mapper class, and let's keep the common logic
*/
package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role.roleName", target = "role")
    UserDTO toUserDTO(User user);

    @Mapping(target = "role", ignore = true) // The role will be set in the service
    User toUserEntity(UserDTO userDTO);
}
