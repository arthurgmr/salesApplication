package io.github.arthurgmr.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String name;

    //get all client orders;
    //can use "fetch" to get always client data;
    //fetch = FetchType.EAGER, default is LAZY;
    @OneToMany( mappedBy = "client")
    private Set<ClientOrder> client_orders;

    public Client() {
    }

    public Set<ClientOrder> getClient_orders() {
        return client_orders;
    }

    public void setClient_orders(Set<ClientOrder> client_orders) {
        this.client_orders = client_orders;
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
