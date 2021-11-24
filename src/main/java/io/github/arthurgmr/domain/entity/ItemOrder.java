package io.github.arthurgmr.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "item_order")
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientOrder getClient_order_id() {
        return client_order;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
