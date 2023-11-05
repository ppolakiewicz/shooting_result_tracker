package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.core.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Clock;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@Table(name = "srt_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class UserEntity extends AbstractBaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @JdbcTypeCode(SqlTypes.JSON)
    private Collection<UserRole> roles;

    @Column(name = "active")
    private boolean active;

    public UserEntity(String username, String password, Collection<UserRole> roles, boolean active, Clock clock) {
        super(clock);
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public static UserEntity newBasicUser(String username, String password, Clock clock) {
        return new UserEntity(
                username,
                password,
                List.of(UserRole.USER),
                true,
                clock
        );
    }
}
