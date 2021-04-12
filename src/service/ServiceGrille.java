package service;

import java.util.List;

import model.HistoriquePartie;
import model.Partie;
import model.Tir;

public interface ServiceGrille {
	final String URL = "http://localhost:8080/API-Bataille_Naval_JPA/rest/Partie";
	public void setPartie(Partie e);
	public void setFinPartie(HistoriquePartie hp);
	public void removePartie(Partie hp);
}
