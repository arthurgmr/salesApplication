package io.github.arthurgmr.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    @NotEmpty(message = "{description.required}")
    private String description;

    @Column(name = "unit_price", precision = 20, scale = 2)
    @NotNull(message = "{price.required}")
    private BigDecimal unit_price;

    @Column
    @CreationTimestamp
    private LocalDate created_at;

    @Column
    @UpdateTimestamp
    private LocalDate updated_at;
}
