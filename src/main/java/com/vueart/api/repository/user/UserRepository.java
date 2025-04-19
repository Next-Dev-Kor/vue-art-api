package com.vueart.api.repository.user;

import com.vueart.api.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, CustomQuerydslUserRepository{
    Optional<User> findByEmail(@NotNull String email);
}
