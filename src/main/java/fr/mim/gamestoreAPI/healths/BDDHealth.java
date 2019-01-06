package fr.mim.gamestoreAPI.healths;

import java.sql.SQLException;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import fr.mim.gamestoreAPI.database.H2DataBase;

@Component
public class BDDHealth implements HealthIndicator {
  
    @Override
    public Health health() {
        boolean databaseUp;
		try {
			databaseUp = H2DataBase.isDatabaseUp();
		} catch (SQLException e) {
			databaseUp = false;		
		}
		
        if (databaseUp) {
            return Health.up().withDetail("La base de données est connecté à l'application", databaseUp).build();
        }
        return Health.down().withDetail("La base de données est down", databaseUp).build();
    }
}