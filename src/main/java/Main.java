import Util.CSVFileWorker;
import model.User;
import repository.UserRepository;
import service.UserService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVFileWorker csvFileWorker = new CSVFileWorker();
        UserRepository userRepository = new UserRepository(csvFileWorker);
        UserService userService = new UserService(userRepository);

//        var chuck = userService.addUser(User.builder()
//                .id(1L)
//                .name("Chuck")
//                .lastname("Norris")
//                .username("c.norris")
//                .build());
//
//        var bruce = userService.addUser(User.builder()
//                .id(2L)
//                .name("Bruce")
//                .lastname("Willis")
//                .username("b.willis")
//                .build());
//
//        var stallone = userService.addUser(User.builder()
//                .id(3L)
//                .name("Silvester")
//                .lastname("Stallone")
//                .username("s.stallone")
//                .build());

        System.out.println(csvFileWorker.readLine());
        System.out.println(csvFileWorker.readLine());
    }
}
