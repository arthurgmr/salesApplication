package io.github.arthurgmr.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    @NotEmpty(message = "{description.required}")
    private String description;

    @Column(name = "unit_price")
    @NotNull(message = "{price.required}")
    private BigDecimal unit_price;
}
