package io.github.arthurgmr.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client_order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    //get items of client order;
    @OneToMany(mappedBy = "client_order")
    private List<ItemOrder> items;


    public List<ItemOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemOrder> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", total=" + total +
                '}';
    }
}
