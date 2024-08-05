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

public class UserMapper {

    public static UserDTO mapToUserDTO(User user) { //method to map(convert) User JPA entity into UserDTO
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getIsTeamLeader(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapToUserEntity(UserDTO userDto) { //method to map(convert) UserDTO into User JPA entity
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getRole(),
                userDto.getIsTeamLeader(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }

}
