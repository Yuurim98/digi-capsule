package io.github.Yuurim98.digi_capsule.auth.repository;

import io.github.Yuurim98.digi_capsule.auth.domain.Verification;
import io.github.Yuurim98.digi_capsule.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 6)
    private String verificationCode;

    @Column(nullable = false)
    private int verificationAttempt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Builder
    private VerificationEntity(String email, String verificationCode, int verificationAttempt,
        LocalDateTime expiredAt) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.verificationAttempt = verificationAttempt;
        this.expiredAt = expiredAt;
    }

    public static VerificationEntity from(Verification verification) {
        return VerificationEntity.builder()
            .email(verification.getEmail())
            .verificationCode(verification.getVerificationCode())
            .verificationAttempt(verification.getVerificationAttempt())
            .expiredAt(verification.getExpiredAt())
            .build();
    }

    public Verification toDomain() {
        return Verification.from(this.email, this.verificationCode, this.verificationAttempt,
            this.expiredAt);
    }
}
