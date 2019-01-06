package fr.mim.gamestoreAPI;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestApplication {

	@Test
	public void testApplicationHorsLigne(){
		assertNull(Application.run);
	}
	
	@Test
	public void testApplicationEnLigne(){
		Application.runApplication();
		assertTrue(Application.run.isRunning());
		Application.exitApplication();
	}
}
