package io.github.Yuurim98.digi_capsule.timeCapsule.service;

import io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto.CreateCapsuleReqDto;
import io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto.ReadCapsulesResDto;
import io.github.Yuurim98.digi_capsule.timeCapsule.domain.TimeCapsule;
import io.github.Yuurim98.digi_capsule.timeCapsule.mapper.TimeCapsuleMapper;
import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleEntity;
import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleRepository;
import io.github.Yuurim98.digi_capsule.user.domain.User;
import io.github.Yuurim98.digi_capsule.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TimeCapsuleService {

    private final TimeCapsuleRepository timeCapsuleRepository;
    private final TimeCapsuleMapper timeCapsuleMapper;
    private final UserService userService;

    @Transactional
    public void createCapsule(CreateCapsuleReqDto capsuleReqDto, Long userId) {

        // 작성자를 가져온다
        User user = userService.findUserByIdOrThrow(userId);

        // 타임캡슐 생성 후 DB 저장한다
        TimeCapsule capsuleDomain = TimeCapsule.create(capsuleReqDto.getTitle(),
            capsuleReqDto.getContent(),
            capsuleReqDto.getViewDate(),
            capsuleReqDto.isEmailNotificationEnabled(), user);

        TimeCapsuleEntity entity = timeCapsuleMapper.toEntity(capsuleDomain);
        timeCapsuleRepository.save(entity);
    }

    public List<ReadCapsulesResDto> readMyCapsules(Long userId, Pageable pageable) {
        Page<TimeCapsuleEntity> timeCapsules = timeCapsuleRepository.findByUser(
            userService.findUserEntityByIdOrThrow(userId), pageable);
        return timeCapsules.stream()
            .map(entity -> {
                return new ReadCapsulesResDto(entity.getTitle(), entity.getViewDate());
            })
            .collect(Collectors.toList());
    }
}
