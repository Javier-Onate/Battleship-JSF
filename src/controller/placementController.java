package controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.DragDropEvent;

import model.Utilisateur;
import service.ServiceUtilisateur;
import service.ServiceUtilisateurImpl;
import service.SessionUtils;

@ManagedBean (name="placementBean")
public class placementController {

	private Utilisateur u;
	private ServiceUtilisateur s;
	private HttpSession session;
	
	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}

	public void setS(ServiceUtilisateur s) {
		this.s = s;
	}

	public ServiceUtilisateur getS() {
		return s;
	}
	
	public placementController() {
		this.u = new Utilisateur();
		this.s = new ServiceUtilisateurImpl();
		
		this.session = new SessionUtils().getSession();
	}
	
	public void onDrop(DragDropEvent dragDropEvent) {
		String dargId = dragDropEvent.getDragId();
	    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    String left = params.get(dargId + "_left");
	    String top = params.get(dargId + "_top");
	    String id = params.get("_dragId");
	    System.out.println(dargId + " " + left + " " + " " + top);
	}
}
