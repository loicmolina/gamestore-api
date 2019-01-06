package fr.mim.gamestoreAPI.controleurs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.mim.gamestoreAPI.modele.Jeu;
import fr.mim.gamestoreAPI.modele.Magasin;

@RestController
public class JeuControleur {
	private static final AtomicInteger counter = new AtomicInteger();
	private static final Logger LOGGER = Logger.getLogger(JeuControleur.class.getName());

	private static final String TEMPLATEJEUAJOUTE = "Le jeu %s a été ajouté !";
	private static final String TEMPLATEJEUDEJAPRESENT = "Un jeu possédant l'id %d existe déjà !";
	private static final String TEMPLATEJEUSUPPRIME = "L'identifiant %d a été supprimé !";
	private static final String TEMPLATEJEUINEXISTANT = "L'identifiant %d n'existe pas !";
	private static final String TEMPSJEUMODIFIE = "Le jeu %s a été modifié";
	private static final String JEUXSUPPRIMES = "Les jeux ont été retiré du magasin";

	private static Magasin magasin;

	static {
		try {
			magasin = new Magasin(true);
			counter.set(magasin.getJeux().size());
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
	}

	public static void restart(boolean online) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		magasin = new Magasin(online);
		counter.set(0);
	}

	@RequestMapping(value = "/jeux", consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<String> ajouterJeu(@RequestBody Jeu jeu) throws Exception {
		if (jeu.getDateSortie() != null && !jeu.dateCorrect()) {
			return new ResponseEntity<>("La date entrée n'est pas au bon format dd/MM/yyyy",
					HttpStatus.BAD_REQUEST);
		}
		jeu.setId(counter.getAndIncrement());
		boolean res = magasin.addJeu(jeu);
		if (res){
			return new ResponseEntity<>(String.format(TEMPLATEJEUAJOUTE, jeu.getNom()), HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(String.format(TEMPLATEJEUDEJAPRESENT, jeu.getId()), HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/jeux/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<?> getJeuxParId(@PathVariable("id") long id) {
		Jeu jeuRecherche = magasin.getJeuParId(id);
		if (jeuRecherche == null) {
			return new ResponseEntity<>(String.format(TEMPLATEJEUINEXISTANT, id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(jeuRecherche, HttpStatus.OK);
	}

	@RequestMapping(value = "/jeux/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteJeu(@PathVariable("id") int id) throws Exception {
		if (id >= 0) {
			boolean res = magasin.deleteJeu(id);
			if (res) {
				return new ResponseEntity<>(String.format(TEMPLATEJEUSUPPRIME, id), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(String.format(TEMPLATEJEUINEXISTANT, id), HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/jeux/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<String> modifierJeu(@RequestBody Jeu jeu, @PathVariable("id") int id) throws Exception {
		Jeu recherche = magasin.getJeuParId(id);
		if (recherche != null) {
			magasin.deleteJeu(id);
			jeu.setId(id);
			magasin.addJeu(jeu);
			return new ResponseEntity<>(String.format(TEMPSJEUMODIFIE, jeu.getNom()), HttpStatus.OK);
		}
		return new ResponseEntity<>(String.format(TEMPLATEJEUINEXISTANT, id), HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/jeux", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Set<Jeu>> getJeux() {
		return new ResponseEntity<>(magasin.getJeux(), HttpStatus.OK);
	}

	@RequestMapping(value = "/jeux", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteJeux() throws Exception {
		magasin.deleteJeux();
		return new ResponseEntity<>(JEUXSUPPRIMES, HttpStatus.OK);
	}

	@RequestMapping(value = "/recherche/{nom}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Object> rechercheJeux(@PathVariable("nom") String nom) {
		ArrayList<Jeu> jeuxRecherche = (ArrayList<Jeu>) magasin.rechercheJeux(nom);
		return new ResponseEntity<>(jeuxRecherche, HttpStatus.OK);
	}
}
