package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.repository.IClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/client")
public class ClientController {

    private IClientRepository clientRepository;

    public ClientController(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClientById( @PathVariable Integer id ) {
        Optional<Client> client =  clientRepository.findById(id);
        if(client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity saveClient(@RequestBody Client dataClient) {
        Client client = clientRepository.save(dataClient);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteClient (@PathVariable Integer id) {
        Optional<Client> client =  clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity updateClient(@PathVariable Integer id,
                                       @RequestBody Client clientUpdated) {
        return clientRepository
                .findById(id)
                .map(clientExists -> {
                    clientUpdated.setId(clientExists.getId());
                    clientRepository.save(clientUpdated);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
