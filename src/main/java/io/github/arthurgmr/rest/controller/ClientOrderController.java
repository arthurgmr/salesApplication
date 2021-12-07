package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.rest.dto.OrderDTO;
import io.github.arthurgmr.service.IOrderService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/order")
public class ClientOrderController {

    private IOrderService service;

    public ClientOrderController(IOrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer saveOrder(@RequestBody OrderDTO dataOrder) {
        ClientOrder order = service.save(dataOrder);
        return order.getId();
    }



}
