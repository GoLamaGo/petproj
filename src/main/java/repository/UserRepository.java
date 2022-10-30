package repository;

import lombok.RequiredArgsConstructor;
import model.User;
import util.CSVFileWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserRepository implements CrudRepository<User> {

    private final CSVFileWorker csvFileWorker;

    private static User scvLine2User(String csvLine) {
        User user = new User();
        String[] userArray = csvLine.split(";");

        user.setId(Long.parseLong(userArray[0]));
        user.setName(userArray[1]);
        user.setLastname(userArray[2]);
        user.setUsername(userArray[3]);

        return user;
    }

    public static String userToCsvString(User user) {
        return user.getId() + ";" +
                user.getName() + ";" +
                user.getLastname() + ";" +
                user.getUsername();
    }

    @Override
    public Optional<User> getById(Long id) throws IOException {
        List<User> allUsers = getAllUsers();
        return allUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return csvFileWorker.readCsv()
                .stream()
                .map(UserRepository::scvLine2User)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) throws IOException {
        var users = getAllUsers();
        List<String> csvStrings = new ArrayList<>();
        boolean result = false;

        if (users.stream().anyMatch(findUser -> findUser.getId().equals(id))) {
            result = true;
            csvStrings = users.stream()
                    .filter(user -> !user.getId().equals(id))
                    .map(UserRepository::userToCsvString).toList();
            csvFileWorker.writeCsv(csvStrings);
        }

        return result;
    }

    @Override
    public User updateById(User withUser) throws IOException {
        List<User> allUsers = getAllUsers();

        if (allUsers.contains(withUser)) {
            var userIndex = allUsers.indexOf(withUser);
            allUsers.get(userIndex).setName(withUser.getName());
            allUsers.get(userIndex).setLastname(withUser.getLastname());
            allUsers.get(userIndex).setUsername(withUser.getUsername());
        }

        return withUser;
    }

    @Override
    public synchronized List<User> add(List<User> users) throws IOException {
        var allUsers = getAllUsers();
        List<String> csvStrings = new ArrayList<>();

        for (User user : users) {
            if (!allUsers.contains(user)) {
                users.add(user);
                csvStrings = users.stream()
                        .map(UserRepository::userToCsvString).toList();
                csvFileWorker.writeCsv(csvStrings);
            }
        }
        return allUsers;
    }

}