package gamestoreAPI;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

@RestController
public class JeuControlleur {

    private static final String templateJeuAjoute = "Le jeu %s a été ajouté !";
    private static final String templateJeuDejaPresent = "Un jeu possédant l'id %d existe déjà !";
    private static final String templateJeuSupprime = "L'identifiant %d a été supprimé !";
    private static final String templateJeuInexistant = "L'identifiant %d n'existe pas !";
    private static final String jeuxSupprimes = "Les jeux ont été retiré du magasin";
    private final Magasin magasin = new Magasin();

    @RequestMapping(value = "/jeu",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public String ajouterJeu(@RequestBody Jeu jeu) {    	
    	boolean res = magasin.addJeu(jeu);
        return res ? String.format(templateJeuAjoute, jeu.getNom()) : String.format(templateJeuDejaPresent, jeu.getId());
    }
    
    @RequestMapping(value = "/jeu",
            produces = { "application/json" }, 
            method = RequestMethod.GET)
    public ArrayList<Jeu> getJeuxParNom(@RequestParam(value="name", defaultValue = "") String name, @RequestParam(value="id", defaultValue = "0") long id) { 
    	if (id != 0){
    		return magasin.getJeuParId(id);
    	}
    	return magasin.getJeuParNom(name);
    }
    
    @RequestMapping(value = "/jeu",
            produces = { "application/json" }, 
            method = RequestMethod.DELETE)
    public String deleteJeu(@RequestParam(value="id", defaultValue = "0") long id) { 
    	if (id != 0){
    		boolean res = magasin.deleteJeu(id);
    		return res ? String.format(templateJeuSupprime, id) : String.format(templateJeuInexistant, id);
    	}
    	return String.format(templateJeuInexistant, id);
    }
    
    @RequestMapping(value = "/jeu",
            produces = { "application/json" }, 
            method = RequestMethod.PUT)
    public String modifierJeu(@RequestBody Jeu jeu, @RequestParam(value="id", defaultValue = "0") long id) { 
    	if (id != 0 && id == jeu.getId()){
    		boolean res = magasin.deleteJeu(id);
    		magasin.addJeu(jeu);
    		return res ? String.format(templateJeuSupprime, id) : String.format(templateJeuInexistant, id);
    	}
    	return String.format(templateJeuInexistant, id);
    }
    
    @RequestMapping(value = "/jeux",
            produces = { "application/json" }, 
            method = RequestMethod.GET)
    public ArrayList<Jeu> getJeux() {    	
    	return magasin.getJeux();
    }
    
    @RequestMapping(value = "/jeux",
            method = RequestMethod.DELETE)
    public String deleteJeux() {    	
    	magasin.deleteJeux();
    	return jeuxSupprimes;
    }
}
