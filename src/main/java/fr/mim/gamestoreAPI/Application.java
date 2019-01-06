package fr.mim.gamestoreAPI;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.mim.gamestoreAPI" } )
public class Application {
	//NOSONAR
	public static ConfigurableApplicationContext run;

    public static void main(String[] args) {
        runApplication();
    }
    
    public static ConfigurableApplicationContext runApplication(){
    	return Application.run = SpringApplication.run(Application.class);
    }
    
    public static int exitApplication(){
    	return SpringApplication.exit(run, (ExitCodeGenerator) () -> 0);
    }
}
