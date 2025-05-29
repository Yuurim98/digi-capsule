package io.github.Yuurim98.digi_capsule.auth.service;

import io.github.Yuurim98.digi_capsule.auth.controller.dto.VerificationReqDto;
import io.github.Yuurim98.digi_capsule.auth.domain.Verification;
import io.github.Yuurim98.digi_capsule.auth.generator.VerificationCodeGenerator;
import io.github.Yuurim98.digi_capsule.auth.repository.VerificationEntity;
import io.github.Yuurim98.digi_capsule.auth.repository.VerificationRepository;
import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final VerificationCodeGenerator verificationCodeGenerator;
    private final EmailService emailService;
    private final VerificationRepository verificationRepository;

    @Transactional
    public void sendVerificationCode(String email) {
        String verificationCode = verificationCodeGenerator.generateVerificationCode();
        emailService.sendVerificationEmail(email, verificationCode);

        Verification verification = Verification.create(email, verificationCode);
        VerificationEntity verificationEntity = VerificationEntity.from(verification);

        verificationRepository.save(verificationEntity);
    }

    @Transactional(noRollbackFor = CustomException.class)
    public void checkVerificationCode(VerificationReqDto verificationReqDto) {
        VerificationEntity verificationEntity = verificationRepository.findTopByEmailOrderByCreatedAtDesc(
                verificationReqDto.getEmail())
            .orElseThrow(() -> new CustomException(ErrorCode.VERIFICATION_NOT_FOUND));

        Verification verification = verificationEntity.toDomain();

        try {
            verification.verifyCode(verificationReqDto.getVerificationCode());
        } finally {
            verificationEntity.updateVerificationStatus(verification.getVerificationStatus());
        }

    }
}
