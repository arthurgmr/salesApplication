package io.github.arthurgmr.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //
public class ResponseOrderDTO {
    private UUID code;
    private String nameClient;
    private String cpf;
    private String status;
    private String date;
    private BigDecimal total;
    private List<ResponseItemOrderDTO> items;

}
