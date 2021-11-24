package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
}
