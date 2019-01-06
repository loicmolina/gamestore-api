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
		JeuControleur.restart(false);
		ResponseEntity<String> ajouterJeu = jc.ajouterJeu(jeu);
		
		assertEquals(HttpStatus.CREATED, ajouterJeu.getStatusCode());
	}
	
	@Test
	public void testAddJeuExistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> ajouterJeu2 = jc.ajouterJeu(jeu);
		
		assertEquals(HttpStatus.CONFLICT, ajouterJeu2.getStatusCode());
	}
	
	@Test
	public void testAddJeuMauvaiseDate() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		jeu.setDateSortie("15/11/17");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		ResponseEntity<String> ajouterJeu = jc.ajouterJeu(jeu);
		
		assertEquals(HttpStatus.BAD_REQUEST, ajouterJeu.getStatusCode());
	}
	
	@Test
	public void testGetJeu() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<?> jeuxParId = jc.getJeuxParId(0);
		
		assertEquals(HttpStatus.OK, jeuxParId.getStatusCode());
	}
	
	@Test
	public void testGetJeuInexistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		ResponseEntity<?> jeuxParId = jc.getJeuxParId(0);
		
		assertEquals(HttpStatus.NOT_FOUND, jeuxParId.getStatusCode());
	}
	
	@Test
	public void testDelete() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> supprimerJeu = jc.deleteJeu(0);
		
		assertEquals(HttpStatus.OK, supprimerJeu.getStatusCode());
	}
	
	@Test
	public void testDeleteJeuInexistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		ResponseEntity<String> supprimerJeu = jc.deleteJeu(0);
		
		assertEquals(HttpStatus.NOT_FOUND, supprimerJeu.getStatusCode());
	}
	
	@Test
	public void testPutJeu() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("nier : automata");
		
		Jeu jeumodifie = new Jeu();
		jeumodifie.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> modifierJeu = jc.modifierJeu(jeumodifie, 0);
		
		assertEquals(HttpStatus.OK, modifierJeu.getStatusCode());
	}
	
	@Test
	public void testPutJeuInexistant() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("nier : automata");
		
		Jeu jeumodifie = new Jeu();
		jeumodifie.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		ResponseEntity<String> modifierJeu = jc.modifierJeu(jeumodifie, 0);
		
		assertEquals(HttpStatus.NOT_FOUND, modifierJeu.getStatusCode());
	}
	
	@Test
	public void testGetJeux() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("Metroid Prime");
		
		Jeu jeu2 = new Jeu();
		jeu2.setNom("NieR : Automata");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		jc.ajouterJeu(jeu2);
		ResponseEntity<Set<Jeu>> jeux = jc.getJeux();
		
		assertEquals(HttpStatus.OK, jeux.getStatusCode());
		assertEquals(2, jeux.getBody().size());
	}
	
	@Test
	public void testDeleteJeux() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("Metroid Prime");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		ResponseEntity<String> supprimerJeux = jc.deleteJeux();
		
		assertEquals(HttpStatus.OK, supprimerJeux.getStatusCode());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRechercheJeux() throws Exception{
		Jeu jeu = new Jeu();
		jeu.setNom("Metroid Prime");
		
		Jeu jeu2 = new Jeu();
		jeu2.setNom("Super Mario");
		
		JeuControleur jc = new JeuControleur();
		JeuControleur.restart(false);
		jc.ajouterJeu(jeu);
		jc.ajouterJeu(jeu2);
		ResponseEntity<Object> jeux = jc.rechercheJeux("metroid");
		
		assertEquals(HttpStatus.OK, jeux.getStatusCode());
		assertEquals(1, ((ArrayList<Jeu>) jeux.getBody()).size());
	}
}
