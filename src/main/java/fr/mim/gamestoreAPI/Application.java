package fr.mim.gamestoreAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.mim.gamestoreAPI" } )
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}