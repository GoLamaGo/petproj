package service;

import lombok.RequiredArgsConstructor;
import model.User;
import repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.add(user);
    }

    public List<User> addUsers(List<User> users) {
        return userRepository.addUsers(users);
    }

    public User getById(Long id) {
//        return userRepository.getById(id).orElseThrow(IllegalArgumentException::new);
        if (userRepository.getById(id).isPresent()) {
            return userRepository.getById(id).get();
        } else {
            System.out.println("Пользователь с таким id не найден");
            return null;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User updateById(User user) {
        return userRepository.updateById(user);
    }

    public boolean deleteUserById(Long id) {
        return userRepository.deleteById(id);
    }
}