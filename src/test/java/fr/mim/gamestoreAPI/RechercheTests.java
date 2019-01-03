package fr.mim.gamestoreAPI;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.mim.gamestoreAPI.modele.Jeu;
import fr.mim.gamestoreAPI.modele.Magasin;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RechercheTests {

	@Test
	public void testRechercheSimple() throws Exception {
		//Given
		Magasin magasin = new Magasin(false);
		Jeu jeu = new Jeu();
		jeu.setNom("Tekken 5");		
		
		magasin.addJeuLocal(jeu);
		
		//When
		ArrayList<Jeu> resultat = magasin.rechercheJeux("Tekken");
		
		//Then
		assertEquals(resultat.size(),1);
		assertEquals(resultat.get(0).getNom(),jeu.getNom());
	}
	
	@Test
	public void testRechercheMultiple() throws Exception {
		//Given
		Magasin magasin = new Magasin(false);
		Jeu jeu = new Jeu();
		jeu.setNom("Super Mario Bros.");	
		
		Jeu jeu2 = new Jeu();
		jeu2.setNom("Mario Odyssey");		

		Jeu jeu3 = new Jeu();
		jeu3.setNom("Overwatch");		
		
		magasin.addJeuLocal(jeu);
		magasin.addJeuLocal(jeu2);
		magasin.addJeuLocal(jeu3);
		
		//When
		ArrayList<Jeu> resultat = magasin.rechercheJeux("Mario");
		
		//Then
		assertEquals(resultat.size(),2);
		assertEquals(resultat.get(0).getNom(),jeu.getNom());
		assertEquals(resultat.get(1).getNom(),jeu2.getNom());
	}
	
	@Test
	public void testRechercheVide() throws Exception {
		//Given
		Magasin magasin = new Magasin(false);
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");		
		
		magasin.addJeuLocal(jeu);
		
		//When
		ArrayList<Jeu> resultat = magasin.rechercheJeux("Metroid");
		
		//Then
		assertEquals(resultat.size(),0);
	}

}
