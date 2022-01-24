package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.Product;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(UUID id);
}
