package com.vueart.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vueart.api.core.enums.Code;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "business", length = 1)
    @Enumerated(EnumType.STRING)
    private Code.YN business;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "region")
    private String region;

    @Enumerated(EnumType.STRING)
    private Code.Role role;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    public User updatePassword(Long id, String password) {
        return User.builder()
                .id(id)
                .email(this.email)
                .userId(this.userId)
                .password(password)
                .build();
    }
}
