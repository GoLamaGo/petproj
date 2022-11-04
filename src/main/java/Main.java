import model.User;
import util.CSVFileWorker;
import repository.UserRepository;
import service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String csvFileLocation = "src/main/resources/";
        String csvFileName = "users.csv";

        File file = CSVFileWorker.createFile(csvFileLocation, csvFileName);
        CSVFileWorker csvFileWorker = new CSVFileWorker(file);
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

        var add1User = userService.addUser(newUsers.get(0));
        var addUsers = userService.addUsers(newUsers);
        var usersInFile = userService.getAllUsers();
        var deleteUser = userService.deleteUserById(userId2Delete);

        var user4Change = usersInFile.stream().findAny().get(); //TODO как isPresent красиво обрабатывать?
        user4Change.setName("ChangedName");
        var updateUser = userService.updateById(user4Change);
        var getUser = userService.getById(999L);

        var updateUnknownUser = userService.updateById(new User());

        System.out.println("add 1 user: " + add1User.toString());
        System.out.println("add users : " + addUsers.toString());
        System.out.println("all users: " + usersInFile.toString());
        System.out.println("delete user : " + deleteUser);
        System.out.println("update user : " + updateUser.toString());

    }
}
