package io.github.Yuurim98.digi_capsule.timeCapsule.domain;

import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleEntity;
import io.github.Yuurim98.digi_capsule.user.domain.User;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeCapsule {

    private String title;
    private String content;
    private LocalDate viewDate;
    private boolean isEmailNotificationEnabled;
    private User user;

    public static TimeCapsule create(String title, String content, LocalDate viewDate,
        boolean isEmailNotificationEnabled, User user) {
        return new TimeCapsule(title, content, viewDate, isEmailNotificationEnabled, user);
    }

    public static TimeCapsule from(TimeCapsuleEntity entity) {
        return new TimeCapsule(entity.getTitle(), entity.getContent(), entity.getViewDate(),
            entity.isEmailNotificationEnabled(), entity.getUser().toDomain());
    }

    public boolean isOwnedBy(User user) {
        return this.user.getId().equals(user.getId());
    }

    public boolean isViewable() {
        return LocalDate.now().isAfter(this.viewDate);
    }
}
