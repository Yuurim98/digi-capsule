package io.github.Yuurim98.digi_capsule.timeCapsule.mapper;

import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import io.github.Yuurim98.digi_capsule.timeCapsule.domain.TimeCapsule;
import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleEntity;
import io.github.Yuurim98.digi_capsule.user.repository.UserEntity;
import io.github.Yuurim98.digi_capsule.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeCapsuleMapper {

    private final UserRepository userRepository;

    public TimeCapsuleEntity toEntity(TimeCapsule capsule) {
        UserEntity userEntity = userRepository.findById(capsule.getUser().getId())
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return TimeCapsuleEntity.builder()
            .title(capsule.getTitle())
            .content(capsule.getContent())
            .viewDate(capsule.getViewDate())
            .isEmailNotificationEnabled(capsule.isEmailNotificationEnabled())
            .user(userEntity)
            .build();
    }
}
