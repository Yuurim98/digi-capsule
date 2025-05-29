package io.github.Yuurim98.digi_capsule.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VerificationStatus {

    PENDING("대기"),
    VERIFIED("인증"),
    EXPIRED("만료");

    private final String description;
}
