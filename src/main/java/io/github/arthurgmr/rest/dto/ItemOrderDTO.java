package io.github.arthurgmr.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemOrderDTO {
    private Integer product_id;
    private Integer quantity;
}
