package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClientOrderRepository extends JpaRepository<ClientOrder, UUID> {
    //Create method to return all orders of one client;
    List<ClientOrder> findByClient(Client client);

    //Crate method to return an order with items;
    //This case, was use HQL Query;
    @Query(" select o from ClientOrder o left join fetch o.items where o.id = :id ")
    Optional<ClientOrder> findByIdFetchItems(@Param("id") UUID id);

    
}
