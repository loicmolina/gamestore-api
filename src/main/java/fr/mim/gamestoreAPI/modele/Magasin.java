package fr.mim.gamestoreAPI.modele;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.mim.gamestoreAPI.database.H2DataBase;
import fr.mim.gamestoreAPI.utils.TextUtils;

public class Magasin {
	private Set<Jeu> jeux;
	private static H2DataBase database = new H2DataBase();

	public Magasin(boolean recuperationDonnees) throws Exception {
		jeux = new HashSet<>();
		if (recuperationDonnees) {
			database.createDataBase();
			jeux = database.getValues();
		}
	}

	public boolean addJeu(Jeu jeu) throws Exception {
		if (getJeuParNom(jeu.getNom()) == null) {
			addJeuLocal(jeu);
			addJeuBDD(jeu);
			return true;
		}
		return false;
	}

	public void addJeuLocal(Jeu jeu) {
		jeux.add(jeu);
	}

	private void addJeuBDD(Jeu jeu)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		database.insertValue(jeu);
	}

	public Jeu getJeuParId(long id) {
		return jeux.stream().filter(jeu -> jeu.getId() == id).findFirst().orElse(null);
	}

	public Jeu getJeuParNom(String nom) {
		return jeux.stream().filter(jeu -> jeu.getNom().equals(nom)).findFirst().orElse(null);
	}

	public Set<Jeu> getJeux() {
		return jeux;
	}

	public boolean contains(Jeu jeu) {
		return jeux.contains(jeu);
	}

	public boolean deleteJeu(long id) throws Exception {
		Jeu jeu = this.getJeuParId(id);
		if (jeu != null) {
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

	public ArrayList<Jeu> rechercheJeux(String nom) {
		String comparaison = ".*" + TextUtils.normaliser(nom) + ".*";
		List<Jeu> res = jeux.stream().filter(jeu -> TextUtils.normaliser(jeu.getNom()).matches(comparaison))
				.collect(Collectors.toList());
		;

		return (ArrayList<Jeu>) res;
	}
}
