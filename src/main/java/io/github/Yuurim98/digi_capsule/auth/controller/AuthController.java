package io.github.Yuurim98.digi_capsule.auth.controller;

import io.github.Yuurim98.digi_capsule.auth.controller.dto.EmailReqDto;
import io.github.Yuurim98.digi_capsule.auth.controller.dto.LoginReqDto;
import io.github.Yuurim98.digi_capsule.auth.controller.dto.RegisterReqDto;
import io.github.Yuurim98.digi_capsule.auth.controller.dto.VerificationReqDto;
import io.github.Yuurim98.digi_capsule.auth.service.AuthService;
import io.github.Yuurim98.digi_capsule.common.response.ApiResponse;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterReqDto registerReqDto) {
        authService.register(registerReqDto);
        return ResponseEntity.ok(ApiResponse.success("회원가입 되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginReqDto loginReqDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", authService.login(loginReqDto));

        return ResponseEntity.ok(ApiResponse.success("로그인 되었습니다."));
    }
}
