package io.github.arthurgmr.rest.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemOrderDTO {
    private UUID product_id;
    private Integer quantity;
}
