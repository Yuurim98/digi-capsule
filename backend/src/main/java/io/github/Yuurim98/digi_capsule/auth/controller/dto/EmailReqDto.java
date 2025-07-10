package io.github.Yuurim98.digi_capsule.auth.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailReqDto {

    @Email
    @NotBlank
    private String email;
}
