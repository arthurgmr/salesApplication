package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.domain.enums.StatusOrder;
import io.github.arthurgmr.rest.dto.ChangeOrderStatusDTO;
import io.github.arthurgmr.rest.dto.OrderDTO;
import io.github.arthurgmr.rest.dto.ResponseOrderDTO;
import io.github.arthurgmr.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class ClientOrderController {

    private IOrderService service;

    public ClientOrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UUID saveOrder(@RequestBody @Valid OrderDTO dataOrder) {
        ClientOrder order = service.save(dataOrder);
        return order.getId();
    }

    @GetMapping("/{id}")
    public ResponseOrderDTO getOrder(@PathVariable UUID id) {
        ResponseOrderDTO order = service.getOrder(id);
        return order;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void changeStatusOrder(@PathVariable UUID id,
                                  @RequestBody ChangeOrderStatusDTO statusOrder) {
        String newStatus = statusOrder.getNewStatus();
        service.changeStatusOrder(id, StatusOrder.valueOf(newStatus));
    }
}
