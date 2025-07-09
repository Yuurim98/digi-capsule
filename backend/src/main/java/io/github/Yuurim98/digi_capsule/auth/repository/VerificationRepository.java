package io.github.Yuurim98.digi_capsule.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationEntity, Long> {

    Optional<VerificationEntity> findTopByEmailOrderByCreatedAtDesc(String email);
}
