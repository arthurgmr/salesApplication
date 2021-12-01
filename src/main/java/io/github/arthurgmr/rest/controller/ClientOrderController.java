package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class ClientOrderController {

    private OrderService service;

    public ClientOrderController(OrderService service) {
        this.service = service;
    }
}
