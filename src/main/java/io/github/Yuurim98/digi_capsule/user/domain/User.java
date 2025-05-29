package io.github.Yuurim98.digi_capsule.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private String nickName;

    private String email;

    private String password;

    private String phoneNumber;

    public static User create(String nickName, String email, String password, String phoneNumber) {
        return new User(nickName, email, password, phoneNumber);
    }
}
