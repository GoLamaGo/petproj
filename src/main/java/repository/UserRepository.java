package repository;

import Util.CSVFileWorker;
import lombok.Getter;
import lombok.Setter;
import model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserRepository implements CrudRepository<User> {

    private final CSVFileWorker csvFileWorker;

    public UserRepository(CSVFileWorker csvFileWorker) {
        this.csvFileWorker = csvFileWorker;
    }

    @Override
    public Optional<User> getById(Long id) throws IOException {
        csvFileWorker.readLine();
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return csvFileWorker.readAllLines().stream().map(User::scvLine2User).collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public User updateById(Long id) {
        return null;
    }

    @Override
    public User add(User data) throws IOException {
        String user2Csv = data.toString();
        csvFileWorker.writeCsv(user2Csv);

        return data;
    }

}