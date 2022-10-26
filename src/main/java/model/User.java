package model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String name;

    private String lastname;

    private String username;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id)
                .append(";")
                .append(name)
                .append(";")
                .append(lastname)
                .append(";")
                .append(username);

        return stringBuilder.toString();
    }

    public static User scvLine2User(String csvLine){    // TODO так надо преобразовывать в объект строку csv ?
        User user = new User();
        String[] userArray = csvLine.split(";");

        user.setId(Long.parseLong(userArray[0]));
        user.setName(userArray[1]);
        user.setLastname(userArray[2]);
        user.setUsername(userArray[3]);

        return user;
    }

}
