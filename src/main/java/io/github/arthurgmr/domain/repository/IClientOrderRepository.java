package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientOrderRepository extends JpaRepository<ClientOrder, Integer> {
    //Create method to return all orders of one client;
    List<ClientOrder> findByClient(Client client);
}
