package repository;

import lombok.RequiredArgsConstructor;
import model.User;
import util.CSVFileWorker;

import java.io.IOException;
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

    public static String toCsvString(User user) {
        return user.getId() + ";" +
                user.getName() + ";" +
                user.getLastname() + ";" +
                user.getUsername();
    }

    @Override
    public Optional<User> getById(Long id) throws IOException {
        List<User> allUsers = getAllUsers();
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return csvFileWorker.readAllLines()
                .stream()
                .map(UserRepository::scvLine2User)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public User updateById(User withUser) throws IOException {
        List<User> allUsers = getAllUsers();
        //find user
        //updated
        //list <- updated
        //saveAll(list);
        return null;
    }

    @Override
    public User add(User user) throws IOException {
        String user2Csv = toCsvString(user);
        csvFileWorker.writeCsv(user2Csv);

        return user;
    }

}