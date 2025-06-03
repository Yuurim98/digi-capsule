package io.github.Yuurim98.digi_capsule.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    VERIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "인증번호를 발송하세요."),
    VERIFICATION_EXPIRED(HttpStatus.GONE, "인증 시간이 만료되었습니다."),
    VERIFICATION_CODE_MISMATCH(HttpStatus.BAD_REQUEST, "인증 번호가 일치하지 않습니다."),
    NOT_VERIFIED(HttpStatus.BAD_REQUEST, "이메일 인증을 먼저 진행해 주세요."),
    EMAIL_CONFLICT(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    NICKNAME_CONFLICT(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
