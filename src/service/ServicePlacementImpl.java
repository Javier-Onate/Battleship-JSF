package service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Partie;
import model.Utilisateur;

public class ServicePlacementImpl implements ServicePlacement {

	private Client client = null;
	private Gson g = null;
	private WebResource webResource = null;

	public ServicePlacementImpl() {
		client = Client.create();
		g = new Gson();
	}

	public Partie creerPartie(Utilisateur u) {

		webResource = client.resource(URL);
		String jsonutilisateur = g.toJson(u);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonutilisateur);
		if(response.getStatus() == 200) {
			String jsonstring = response.getEntity(String.class);
			Partie p1 =g.fromJson(jsonstring, new TypeToken<Partie>(){}.getType());
			return p1;
		}
		else  {
			System.out.println("ERREUR");
			return null;
		}
	}

}
