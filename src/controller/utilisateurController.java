package controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import service.ServiceUtilisateur;
import service.ServiceUtilisateurImpl;
import service.SessionUtils;

// Ici nous avons le bean ou controller qui fera le lien avec le JSF pour la manipulation de l'utilisateur
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

	// On fait appel au login de l'implemantation service et on retourne le message d'erreur et/ou la page que l'on veut
	// On stock aussi les données de la session dans le stockage coté client pour ne pas utiliser @SessionScope
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

			return "menuser";
		}		
	}	
	
	public String register() {
		Utilisateur u1 = s.register(u);
		
		if(u1 == null) {
			System.out.println(s.getMessage());
			session.setAttribute("messageErreurConnexion", s.getMessage());

			return "register";
		}
		else {
			session.setAttribute("user", u1);
			Utilisateur u2 = (Utilisateur) session.getAttribute("user");
			session.setAttribute("messageErreurConnexion", "");

			return "menuser";
		}		
	}	
}
