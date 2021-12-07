package io.github.arthurgmr.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private Integer client_id;
    private BigDecimal total;
    private List<ItemOrderDTO> items;
}
