package service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Partie;
import model.Utilisateur;

public class ServiceMenuImpl implements ServiceMenu {

	private String message;
	private Client client = null;
	private Gson g = null ;
	private WebResource webResource = null;
	
	public ServiceMenuImpl() {
		client = Client.create();
		g = new Gson();
	}

	public String getMessage() {
		return null;
	}
	
	public void setMessage(String message) {
	}
	
	public List<Partie> getPartieByUser(Utilisateur u) {
		
		webResource = client.resource(URL  + "/partie/" + u.getId());
		String jsonutilisateur = g.toJson(u);
		ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
             + response.getStatus());
         }
         String jsonstring = response.getEntity(String.class);
         // Convertir en collection         
         List<Partie> p =g.fromJson(jsonstring, new TypeToken<List<Partie>>(){}.getType());
		return p;
		
	}
	
}
