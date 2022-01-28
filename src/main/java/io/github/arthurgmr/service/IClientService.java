package io.github.arthurgmr.service;

import java.util.List;
import java.util.UUID;

import io.github.arthurgmr.domain.entity.Client;

public interface IClientService {

    Client saveClinet(Client dataClinet);

    Client getClient(UUID id);

    void updateClient(UUID id, Client dataClient);

    List<Client> findClient(Client filter);

    void deleteClient(UUID id);

}
