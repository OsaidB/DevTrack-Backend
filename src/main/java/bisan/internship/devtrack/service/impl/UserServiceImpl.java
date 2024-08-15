package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.UserMapper;
import bisan.internship.devtrack.model.entity.Role;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.RoleRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Role role = roleRepo.findById(userDTO.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id : " + userDTO.getRole()));
        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        User user = UserMapper.INSTANCE.toUserEntity(userDTO);
        user.setRole(role);

        User savedUser = userRepo.save(user);
        return UserMapper.INSTANCE.toUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserMapper.INSTANCE::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByRoleId(Long roleId){
        roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id : " + roleId));
        List<User> users = userRepo.findUsersByRoleRoleId(roleId);
        return users.stream().map(UserMapper.INSTANCE::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO updatedUser) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the given id: " + userId));

        // Fetch the Role entity from the database
        Role role = roleRepo.findById(updatedUser.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id : " + updatedUser.getRole()));
        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        user.setUpdatedAt(updatedUser.getUpdatedAt());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(role); // Set the Role entity
        user.setIsTeamLeader(updatedUser.getIsTeamLeader());
        user.setUsername(updatedUser.getUsername());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());

        User updatedUserObj = userRepo.save(user);
        return UserMapper.INSTANCE.toUserDTO(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        userRepo.deleteById(userId);
    }
}
