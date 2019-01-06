package fr.mim.gamestoreAPI;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.mim.gamestoreAPI.modele.Jeu;
import fr.mim.gamestoreAPI.modele.Magasin;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRecherche {

	@Test
	public void testRechercheSimple() throws Exception {
		Magasin magasin = new Magasin(false);
		Jeu jeu = new Jeu();
		jeu.setNom("Tekken 5");				
		magasin.addJeuLocal(jeu);
		
		ArrayList<Jeu> resultat = (ArrayList<Jeu>) magasin.rechercheJeux("Tekken");
		
		assertEquals(1, resultat.size());
		assertEquals(jeu.getNom(), resultat.get(0).getNom());
	}
	
	@Test
	public void testRechercheMultiple() throws Exception {
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
		
		ArrayList<Jeu> resultat = (ArrayList<Jeu>) magasin.rechercheJeux("Mario");
		Collections.sort(resultat, new Comparator<Jeu>() {
	        @Override
	        public int compare(Jeu jeu1, Jeu jeu2)
	        {
	            return  jeu1.getNom().compareTo(jeu2.getNom());
	        }
	    });
		
		assertEquals(2, resultat.size());
		assertEquals(jeu2.getNom(), resultat.get(0).getNom());
		assertEquals(jeu.getNom(), resultat.get(1).getNom());
	}
	
	@Test
	public void testRechercheVide() throws Exception {
		Magasin magasin = new Magasin(false);
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");				
		magasin.addJeuLocal(jeu);
		
		ArrayList<Jeu> resultat = (ArrayList<Jeu>) magasin.rechercheJeux("Metroid");
		
		assertEquals(0, resultat.size());
	}

}