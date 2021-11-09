package io.github.arthurgmr.domain.entity;

import java.time.LocalDate;

public class ItemOrder {
    private Integer id;
    private ClientOrder client_order_id;
    private Product product_id;
    private Integer quantity;
    private LocalDate created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientOrder getClient_order_id() {
        return client_order_id;
    }

    public void setClient_order_id(ClientOrder client_order_id) {
        this.client_order_id = client_order_id;
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
