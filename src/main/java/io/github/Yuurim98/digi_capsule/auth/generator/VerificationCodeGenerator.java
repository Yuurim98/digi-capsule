package io.github.Yuurim98.digi_capsule.auth.generator;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeGenerator {

    private static final int CODE_LENGTH = 6;
    private static final Random random = new Random();

    public String generateVerificationCode() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < CODE_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

}
