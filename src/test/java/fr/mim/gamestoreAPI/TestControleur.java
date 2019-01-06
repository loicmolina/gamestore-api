package fr.mim.gamestoreAPI;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.mim.gamestoreAPI.controleurs.JeuControleur;
import fr.mim.gamestoreAPI.modele.Jeu;

public class TestControleur {

	@Test
	public void testAddJeu() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		ResponseEntity<String> ajouterJeu = jc.ajouterJeu(jeu);
		
		assertEquals(ajouterJeu.getStatusCode(),HttpStatus.CREATED);
	}
	
	@Test
	public void testAddJeuExistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> ajouterJeu2 = jc.ajouterJeu(jeu);
		
		assertEquals(ajouterJeu2.getStatusCode(),HttpStatus.CONFLICT);
	}
	
	@Test
	public void testAddJeuMauvaiseDate() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		jeu.setDateSortie("15/11/17");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		ResponseEntity<String> ajouterJeu = jc.ajouterJeu(jeu);
		
		assertEquals(ajouterJeu.getStatusCode(),HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void testGetJeu() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<?> jeuxParId = jc.getJeuxParId(0);
		
		assertEquals(jeuxParId.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void testGetJeuInexistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		ResponseEntity<?> jeuxParId = jc.getJeuxParId(0);
		
		assertEquals(jeuxParId.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testDelete() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> supprimerJeu = jc.deleteJeu(0);
		
		assertEquals(supprimerJeu.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void testDeleteJeuInexistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		ResponseEntity<String> supprimerJeu = jc.deleteJeu(0);
		
		assertEquals(supprimerJeu.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testPutJeu() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("nier : automata");
		
		Jeu jeumodifie = new Jeu();
		jeumodifie.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> modifierJeu = jc.modifierJeu(jeumodifie, 0);
		
		assertEquals(modifierJeu.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void testPutJeuInexistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("nier : automata");
		
		Jeu jeumodifie = new Jeu();
		jeumodifie.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		ResponseEntity<String> modifierJeu = jc.modifierJeu(jeumodifie, 0);
		
		assertEquals(modifierJeu.getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testGetJeux() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("Metroid Prime");
		
		Jeu jeu2 = new Jeu();
		jeu2.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		jc.ajouterJeu(jeu2);
		ResponseEntity<Set<Jeu>> jeux = jc.getJeux();
		
		assertEquals(jeux.getStatusCode(),HttpStatus.OK);
		assertEquals(jeux.getBody().size(),2);
	}
	
	@Test
	public void testDeleteJeux() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("Metroid Prime");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> supprimerJeux = jc.deleteJeux();
		
		assertEquals(supprimerJeux.getStatusCode(),HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRechercheJeux() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("Metroid Prime");
		
		Jeu jeu2 = new Jeu();
		jeu2.setNom("Super Mario");
		
		JeuControleur jc = new JeuControleur();
		jc.restart(false);
		jc.ajouterJeu(jeu);
		jc.ajouterJeu(jeu2);
		ResponseEntity<Object> jeux = jc.rechercheJeux("metroid");
		
		assertEquals(jeux.getStatusCode(),HttpStatus.OK);
		assertEquals(((ArrayList<Jeu>) jeux.getBody()).size(), 1);
	}
}
