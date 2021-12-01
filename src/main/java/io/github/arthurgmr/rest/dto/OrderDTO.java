package io.github.arthurgmr.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Integer client_id;
    private BigDecimal total;
    private List<ItemOrderDTO> items;
}
