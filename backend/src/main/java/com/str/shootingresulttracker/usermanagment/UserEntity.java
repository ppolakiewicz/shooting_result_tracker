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

@Getter
@Entity
@Table(name = "srt_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends AbstractBaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "permission")
    @JdbcTypeCode(SqlTypes.JSON)
    private Collection<UserPermission> permissions;

    @Column(name = "active")
    private boolean active;

    public UserEntity(String username, String password, Collection<UserPermission> permissions, boolean active, Clock clock) {
        super(clock);
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        this.active = active;
    }

    public static UserEntity newBasicUser(String username, String password, Clock clock) {
        return new UserEntity(
                username,
                password,
                UserPermission.DEFAULT_PERMISSIONS,
                true,
                clock
        );
    }
}
