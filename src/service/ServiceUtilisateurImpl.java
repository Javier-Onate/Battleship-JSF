package service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


import model.Utilisateur;

public class ServiceUtilisateurImpl implements ServiceUtilisateur {

	private String message;
	private Client client = null;
	private Gson g = null ;
	WebResource webResource = null;

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceUtilisateurImpl() {
		client = Client.create();
		g = new Gson();
	}
	
	@Override
	public Utilisateur register(Utilisateur u) {
		
		webResource = client.resource(URL);
		String jsonutilisateur = g.toJson(u);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonutilisateur);
		if(response.getStatus() == 200) {
			String jsonstring = response.getEntity(String.class);
			Utilisateur u1 =g.fromJson(jsonstring, new TypeToken<Utilisateur>(){}.getType());
			return u1;
		}
		if (response.getStatus() == 201) {
			this.message = response.getEntity(String.class);
		}
		
		return null;
	}

	@Override
	public Utilisateur login(Utilisateur u) {
		webResource = client.resource(URL + "/byemail");
		String jsonutilisateur = g.toJson(u);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonutilisateur);
		if(response.getStatus() == 200) {
			String jsonstring = response.getEntity(String.class);
			Utilisateur u1 =g.fromJson(jsonstring, new TypeToken<Utilisateur>(){}.getType());
			return u1;
		}
		if (response.getStatus() == 201) {
			this.message = response.getEntity(String.class);
		}
		return null;
	}


	
}
