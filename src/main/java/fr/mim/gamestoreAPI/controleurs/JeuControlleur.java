package fr.mim.gamestoreAPI.controleurs;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.mim.gamestoreAPI.modele.Jeu;
import fr.mim.gamestoreAPI.modele.Magasin;

@RestController
public class JeuControlleur{
	private final static AtomicInteger counter = new AtomicInteger();
	
	private static final String templateJeuAjoute = "Le jeu %s a été ajouté !";
	private static final String templateJeuDejaPresent = "Un jeu possédant l'id %d existe déjà !";
	private static final String templateJeuSupprime = "L'identifiant %d a été supprimé !";
	private static final String templateJeuInexistant = "L'identifiant %d n'existe pas !";
	private static final String templateJeuModifie = "Le jeu %s a été modifié";
	private static final String jeuxSupprimes = "Les jeux ont été retiré du magasin";
	
	private static Magasin magasin;
	
	
	static{
		try {
			magasin = new Magasin();
			counter.set(magasin.getJeux().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/jeux", consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<?> ajouterJeu(@RequestBody Jeu jeu) throws Exception {
		if (jeu.getDateSortie() != null && !jeu.dateCorrect()){
			return new ResponseEntity<String>( "La date entrée n'est pas au bon format dd/MM/yyyy", HttpStatus.BAD_REQUEST);
		}
		jeu.setId(counter.getAndIncrement());
		boolean res = magasin.addJeu(jeu);		
		return res ? new ResponseEntity<String>( String.format(templateJeuAjoute, jeu.getNom()) , HttpStatus.CREATED)
				: new ResponseEntity<String>( String.format(templateJeuDejaPresent, jeu.getId()) , HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/jeux/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getJeuxParNom(@PathVariable("id") long id) {
		Jeu jeuRecherche =  magasin.getJeuParId(id) ;
		if (jeuRecherche == null){
			return new ResponseEntity<String>( String.format(templateJeuInexistant, id ) , HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(jeuRecherche, HttpStatus.OK);
	}

	@RequestMapping(value = "/jeux/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJeu(@PathVariable("id") int id) throws Exception {
		if (id != 0) {
			boolean res = magasin.deleteJeu(id);
			if (res){
				return new ResponseEntity<String>( String.format(templateJeuSupprime, id) , HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>( String.format(templateJeuInexistant, id) , HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/jeux/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<?> modifierJeu(@RequestBody Jeu jeu, @PathVariable("id") int id) throws Exception {
		Jeu recherche = magasin.getJeuParId(id);
		if (recherche != null) {
			magasin.deleteJeu(id);
			jeu.setId(id);
			magasin.addJeu(jeu);
			return new ResponseEntity<String>( String.format(templateJeuModifie, jeu.getNom()) , HttpStatus.OK);			
		}
		return new ResponseEntity<String>( String.format(templateJeuInexistant, id) , HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/jeux", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getJeux() {
		return new ResponseEntity<ArrayList<Jeu>>( magasin.getJeux(), HttpStatus.OK);
	}

	@RequestMapping(value = "/jeux", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJeux() throws Exception {
		magasin.deleteJeux();
		return new ResponseEntity<String>( jeuxSupprimes , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/recherche/{nom}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> rechercheJeux(@PathVariable("nom") String nom) {
		ArrayList<Jeu> jeuxRecherche =  magasin.rechercheJeux(nom) ;
		return new ResponseEntity<Object>(jeuxRecherche, HttpStatus.OK);
	}
}
