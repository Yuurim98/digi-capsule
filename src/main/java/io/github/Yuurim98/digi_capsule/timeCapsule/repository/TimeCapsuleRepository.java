package io.github.Yuurim98.digi_capsule.timeCapsule.repository;

import io.github.Yuurim98.digi_capsule.user.repository.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCapsuleRepository extends JpaRepository<TimeCapsuleEntity, Long> {

    List<TimeCapsuleEntity> findByUser(UserEntity user);
}
