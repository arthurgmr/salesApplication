package io.github.arthurgmr.service.implementation;

import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private ClientOrder orderRepository;

    public OrderServiceImpl(ClientOrder orderRepository) {
        this.orderRepository = orderRepository;
    }
}
