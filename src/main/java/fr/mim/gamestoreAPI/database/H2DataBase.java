package fr.mim.gamestoreAPI.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.mim.gamestoreAPI.modele.Jeu;

public class H2DataBase {	
	private Statement stmt;

	public H2DataBase(){}
	
	public void createDataBase() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection con = null;
		try {
			Class classe = Class.forName("org.h2.Driver");
			classe.newInstance();
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
		finally{
			con.close();
		}		
	}
	
	public void insertValue(Jeu jeu) throws SQLException{
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
	
	public void deleteValue(Jeu jeu) throws SQLException{
		try {
			StringBuilder sql = new StringBuilder("DELETE FROM MAGASIN WHERE id = ");
					sql.append(Long.toString(jeu.getId()) );
			stmt.executeUpdate(sql.toString());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public void deleteValues() throws SQLException{
		try {
			StringBuilder sql = new StringBuilder("DELETE FROM MAGASIN");
			stmt.executeUpdate(sql.toString());
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public Set<Jeu> getValues() throws SQLException{
		Set<Jeu> jeux = new HashSet<>();
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM MAGASIN");
			rs = stmt.executeQuery(sql.toString());
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
		finally{
			rs.close();
		}
		return jeux;	
	}
}
