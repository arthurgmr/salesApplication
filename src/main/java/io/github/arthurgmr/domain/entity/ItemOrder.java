package io.github.arthurgmr.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "item_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_order_id")
    private ClientOrder client_order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    @Column
    private Integer quantity;

    @Column
    private LocalDate created_at;

}
