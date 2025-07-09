package io.github.Yuurim98.digi_capsule.auth.service;

import io.github.Yuurim98.digi_capsule.auth.controller.dto.LoginReqDto;
import io.github.Yuurim98.digi_capsule.auth.controller.dto.RegisterReqDto;
import io.github.Yuurim98.digi_capsule.auth.controller.dto.VerificationReqDto;
import io.github.Yuurim98.digi_capsule.auth.domain.Verification;
import io.github.Yuurim98.digi_capsule.auth.generator.VerificationCodeGenerator;
import io.github.Yuurim98.digi_capsule.auth.repository.VerificationEntity;
import io.github.Yuurim98.digi_capsule.auth.repository.VerificationRepository;
import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import io.github.Yuurim98.digi_capsule.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final VerificationCodeGenerator verificationCodeGenerator;
    private final EmailService emailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
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
        VerificationEntity verificationEntity = getVerificationEntity(
            verificationReqDto.getEmail());

        Verification verification = getVerification(verificationEntity);

        try {
            verification.verifyCode(verificationReqDto.getVerificationCode());
        } finally {
            verificationEntity.updateVerificationStatus(verification.getVerificationStatus());
        }

    }

    @Transactional
    public void register(RegisterReqDto registerReqDto) {
        VerificationEntity verificationEntity = getVerificationEntity(registerReqDto.getEmail());

        Verification verification = getVerification(verificationEntity);

        if (!verification.isVerified()) {
            throw new CustomException(ErrorCode.NOT_VERIFIED);
        }

        userService.createUser(registerReqDto, getEncodedPassword(registerReqDto));
    }

    private static Verification getVerification(VerificationEntity verificationEntity) {
        return verificationEntity.toDomain();
    }

    private VerificationEntity getVerificationEntity(String email) {
        return verificationRepository.findTopByEmailOrderByCreatedAtDesc(email)
            .orElseThrow(() -> new CustomException(ErrorCode.VERIFICATION_NOT_FOUND));
    }

    private String getEncodedPassword(RegisterReqDto registerReqDto) {
        return passwordEncoder.encode(registerReqDto.getPassword());
    }

    public Long login(LoginReqDto loginReqDto) {
        if (!userService.userExistsByEmail(loginReqDto.getEmail())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        String encodedPassword = userService.getEncodedPasswordByEmail(loginReqDto.getEmail());

        if (!passwordEncoder.matches(loginReqDto.getPassword(), encodedPassword)) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        return userService.getUserIdByEmail(loginReqDto.getEmail());
    }
}
