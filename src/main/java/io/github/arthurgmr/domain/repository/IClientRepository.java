package io.github.arthurgmr.domain.repository;

import io.github.arthurgmr.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    //Create automatic method through the name;
    List<Client> findByNameLike (String name);

    //Create method with JPQL query;
    @Query(value = "select client from Client client where client.name like :name")
    List<Client> filterNamesJPQL (@Param("name") String name);

    //Create method with SLQ query;
    @Query(value = "select * from Client client where client.name like '%:name%'", nativeQuery = true)
    List<Client> filterNamesSQL (@Param("name") String name);

    //Create method that modifies data in db;
    @Query(value = "delete from Client client where client.name = :name")
    @Modifying
    void deleteForExample(String name);
}
