package io.github.arthurgmr.service;

import java.util.UUID;

import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.domain.enums.StatusOrder;
import io.github.arthurgmr.rest.dto.OrderDTO;
import io.github.arthurgmr.rest.dto.ResponseOrderDTO;

public interface IOrderService {
    ClientOrder save(OrderDTO dataOrder);

    ResponseOrderDTO getOrder(UUID id);

    void changeStatusOrder(UUID id, StatusOrder status);


}
