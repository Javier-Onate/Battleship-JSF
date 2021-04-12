package service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.HistoriquePartie;
import model.Partie;
import model.Tir;
import model.Utilisateur;

public class ServiceGrilleImpl implements ServiceGrille {

	private Client client = null;
	private Gson g = null;
	private WebResource webResource = null;

	public ServiceGrilleImpl() {
		client = Client.create();
		g = new Gson();
	}

	public void setPartie(Partie p) {

		webResource = client.resource(URL);
		String jsonutilisateur = g.toJson(p);
		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, jsonutilisateur);

	}
	
	public void setFinPartie(HistoriquePartie hp) {
		webResource = client.resource("http://localhost:8080/API-Bataille_Naval_JPA/rest/HistoriquePartie" );
		String jsonutilisateur = g.toJson(hp);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonutilisateur);
	}
	
	public void removePartie(Partie hp) {
		webResource = client.resource(URL + "/" + hp.getId());
		String jsonutilisateur = g.toJson(hp);
		ClientResponse response = webResource.type("application/json").delete(ClientResponse.class, jsonutilisateur);
	}

}
