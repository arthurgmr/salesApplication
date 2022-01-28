package io.github.arthurgmr.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.repository.IClientRepository;
import io.github.arthurgmr.service.IClientService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client saveClinet(Client dataClinet) {
        Client clientExists = clientRepository.findByCpf(dataClinet.getCpf());

        if(clientExists != null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Client already exists!");
        }

        Client clientSaved = clientRepository.save(dataClinet);

        return clientSaved;
    }

    @Override
    public Client getClient(UUID id) {
    // CAN BE DONE THIS WAY
    //    Optional<Client> client =  clientRepository.findById(id);
    //    if(client.isPresent()) {
    //        return client.get();
    //    }
    //    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");

        return  clientRepository
                    .findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
    }

    @Override
    public void updateClient(UUID id, Client dataClient) {
        clientRepository
                    .findById(id)
                    .map(clientExists -> {
                        dataClient.setId(clientExists.getId());

                        Client cpfExists = clientRepository.findByCpf(dataClient.getCpf());
                        if(cpfExists != null && cpfExists.getCpf() != clientExists.getCpf()) {
                            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Client already exists!");  
                        }

                        clientRepository.save(dataClient);
                        return clientExists;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
        
    }

    @Override
    public List<Client> findClient(Client filter) {
        // creating matcher to find Client;
        // using ExampleMatcher of SpringFramework;
        ExampleMatcher matcher = ExampleMatcher
                                    .matching() // execute the match;
                                    .withIgnoreCase() //ignore letter case;
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // matching with containing words;
        // set information of filter with matcher config;
        Example<Client> example = Example.of(filter, matcher);

        List<Client> result = clientRepository.findAll(example);
        return result;
    }

    @Override
    public void deleteClient(UUID id) {
        Optional<Client> client =  clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.delete(client.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");
        }
        
    }
    
}
