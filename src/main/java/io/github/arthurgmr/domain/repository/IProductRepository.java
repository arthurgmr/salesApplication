package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
