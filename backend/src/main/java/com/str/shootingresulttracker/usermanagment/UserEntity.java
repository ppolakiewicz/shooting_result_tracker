package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.kernel.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "srt_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class UserEntity extends AbstractBaseEntity {

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public UserRole role;

    @Column(name = "active")
    public boolean active;

    public UserEntity(String username, String password, UserRole role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public static UserEntity newBasicUser(String username, String password) {
        return new UserEntity(
                username,
                password,
                UserRole.USER,
                true
        );
    }
}
