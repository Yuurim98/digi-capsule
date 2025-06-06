package io.github.Yuurim98.digi_capsule.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickName);

    Optional<UserEntity> findByEmail(String email);
}
