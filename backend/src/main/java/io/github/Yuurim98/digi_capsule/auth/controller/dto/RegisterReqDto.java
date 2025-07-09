package io.github.Yuurim98.digi_capsule.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterReqDto {

    @Size(min = 3, max = 20)
    @NotBlank
    private String nickName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "최소 8자 이상, 15자 이하로 a-z, A-Z, 0-9 만 입력하세요.")
    private String password;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "하이픈을 포함한 휴대폰 번호를 입력하세요.")
    private String phoneNumber;
}
