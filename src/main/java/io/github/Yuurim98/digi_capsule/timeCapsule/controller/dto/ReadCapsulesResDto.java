package io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadCapsulesResDto {

    private final String title;

    private final LocalDate viewDate;

}
