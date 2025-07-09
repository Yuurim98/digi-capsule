package io.github.Yuurim98.digi_capsule.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginReqDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "최소 8자 이상, 15자 이하로 a-z, A-Z, 0-9 만 입력하세요.")
    private String password;
}
