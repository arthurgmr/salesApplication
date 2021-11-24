package io.github.arthurgmr;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.domain.repository.IClientOrderRepository;
import io.github.arthurgmr.domain.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class SalesApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired IClientRepository client,
            @Autowired IClientOrderRepository client_order
    ) {
        return args -> {
            Client arthur = new Client("Arthur");
            client.save(arthur);

            ClientOrder order = new ClientOrder();
            order.setClient(arthur);
            order.setCreated_at(LocalDate.now());
            order.setTotal(BigDecimal.valueOf(100));

            client_order.save(order);

//            Client clientWithOrders = client.findClientWithOrders(arthur.getId());
//            System.out.println(clientWithOrders);
//            System.out.println(clientWithOrders.getClient_orders());


            List<ClientOrder> ordersList = client_order.findByClient(arthur);
            System.out.println(ordersList);
        };
    }


    @GetMapping("/hello")
    public String helloWorld() {
        return "hello word";
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}
