package gamestoreAPI;

import java.util.ArrayList;

public class Magasin {
	private ArrayList<Jeu> jeux;
	private static H2DataBase database = new H2DataBase();
	
	public Magasin() throws Exception{
		jeux = new ArrayList<Jeu>();
		database.createDataBase();
		jeux = database.getValues();
	}
	
	public boolean addJeu(Jeu jeu) throws Exception{
		if (jeu.getId() > 0 && !getJeuParId(jeu.getId()).contains(jeu)){
			jeux.add(jeu);
			database.insertValue(jeu);
			return true;
		}		
		return false;
	}	
	
	public ArrayList<Jeu> getJeuParNom(String nom){
		ArrayList<Jeu> jeuxTrouves = new ArrayList<>();
		jeux.stream().filter(jeu -> jeu.getNom().equals(nom)).forEach(jeuxTrouves::add);
		return jeuxTrouves;
	}
	
	public ArrayList<Jeu> getJeuParId(long id){
		ArrayList<Jeu> jeuxTrouves = new ArrayList<>();
		jeux.stream().filter(jeu -> jeu.getId() == id).forEach(jeuxTrouves::add);
		return jeuxTrouves;
	}
	
	public ArrayList<Jeu> getJeux(){
		return jeux;
	}
	
	public boolean contains(Jeu jeu){
		return jeux.contains(jeu);
	}

	public boolean deleteJeu(long id) throws Exception {
		ArrayList<Jeu> jeuxTrouves = this.getJeuParId(id);
		if (jeuxTrouves.size() > 0){
			jeux.remove(jeuxTrouves.get(0));
			database.deleteValue(jeuxTrouves.get(0));
			return true;
		}		
		return false;
	}
	
	public void deleteJeux() {
		jeux.clear();	
	}
}
