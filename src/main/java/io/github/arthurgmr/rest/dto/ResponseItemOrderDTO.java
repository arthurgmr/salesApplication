package io.github.arthurgmr.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseItemOrderDTO {
    private String productDescription;
    private BigDecimal unit_price;
    private Integer quantity;
}
