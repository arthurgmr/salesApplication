package io.github.arthurgmr;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SalesApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository client) {
        return args -> {
            client.save(new Client("Arthur"));
            client.save(new Client("Nara"));

            List<Client> allClients = client.getAll();
            allClients.forEach(System.out::println);
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
