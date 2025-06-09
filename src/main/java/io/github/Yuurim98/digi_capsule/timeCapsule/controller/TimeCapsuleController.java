package io.github.Yuurim98.digi_capsule.timeCapsule.controller;

import io.github.Yuurim98.digi_capsule.common.exception.CustomException;
import io.github.Yuurim98.digi_capsule.common.exception.ErrorCode;
import io.github.Yuurim98.digi_capsule.common.response.ApiResponse;
import io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto.CreateCapsuleReqDto;
import io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto.ReadCapsulesResDto;
import io.github.Yuurim98.digi_capsule.timeCapsule.service.TimeCapsuleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/capsules")
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

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ReadCapsulesResDto>>> readMyCapsules(
        HttpServletRequest request) {

        return ResponseEntity.ok(ApiResponse.success("타임캡슐 목록이 조회되었습니다.",
            timeCapsuleService.readMyCapsules(getUserId(request.getSession(false)))));
    }

    private Long getUserId(HttpSession session) {
        if (session == null || session.getAttribute("userId") == null) {
            throw new CustomException(ErrorCode.NOT_LOGGED_IN);
        }
        return (Long) session.getAttribute("userId");
    }


}
