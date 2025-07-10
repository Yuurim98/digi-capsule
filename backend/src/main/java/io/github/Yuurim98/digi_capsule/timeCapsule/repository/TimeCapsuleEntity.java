package io.github.Yuurim98.digi_capsule.timeCapsule.repository;

import io.github.Yuurim98.digi_capsule.common.entity.BaseEntity;
import io.github.Yuurim98.digi_capsule.user.repository.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeCapsuleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    //Todo: 추후 첨부파일 추가

    @Column(nullable = false)
    private LocalDate viewDate;

    @Column(nullable = false)
    private boolean isEmailNotificationEnabled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    private TimeCapsuleEntity(String title, String content, LocalDate viewDate, boolean isEmailNotificationEnabled, UserEntity user) {
        this.title = title;
        this.content = content;
        this.viewDate = viewDate;
        this.isEmailNotificationEnabled = isEmailNotificationEnabled;
        this.user = user;
    }

}
