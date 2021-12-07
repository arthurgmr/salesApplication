package io.github.arthurgmr.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDTO {
    private Integer code;
    private String nameClient;
    private String cpf;
    private BigDecimal total;
    private List<ResponseItemOrderDTO> items;

}
