package fr.mim.gamestoreAPI.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.mim.gamestoreAPI.modele.Jeu;

public class H2DataBase {
	private static final Logger LOGGER = Logger.getLogger(H2DataBase.class.getName());
	private static boolean connected;
	private Statement stmt;
	private Connection con;

	public H2DataBase(){
		stmt = null;
		con = null;
	}

	public void createDataBase()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		try {
			LOGGER.log(Level.FINE, "Test branch master s");
			connectionToDatabase();
			stmt = con.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS MAGASIN" + "(id INTEGER," + "nom VARCHAR(255),"
					+ "dateSortie VARCHAR(255)," + "developpeur VARCHAR(255)," + "genre1 VARCHAR(255),"
					+ "genre2 VARCHAR(255)," + "PRIMARY KEY (id))";

			stmt.executeUpdate(sql);
			connected = true;
			LOGGER.log(Level.FINE, "Table created");

		} catch (SQLException e) {
			connected = false;
			LOGGER.log(Level.WARNING, e.getMessage());
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					if (connected){
						closeConnectionToDatabase();		        		
					}
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, e.getMessage());
				}
			}
		}));
	}

	public void connectionToDatabase() throws SQLException{
		con = DriverManager.getConnection("jdbc:h2:" + "./database/dataFile", "root", "");
	}

	public void closeConnectionToDatabase() throws SQLException {
		con.close();
	}

	public void insertValue(Jeu jeu)
			throws SQLException{
		if (connected) {
			PreparedStatement sql = null;
			try {
				sql = con.prepareStatement("INSERT INTO MAGASIN(id,nom,dateSortie,developpeur,genre1,genre2) values (?, ?, ?, ?, ?, ?)");
				sql.setString(1, Long.toString(jeu.getId()));
				sql.setString(2, jeu.getNom());
				sql.setString(3, jeu.getDateSortie());
				sql.setString(4, jeu.getDeveloppeur());
				sql.setString(5, jeu.getGenre1());
				sql.setString(6, jeu.getGenre2());
				sql.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, e.getMessage());
			} finally {
				sql.close();
			}
		}
	}

	public void deleteValue(Jeu jeu)
			throws SQLException{
		if (connected) {
			connectionToDatabase();
			try {
				StringBuilder sql = new StringBuilder("DELETE FROM MAGASIN WHERE id = ");
				sql.append(Long.toString(jeu.getId()));
				stmt.executeUpdate(sql.toString());

			} catch (SQLException e ){
				LOGGER.log(Level.WARNING, e.getMessage());
			} 
		}
	}

	public void deleteValues()
			throws SQLException{
		if (connected) {
			try {
				String sql = "DELETE FROM MAGASIN";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, e.getMessage());
			} 
		}
	}

	public Set<Jeu> getValues()
			throws SQLException{
		Set<Jeu> jeux = new HashSet<>();
		if (connected) {
			String sql = "SELECT * FROM MAGASIN";
			try (ResultSet rs = stmt.executeQuery(sql);) {		
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
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, e.getMessage());
			}
		}
		return jeux;
	}

	public static boolean isDatabaseUp(){
		return connected;
	}
}
