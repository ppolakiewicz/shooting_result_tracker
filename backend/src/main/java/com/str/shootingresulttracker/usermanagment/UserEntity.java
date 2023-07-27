package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.kernel.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.JsonJdbcType;

import java.util.Collection;
import java.util.List;

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
    @JdbcTypeCode(SqlTypes.JSON)
    public Collection<UserRole> roles;

    @Column(name = "active")
    public boolean active;

    public UserEntity(String username, String password, Collection<UserRole> roles, boolean active) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public static UserEntity newBasicUser(String username, String password) {
        return new UserEntity(
                username,
                password,
                List.of(UserRole.USER),
                true
        );
    }
}
