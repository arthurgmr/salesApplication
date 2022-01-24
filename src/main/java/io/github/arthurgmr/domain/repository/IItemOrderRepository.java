package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.ItemOrder;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemOrderRepository extends JpaRepository<ItemOrder, UUID> {
}
