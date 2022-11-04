package repository;

import lombok.RequiredArgsConstructor;
import model.User;
import util.CSVFileWorker;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    public static String userToCsvLine(User user) {
        return user.getId() + ";" +
                user.getName() + ";" +
                user.getLastname() + ";" +
                user.getUsername();
    }

    @Override
    public List<User> getAllUsers() {
        return csvFileWorker.readCsv().stream()
                .map(UserRepository::scvLine2User)
                .toList();
    }

    @Override
    public Optional<User> getById(Long id) {
        List<User> allUsers = getAllUsers();
        return allUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean deleteById(Long id) {
        var users = getAllUsers();

        if (users.stream().anyMatch(findUser -> findUser.getId().equals(id))) {
            csvFileWorker.writeCsv(users.stream()
                    .filter(user -> !user.getId().equals(id))
                    .map(UserRepository::userToCsvLine)
                    .toList());
            return true;
        }

        return false;
    }

    @Override
    public User updateById(User user) {
        List<User> allUsers = getAllUsers();

        if (allUsers.contains(user)) {
            var userIndex = allUsers.indexOf(user);
            allUsers.get(userIndex).setName(user.getName());
            allUsers.get(userIndex).setLastname(user.getLastname());
            allUsers.get(userIndex).setUsername(user.getUsername());
            return user;
        }

        // TODO прокидывать exception или возвращать null ?
        System.out.println("Не найден пользователь для апдейта");
        return null;
    }

    @Override
    public User add(User user) {
        var allUsers = getAllUsers();

        if (!allUsers.contains(user)) {
            allUsers.add(user);
            csvFileWorker.writeCsv(allUsers.stream()
                    .map(UserRepository::userToCsvLine)
                    .toList());
        }

        return user;    // TODO в любом случае возвращаем этого юзера? либо добавили и он есть, либо уже был
    }

    public List<User> addUsers(List<User> users) {
        var allUsers = getAllUsers();
        var iterator = allUsers.iterator();

        while (iterator.hasNext()) {
            if (!allUsers.contains(iterator.next())) {
                users.add(iterator.next());
                csvFileWorker.writeCsv(users.stream()
                        .map(UserRepository::userToCsvLine)
                        .toList());
            }
        }
        return allUsers;
    }
}