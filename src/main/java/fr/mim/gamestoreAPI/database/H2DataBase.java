package fr.mim.gamestoreAPI.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.mim.gamestoreAPI.modele.Jeu;

public class H2DataBase {
	private Statement stmt;
	private static final Logger LOGGER = Logger.getLogger(H2DataBase.class.getName());
	private Connection con;

	public void createDataBase()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			connectionToDatabase();
			stmt = con.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS MAGASIN" + "(id INTEGER," + "nom VARCHAR(255),"
					+ "dateSortie VARCHAR(255)," + "developpeur VARCHAR(255)," + "genre1 VARCHAR(255),"
					+ "genre2 VARCHAR(255)," + "PRIMARY KEY (id))";

			stmt.executeUpdate(sql);
			LOGGER.log(Level.FINE, "Table created");

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} finally {
			closeConnectionToDatabase();
		}
	}

	public void connectionToDatabase()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Class<?> classe = Class.forName("org.h2.Driver");
		classe.newInstance();
		con = DriverManager.getConnection("jdbc:h2:" + "./database/dataFile", "root", "password");
	}

	public void closeConnectionToDatabase() throws SQLException {
		con.close();
	}

	public void insertValue(Jeu jeu)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		connectionToDatabase();
		String separateur = "\',\'";
		try {
			String sql = "INSERT INTO MAGASIN(id,nom,dateSortie,developpeur,genre1,genre2) values ("
					+ Long.toString(jeu.getId()) + ",\'" + jeu.getNom() + separateur + jeu.getDateSortie() + separateur
					+ jeu.getDeveloppeur() + separateur + jeu.getGenre1() + separateur + jeu.getGenre2() + "\')";

			stmt.executeUpdate(sql);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} finally {
			closeConnectionToDatabase();
		}
	}

	public void deleteValue(Jeu jeu)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		connectionToDatabase();
		try {
			StringBuilder sql = new StringBuilder("DELETE FROM MAGASIN WHERE id = ");
			sql.append(Long.toString(jeu.getId()));
			stmt.executeUpdate(sql.toString());
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} finally {
			closeConnectionToDatabase();
		}
	}

	public void deleteValues()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		connectionToDatabase();
		try {
			StringBuilder sql = new StringBuilder("DELETE FROM MAGASIN");
			stmt.executeUpdate(sql.toString());
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} finally {
			closeConnectionToDatabase();
		}
	}

	public Set<Jeu> getValues()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		connectionToDatabase();
		Set<Jeu> jeux = new HashSet<>();
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM MAGASIN");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				Jeu jeu = new Jeu();
				jeu.setId(rs.getLong("id"));
				jeu.setNom(rs.getString("nom"));
				jeu.setDateSortie(rs.getString("dateSortie"));
				jeu.setDeveloppeur(rs.getString("developpeur"));
				jeu.setGenre1(rs.getString("genre1"));
				jeu.setGenre2(rs.getString("genre2"));
				jeux.add(jeu);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} finally {
			closeConnectionToDatabase();
		}
		return jeux;
	}
}