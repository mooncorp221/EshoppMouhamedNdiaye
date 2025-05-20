package com.ndiaye.user.repository;

import com.ndiaye.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(@NotBlank(message = "Le nom d'utilisateur est obligatoire") String username);
}
