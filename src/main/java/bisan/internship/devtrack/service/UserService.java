package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    List<UserDTO> getUsersByRoleId(Long roleId);
    UserDTO updateUser(Long userId, UserDTO updatedUser);
    void deleteUser(Long userId);
//    List<User> getAllUsers();
    UserDTO getUserByUsername(String username);//email


}
