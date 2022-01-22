package io.github.arthurgmr.rest.dto;

import io.github.arthurgmr.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    @NotNull(message = "{client.required}")
    private UUID client_id;

    @NotNull(message = "{total.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{items.required}")
    private List<ItemOrderDTO> items;
}
