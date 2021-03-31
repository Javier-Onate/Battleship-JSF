package model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Partie {

	private int id;
	private String tourJoueur;
	private int nombreCoup;	
	private Timestamp creele;
	private List<Utilisateur> utilisateurList;
	
	
	public Partie() {
		super();
	}
	public List<Utilisateur> getUtilisateurList() {
		return utilisateurList;
	}
	public void setUtilisateurList(List<Utilisateur> utilisateurList) {
		this.utilisateurList = utilisateurList;
	}	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNombreCoup() {
		return nombreCoup;
	}
	
	public void setNombreCoup(int nombreCoup) {
		this.nombreCoup = nombreCoup;
	}
	
	public String getTourJoueur() {
		return tourJoueur;
	}
	
	public void setTourJoueur(String tourJoueur) {
		this.tourJoueur = tourJoueur;
	}
	@Override
	public String toString() {
		return "Partie [id=" + id + ", tourJoueur=" + tourJoueur + ", nombreCoup=" + nombreCoup + ", creele=" + creele
				+ ", utilisateurList=" + utilisateurList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partie other = (Partie) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
