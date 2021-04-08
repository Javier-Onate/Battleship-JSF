package service;

import model.Partie;
import model.Utilisateur;

public interface ServicePlacement {
	final String URL = "http://localhost:8080/API-Bataille_Naval_JPA/rest/Partie";
	public Partie creerPartie(Utilisateur u);
}
