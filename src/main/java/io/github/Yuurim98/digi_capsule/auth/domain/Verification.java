package io.github.Yuurim98.digi_capsule.auth.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Verification {

    private String email;

    private String verificationCode;

    private int verificationAttempt;

    private LocalDateTime expiredAt;

    public static Verification create(String email, String verificationCode) {
        LocalDateTime now = LocalDateTime.now();

        return new Verification(email, verificationCode, 0, now.plusMinutes(5));
    }
}
