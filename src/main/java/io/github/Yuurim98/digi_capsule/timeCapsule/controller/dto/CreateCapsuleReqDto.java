package io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class CreateCapsuleReqDto {

    @Size(max = 255)
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDate viewDate;

    @NotNull
    private boolean emailNotificationEnabled;

}
