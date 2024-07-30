package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.mapper.UserMapper;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        User savedUser= userRepo.save(user);

        return UserMapper.mapToUserDTO(savedUser);
    }
}
