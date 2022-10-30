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

    public List<User> addUser(List<User> users) {
        try {
            return userRepository.add(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getById(Long id) {
        try {
            return userRepository.getById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public User updateById(User withUser) throws IOException {
        return userRepository.updateById(withUser);
    }

    public boolean deleteUserById(Long id) {
        try {
            return userRepository.deleteById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}