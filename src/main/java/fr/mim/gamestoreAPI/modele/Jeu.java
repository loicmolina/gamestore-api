package fr.mim.gamestoreAPI.modele;

import java.lang.String;

public class Jeu {
	private long id;
	private String nom;
	private String dateSortie;
	private String developpeur;
	private String genre1;
	private String genre2;
	private final static String PATTERNDATE = "\\d{2}/\\d{2}/\\d{4}";
	
	public Jeu(){	}
	
	public Jeu(long id, String nom){
		this.id = id;
		this.nom = nom;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDateSortie() {
		return dateSortie;
	}
	
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	public String getDeveloppeur() {
		return developpeur;
	}
	
	public void setDeveloppeur(String developpeur) {
		this.developpeur = developpeur;
	}

	public String getGenre1() {
		return genre1;
	}

	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}

	public String getGenre2() {
		return genre2;
	}

	public void setGenre2(String genre2) {
		this.genre2 = genre2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null){
			return this.id == ((Jeu)obj).getId();
		}else{
			throw new NullPointerException("L'objet est vide");
		}
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean dateCorrect(){
		return dateSortie.matches(PATTERNDATE);
	}
	
}
