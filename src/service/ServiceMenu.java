package service;

import java.util.List;

import model.Partie;
import model.Utilisateur;

public interface ServiceMenu {

	final String URL = "http://localhost:8080/API-Bataille_Naval_JPA/rest/Partie";
	public String getMessage();
	public void setMessage(String message);
	public List<Partie> getPartieByUser(Utilisateur u);
}
