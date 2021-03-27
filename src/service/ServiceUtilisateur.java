package service;

import java.util.List;

import model.Utilisateur;


public interface ServiceUtilisateur {
	final String URL = "http://localhost:8080/API-Bataille_Naval_JPA/rest/Utilisateur";
	public Utilisateur register(Utilisateur u);
	public Utilisateur login(Utilisateur u);
	public String getMessage();
	public void setMessage(String message);
}
