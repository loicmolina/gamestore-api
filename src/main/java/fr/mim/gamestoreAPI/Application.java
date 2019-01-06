package fr.mim.gamestoreAPI;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.mim.gamestoreAPI" } )
public class Application {
	
	private final static ConfigurableApplicationContext run = SpringApplication.run(Application.class);

    public static void main(String[] args) {
        runApplication();
    }
    
    public static ConfigurableApplicationContext runApplication(){
    	return run;
    }
    
    public static int exitApplication(){
    	return SpringApplication.exit(run, (ExitCodeGenerator) () -> 0);
    }
}
