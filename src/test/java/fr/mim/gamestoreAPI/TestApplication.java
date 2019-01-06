package fr.mim.gamestoreAPI;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestApplication {

	@Test
	public void testApplicationHorsLigne() {		
		assertNull(new Application().getRun());
	}
	
	@Test
	public void testApplicationEnLigne(){
		Application application = new Application();
		application.runApplication();
		assertTrue(application.getRun().isRunning());
		application.exitApplication();
	}
}
