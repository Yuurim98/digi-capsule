package io.github.Yuurim98.digi_capsule.timeCapsule.controller;

import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import io.github.Yuurim98.digi_capsule.common.response.ApiResponse;
import io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto.CreateCapsuleReqDto;
import io.github.Yuurim98.digi_capsule.timeCapsule.service.TimeCapsuleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/capsule")
public class TimeCapsuleController {

    private final TimeCapsuleService timeCapsuleService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> createCapsule(
        @Valid @RequestBody CreateCapsuleReqDto capsuleReqDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            throw new CustomException(ErrorCode.NOT_LOGGED_IN);
        }
        Long userId = (Long) session.getAttribute("userId");

        timeCapsuleService.createCapsule(capsuleReqDto, userId);
        return ResponseEntity.ok(ApiResponse.success("타임캡슐이 생성되었습니다."));
    }


}
