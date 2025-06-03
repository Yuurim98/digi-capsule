package io.github.Yuurim98.digi_capsule.user.repository;

import io.github.Yuurim98.digi_capsule.common.entity.BaseEntity;
import io.github.Yuurim98.digi_capsule.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String phoneNumber;


    @Builder
    private UserEntity(String nickName, String email, String password, String phoneNumber) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public static UserEntity from(User user) {
        return UserEntity.builder()
            .nickName(user.getNickName())
            .email(user.getEmail())
            .password(user.getPassword())
            .phoneNumber(user.getPhoneNumber())
            .build();
    }

    public User toDomain() {
        return User.from(this.id, this.nickName, this.email, this.password, this.phoneNumber);
    }

}
