package gamestoreAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class H2DataBase {
	private Connection con;
	private Statement stmt;

	public void createDataBase() throws Exception {
		try {
			Class.forName("org.h2.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:h2:" + "./database/dataFile", "root", "password");
			stmt = con.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS MAGASIN" 
					+ "(id INTEGER," 
					+ "nom VARCHAR(255),"
					+ "dateSortie VARCHAR(255)," 
					+ "developpeur VARCHAR(255),"
					+ "genre1 VARCHAR(255)," 
					+ "genre2 VARCHAR(255),"
					+ "PRIMARY KEY (id))";

			stmt.executeUpdate(sql);
			System.out.println("Table created");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void insertValue(Jeu jeu) throws Exception{
		try {
			String sql = "INSERT INTO MAGASIN(id,nom,dateSortie,developpeur,genre1,genre2) values ("
					+ Long.toString(jeu.getId()) + ",\'"
					+ jeu.getNom() + "\',\'"
					+ jeu.getDateSortie() + "\',\'"
					+ jeu.getDeveloppeur() + "\',\'"
					+ jeu.getGenre1() + "\',\'"
					+ jeu.getGenre2() + "\')";
			
			stmt.executeUpdate(sql);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public void changeValue(Jeu jeu) throws Exception{
		try {
			StringBuilder startsql = new StringBuilder("UPDATE MAGASIN SET");
			if (jeu.getNom() != null){
				startsql.append("nom = \'"+ jeu.getNom() +"\' ,");
			}
			if (jeu.getDateSortie() != null){
				startsql.append("dateSortie = \'"+ jeu.getDateSortie() +"\' ,");
			}
			if (jeu.getDeveloppeur() != null){
				startsql.append("developpeur = \'"+ jeu.getDeveloppeur() +"\' ,");
			}
			if (jeu.getGenre1() != null){
				startsql.append("genre1 = \'"+ jeu.getGenre1() +"\' ,");
			}
			if (jeu.getGenre2() != null){
				startsql.append("genre2 = \'"+ jeu.getGenre2() +"\' ,");
			}
			StringBuilder sql = new StringBuilder(startsql.substring(0, startsql.length()-2));
			sql.append("WHERE id == "+Long.toString(jeu.getId()));
			
			stmt.executeUpdate(sql.toString());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public void deleteValue(Jeu jeu) throws Exception{
		try {
			StringBuilder sql = new StringBuilder("DELETE FROM MAGASIN WHERE id == "
					+ Long.toString(jeu.getId()) );
			stmt.executeUpdate(sql.toString());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public ArrayList<Jeu> getValues() throws Exception{
		ArrayList<Jeu> jeux = new ArrayList<Jeu>();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM MAGASIN");
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()){
				Jeu jeu = new Jeu();
				jeu.setId(rs.getLong("id"));
				jeu.setNom(rs.getString("nom"));
				jeu.setDateSortie(rs.getString("dateSortie"));
				jeu.setDeveloppeur(rs.getString("developpeur"));
				jeu.setGenre1(rs.getString("genre1"));
				jeu.setGenre2(rs.getString("genre2"));
				jeux.add(jeu);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return jeux;	
	}
}
