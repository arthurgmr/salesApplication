package io.github.arthurgmr.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.arthurgmr.domain.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    
    Optional<UserEntity> findByLogin(String login);

}
