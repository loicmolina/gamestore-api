package fr.mim.gamestoreAPI;

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
		if (getJeuParId(jeu.getId()) == null){
			jeux.add(jeu);
			database.insertValue(jeu);
			return true;
		}		
		return false;
	}	
	
	public Jeu getJeuParId(long id){
		return jeux.stream().filter(jeu -> jeu.getId() == id).findFirst().orElse(null);
	}
	
	public ArrayList<Jeu> getJeux(){
		return jeux;
	}
	
	public boolean contains(Jeu jeu){
		return jeux.contains(jeu);
	}

	public boolean deleteJeu(long id) throws Exception {
		Jeu jeu = this.getJeuParId(id);
		if (jeu != null){
			jeux.remove(jeu);
			database.deleteValue(jeu);
			return true;
		}		
		return false;
	}
	
	public void deleteJeux() throws Exception {
		database.deleteValues();
		jeux.clear();	
	}
}
