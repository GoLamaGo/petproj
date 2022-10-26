package service;

import model.User;
import repository.CrudRepository;
import repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) throws IOException {
        return userRepository.add(user);
    }

    public Optional<User> getById(int id) {
//        userRepository
        return null;
    }

    public List<User> getAllUsers() throws IOException {
        return userRepository.getAllUsers();
    }
}