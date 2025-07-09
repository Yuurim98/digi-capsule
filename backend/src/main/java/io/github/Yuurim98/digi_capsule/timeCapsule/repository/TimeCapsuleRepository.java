package io.github.Yuurim98.digi_capsule.timeCapsule.repository;

import io.github.Yuurim98.digi_capsule.user.repository.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCapsuleRepository extends JpaRepository<TimeCapsuleEntity, Long> {

    Page<TimeCapsuleEntity> findByUser(UserEntity user, Pageable pageable);
}
