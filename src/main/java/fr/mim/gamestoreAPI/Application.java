package fr.mim.gamestoreAPI;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.mim.gamestoreAPI" } )
public class Application {
	private ConfigurableApplicationContext run = null;

	public void runApplication() {
		run = SpringApplication.run(Application.class);
	}

	public int exitApplication() {
		return SpringApplication.exit(run, (ExitCodeGenerator) () -> 0);
	}

	public ConfigurableApplicationContext getRun() {
		return run;
	}
	
	public static void main(String[] args) {
		new Application().runApplication();
	}
}
