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

    public User addUser(User user) throws IOException {
        return userRepository.add(user);
    }

    public Optional<User> getById(int id) {
//        userRepository
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}