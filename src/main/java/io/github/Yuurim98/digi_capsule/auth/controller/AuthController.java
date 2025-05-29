package io.github.Yuurim98.digi_capsule.auth.controller;

import io.github.Yuurim98.digi_capsule.auth.controller.dto.EmailReqDto;
import io.github.Yuurim98.digi_capsule.auth.controller.dto.VerificationReqDto;
import io.github.Yuurim98.digi_capsule.auth.service.AuthService;
import io.github.Yuurim98.digi_capsule.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/verification-code")
    public ResponseEntity<ApiResponse<String>> sendVerificationCode(
        @Valid @RequestBody EmailReqDto emailReqDto) {
        authService.sendVerificationCode(emailReqDto.getEmail());
        return ResponseEntity.ok(ApiResponse.success("인증번호가 발송되었습니다."));
    }

    @PostMapping("/verification")
    public ResponseEntity<ApiResponse<String>> checkVerificationCode(@Valid @RequestBody
    VerificationReqDto verificationReqDto) {
        authService.checkVerificationCode(verificationReqDto);
        return ResponseEntity.ok(ApiResponse.success("인증이 완료되었습니다."));
    }
}
