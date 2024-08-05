package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.UserMapper;
import bisan.internship.devtrack.model.entity.User;
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
    private UserRepo userRepo;

//    public User getUser(Long id) {
//        Optional<User> user = this.userRepo.findById(id); // optional<> is a relatively new feature in java 8, found to fix null pointer exception (not found pointer)
//
//        //same purpose, different approach:
//        //return user.orElse( null);
//
//        if (user.isPresent())
//            return user.get();
//        return null;
//    }

    @Override
    public UserDTO createUser(UserDTO userDto) {

        User user = UserMapper.mapToUserEntity(userDto);

        User savedUser = userRepo.save(user);

        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User is not exists with given id: " + userId));

        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user) ->
                        UserMapper.mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO updatedUser) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with given id: " + userId)
        );

        user.setUpdatedAt(updatedUser.getUpdatedAt());
        user.setCreatedAt(updatedUser.getCreatedAt());

        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());


        user.setRole(updatedUser.getRole());
        user.setIsTeamLeader(updatedUser.getIsTeamLeader());

        user.setUsername(updatedUser.getUsername());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());

        User updatedUserObj=userRepo.save(user);
        return UserMapper.mapToUserDTO(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with given id: " + userId)
        );

        userRepo.deleteById(userId);

    }
}
