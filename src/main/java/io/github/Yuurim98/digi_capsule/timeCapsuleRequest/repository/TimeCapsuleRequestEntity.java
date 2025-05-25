package io.github.Yuurim98.digi_capsule.timeCapsuleRequest.repository;

import io.github.Yuurim98.digi_capsule.common.entity.BaseEntity;
import io.github.Yuurim98.digi_capsule.timeCapsule.repository.TimeCapsuleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeCapsuleRequestEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String recipientPhoneNumber;

    @Column(nullable = false)
    private boolean isAnonymousSender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_capsules_id", unique = true)
    private TimeCapsuleEntity timeCapsule;
}
