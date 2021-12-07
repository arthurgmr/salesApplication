package io.github.arthurgmr.service;

import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.rest.dto.OrderDTO;

public interface IOrderService {
    ClientOrder save(OrderDTO dataOrder);

    ClientOrder getOrder(Integer id);
}
