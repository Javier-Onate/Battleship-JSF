package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class HistoriquePartie {

	private int id;

	private List<Utilisateur> utilisateurList;

	private String Vainqueur;
	private int nombreCoup;
	
	
	public int getId() {
		return id;
	}	public HistoriquePartie(int id, Utilisateur utilisateur1, Utilisateur utilisateur2, String vainqueur, int nombreCoup) {
		super();
		this.utilisateurList = new ArrayList<Utilisateur>();
		this.utilisateurList.add(utilisateur1);
		this.utilisateurList.add(utilisateur2);
		this.id = id;
		this.utilisateurList = utilisateurList;
		Vainqueur = vainqueur;
		this.nombreCoup = nombreCoup;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	public List<Utilisateur> getUtilisateurList() {
		return utilisateurList;
	}
	public void setUtilisateurList(List<Utilisateur> utilisateurList) {
		this.utilisateurList = utilisateurList;
	}
	public String getVainqueur() {
		return this.Vainqueur;
	}
	public void setVainqueur(String Vainqueur) {
		this.Vainqueur = Vainqueur;
	}
	public int getNombreCoup() {
		return nombreCoup;
	}
	public void setNombreCoup(int nombreCoup) {
		this.nombreCoup = nombreCoup;
	}
	public Timestamp getCreele() {
		return creele;
	}
	public void setCreele(Timestamp creele) {
		this.creele = creele;
	}
	private Timestamp creele;
	
	@Override
	public String toString() {
		return "HistoriquePartie [id=" + id + ", utilisateurList=" + utilisateurList + ", Vainqueur=" + Vainqueur
				+ ", nombreCoup=" + nombreCoup + ", creele=" + creele + "]";
	}
	
	
}
