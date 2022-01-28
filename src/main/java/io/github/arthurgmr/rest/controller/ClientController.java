package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.service.IClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    // private IClientRepository clientRepository;

    private final IClientService clientService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody @Valid Client dataClient) {
        Client client = clientService.saveClinet(dataClient);
        return client;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Client getClientById( @PathVariable UUID id ) {
        return clientService.getClient(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable @Valid UUID id,
                             @RequestBody Client dataClient) {
                                 clientService.updateClient(id, dataClient);
                             }


    @GetMapping("/find")
    public List<Client> findClient (Client filter) {
        return clientService.findClient(filter);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient (@PathVariable UUID id) {
        clientService.deleteClient(id);
    }
}
