package io.github.arthurgmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}


//USED TO TEST DIRECT OPERATION IN REPOSITORY
//    @Bean
//    public CommandLineRunner init(
//            @Autowired IClientRepository client,
//            @Autowired IClientOrderRepository client_order
//    ) {
//        return args -> {
//            Client arthur = new Client("Arthur");
//            client.save(arthur);
//
//            ClientOrder order = new ClientOrder();
//            order.setClient(arthur);
//            order.setCreated_at(LocalDate.now());
//            order.setTotal(BigDecimal.valueOf(100));
//
//            client_order.save(order);
//
//            Client clientWithOrders = client.findClientWithOrders(arthur.getId());
//            System.out.println(clientWithOrders);
//           System.out.println(clientWithOrders.getClient_orders());
//
//
//            List<ClientOrder> ordersList = client_order.findByClient(arthur);
//            System.out.println(ordersList);
//        };
//    }
//
//
//    @GetMapping("/hello")
//    public String helloWorld() {
//        return "hello word";
//    }
