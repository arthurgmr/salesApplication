package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.repository.IClientRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private IClientRepository clientRepository;

    public ClientController(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Client getClientById( @PathVariable Integer id ) {
// CAN BE DONE THIS WAY
//        Optional<Client> client =  clientRepository.findById(id);
//        if(client.isPresent()) {
//            return client.get();
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");

// OR THIS ANOTHER WAY
        return  clientRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody @Valid Client dataClient) {
        Client client = clientRepository.save(dataClient);
        return client;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient (@PathVariable Integer id) {
        Optional<Client> client =  clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.delete(client.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable @Valid Integer id,
                             @RequestBody Client clientUpdated) {
        clientRepository
                .findById(id)
                .map(clientExists -> {
                    clientUpdated.setId(clientExists.getId());
                    clientRepository.save(clientUpdated);
                    return clientExists;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
    }

    @GetMapping("/find")
    public List<Client> findClient (Client filter) {
        // creating matcher to find Client;
        // using ExampleMatcher of SpringFramework;
        ExampleMatcher matcher = ExampleMatcher
                                    .matching() // execute the match;
                                    .withIgnoreCase() //ignore letter case;
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // matching with containing words;
        // set information of filter with matcher config;
        Example example = Example.of(filter, matcher);

        List<Client> result = clientRepository.findAll(example);
        return result;

    }
}
