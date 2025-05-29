package io.github.Yuurim98.digi_capsule.user.service;

import io.github.Yuurim98.digi_capsule.auth.controller.dto.RegisterReqDto;
import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import io.github.Yuurim98.digi_capsule.user.domain.User;
import io.github.Yuurim98.digi_capsule.user.repository.UserEntity;
import io.github.Yuurim98.digi_capsule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(RegisterReqDto registerReqDto, String encodedPassword) {
        validateDuplicateEmail(registerReqDto.getEmail());

        validateDuplicateNickName(registerReqDto.getNickName());

        User user = User.create(registerReqDto.getNickName(), registerReqDto.getEmail(),
            encodedPassword, registerReqDto.getPhoneNumber());

        userRepository.save(UserEntity.from(user));
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.EMAIL_CONFLICT);
        }
    }

    private void validateDuplicateNickName(String nickName) {
        if (userRepository.existsByNickName(nickName)) {
            throw new CustomException(ErrorCode.NICKNAME_CONFLICT);
        }
    }
}
