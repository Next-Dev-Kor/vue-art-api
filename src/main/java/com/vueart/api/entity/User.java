package com.vueart.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // JPA에서 lazy관련 에러 날 경우 사용
@Entity  // 객체와 테이블 매핑
@Table(name = "users")
public class User extends BaseEntity{
    @Id  // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT 설정 (id값이 null일 경우 자동 생성)
    @Column(name = "id")  // 컬럼 지정
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "email")
    private String email;

    @Builder
    public User(Long id, String password, String userId, String email){
        this.id = id;
        this.password = password;
        this.userId = userId;
        this.email = email;
    }

    public User updatePassword(Long id, String password){
        return User.builder()
                .id(this.id)
                .email(this.email)
                .userId(this.userId)
                .password(password)
                .build();
    }
}
