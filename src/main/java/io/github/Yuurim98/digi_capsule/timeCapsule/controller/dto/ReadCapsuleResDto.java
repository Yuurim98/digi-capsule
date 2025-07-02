package io.github.Yuurim98.digi_capsule.timeCapsule.controller.dto;

import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleEntity;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class ReadCapsuleResDto {

    private String title;

    private String content;

    private LocalDate viewDate;

    private ReadCapsuleResDto(String title, String content, LocalDate viewDate) {
        this.title = title;
        this.content = content;
        this.viewDate = viewDate;
    }

    public static ReadCapsuleResDto from(TimeCapsuleEntity timeCapsule) {
        return new ReadCapsuleResDto(timeCapsule.getTitle(), timeCapsule.getContent(),
            timeCapsule.getViewDate());
    }
}
