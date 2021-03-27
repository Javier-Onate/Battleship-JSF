package controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import service.ServiceUtilisateur;
import service.ServiceUtilisateurImpl;
import service.SessionUtils;

@ManagedBean (name="utilisateurBean")
public class utilisateurController {

	private Utilisateur u;
	private ServiceUtilisateur s;
	private HttpSession session;
	
	public utilisateurController() {
		this.u = new Utilisateur();
		this.s = new ServiceUtilisateurImpl();
		
		this.session = new SessionUtils().getSession();
		s.setMessage((String) session.getAttribute("messageErreurConnexion"));
	}
	
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

	public String login() {
		Utilisateur u1 = s.login(u);
		
		if(u1 == null) {
			System.out.println(s.getMessage());
			session.setAttribute("messageErreurConnexion", s.getMessage());

			return "login";
		}
		else {
			session.setAttribute("user", u1);
			Utilisateur u2 = (Utilisateur) session.getAttribute("user");
			session.setAttribute("messageErreurConnexion", "");

			return "login";
		}		
	}	
	
	public String register() {
		Utilisateur u1 = s.login(u);
		
		if(u1 == null) {
			System.out.println(s.getMessage());
			session.setAttribute("messageErreurConnexion", s.getMessage());

			return "login";
		}
		else {
			session.setAttribute("user", u1);
			Utilisateur u2 = (Utilisateur) session.getAttribute("user");
			session.setAttribute("messageErreurConnexion", "");

			return "login";
		}		
	}	
}
