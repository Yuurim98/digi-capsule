package io.github.Yuurim98.digi_capsule.auth.domain;

import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Verification {

    private String email;

    private String verificationCode;

    private VerificationStatus verificationStatus;

    private LocalDateTime expiredAt;

    public static Verification create(String email, String verificationCode) {
        LocalDateTime now = LocalDateTime.now();

        return new Verification(email, verificationCode, VerificationStatus.PENDING,
            now.plusMinutes(5));
    }

    public static Verification from(String email, String verificationCode,
        VerificationStatus verificationStatus,
        LocalDateTime expiredAt) {
        return new Verification(email, verificationCode, verificationStatus, expiredAt);
    }

    public void verifyCode(String verificationCode) {
        // 코드가 일치하는지
        checkCodeMatches(verificationCode);

        // 만료시간이 지났는지
        checkExpiration();

        verificationStatus = VerificationStatus.VERIFIED;
    }

    private void checkCodeMatches(String verificationCode) {
        if (!this.verificationCode.equals(verificationCode)) {
            throw new CustomException(ErrorCode.VERIFICATION_CODE_MISMATCH);
        }
    }

    private void checkExpiration() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(expiredAt)) {
            verificationStatus = VerificationStatus.EXPIRED;
            throw new CustomException(ErrorCode.VERIFICATION_EXPIRED);
        }
    }
}
