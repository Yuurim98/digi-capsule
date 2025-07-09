package io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto;

import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleEntity;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class ReadCapsulesResDto {

    private String title;

    private LocalDate viewDate;

    private ReadCapsulesResDto(String title, LocalDate viewDate) {
        this.title = title;
        this.viewDate = viewDate;
    }

    public static ReadCapsulesResDto from(TimeCapsuleEntity timeCapsule) {
        return new ReadCapsulesResDto(timeCapsule.getTitle(), timeCapsule.getViewDate());
    }

}
