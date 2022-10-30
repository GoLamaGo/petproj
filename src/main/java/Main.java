import model.User;
import util.CSVFileWorker;
import repository.UserRepository;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVFileWorker csvFileWorker = new CSVFileWorker("src/main/resources/", "users.csv");
        UserRepository userRepository = new UserRepository(csvFileWorker);
        UserService userService = new UserService(userRepository);

        Long userId2Delete = 2L;

        List<User> newUsers = new ArrayList<>(List.of(
                User.builder()
                        .id(1L)
                        .name("Chuck")
                        .lastname("Norris")
                        .username("c.norris")
                        .build(),
                User.builder()
                        .id(2L)
                        .name("Bruce")
                        .lastname("Willis")
                        .username("b.willis")
                        .build(),
                User.builder()
                        .id(3L)
                        .name("Silvester")
                        .lastname("Stallone")
                        .username("s.stallone")
                        .build()
        ));

        var addUsers = userService.addUser(newUsers);
        var usersInFile = userService.getAllUsers();
        var deleteUser = userService.deleteUserById(userId2Delete);

        var user4Change = usersInFile.stream().findAny().get();
        user4Change.setName("ChangedName");
        var updateUser = userService.updateById(user4Change);

        var updateUnknownUser = userService.updateById(new User());

        System.out.println("add users : " + addUsers.toString());
//        addUsers.forEach(System.out::println);      // TODO Exception in thread "main" java.util.ConcurrentModificationException
        System.out.println("all users: " + usersInFile.toString());
        System.out.println("delete user : " + deleteUser);
        System.out.println("update user : " + updateUser.toString());

    }
}
