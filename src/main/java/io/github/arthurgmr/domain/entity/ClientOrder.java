package io.github.arthurgmr.domain.entity;

import io.github.arthurgmr.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    //get client data
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "created_at")
    private LocalDate created_at;

    //save total quantity with format => 1000.00;
    //precision is the length number;
    //scale is the quantity decimal house;
    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOrder status;

    //get items of client order;
    @OneToMany(mappedBy = "client_order")
    private List<ItemOrder> items;
}
