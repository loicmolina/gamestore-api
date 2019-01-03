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
		Magasin magasin = new Magasin();
		Jeu jeu = new Jeu();
		jeu.setNom("Super Smash Bros Ultimate");		
		
		magasin.addJeuLocal(jeu);
		
		//When
		ArrayList<Jeu> resultat = magasin.rechercheJeux("Smash");
		
		//Then
		assertEquals(resultat.size(),1);
		assertEquals(resultat.get(0).getNom(),jeu.getNom());
	}
	
	@Test
	public void testRechercheMultiple() throws Exception {
		//Given
		Magasin magasin = new Magasin();
		Jeu jeu = new Jeu();
		jeu.setNom("Super Smash Bros Ultimate");	
		
		Jeu jeu2 = new Jeu();
		jeu2.setNom("Super Smash Bros Melee");		

		Jeu jeu3 = new Jeu();
		jeu3.setNom("Overwatch");		
		
		magasin.addJeuLocal(jeu);
		magasin.addJeuLocal(jeu2);
		magasin.addJeuLocal(jeu3);
		
		//When
		ArrayList<Jeu> resultat = magasin.rechercheJeux("Smash");
		
		//Then
		assertEquals(resultat.size(),2);
		assertEquals(resultat.get(0).getNom(),jeu.getNom());
		assertEquals(resultat.get(1).getNom(),jeu2.getNom());
	}
	
	@Test
	public void testRechercheVide() throws Exception {
		//Given
		Magasin magasin = new Magasin();
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");		
		
		magasin.addJeuLocal(jeu);
		
		//When
		ArrayList<Jeu> resultat = magasin.rechercheJeux("Metroid");
		
		//Then
		assertEquals(resultat.size(),0);
	}

}
