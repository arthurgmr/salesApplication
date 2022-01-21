package io.github.arthurgmr.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;

    @Column
    @NotEmpty(message = "{name.required}")
    private String name;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{cpf.required}")
    @CPF(message = "{cpf.invalid}")
    private String cpf;

    //get all client orders;
    //can use "fetch" to get always client data;
    //fetch = FetchType.EAGER, default is LAZY;
    @JsonIgnore
    @OneToMany( mappedBy = "client")
    private Set<ClientOrder> client_orders;

    public Set<ClientOrder> getClient_orders() {
        return client_orders;
    }

    public void setClient_orders(Set<ClientOrder> client_orders) {
        this.client_orders = client_orders;
    }

    public Client(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
