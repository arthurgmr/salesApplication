package io.github.arthurgmr.service;

import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.domain.enums.StatusOrder;
import io.github.arthurgmr.rest.dto.OrderDTO;
import io.github.arthurgmr.rest.dto.ResponseOrderDTO;

public interface IOrderService {
    ClientOrder save(OrderDTO dataOrder);

    ResponseOrderDTO getOrder(Integer id);

    void changeStatusOrder(Integer id, StatusOrder status);


}
