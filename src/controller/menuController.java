package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import model.HistoriquePartie;
import model.Partie;
import model.Utilisateur;
import service.ServiceMenu;
import service.ServiceMenuImpl;
import service.SessionUtils;

@ManagedBean (name="menuserBean")
public class menuController {

	private Utilisateur u;
	private ServiceMenu s;
	private HttpSession session;
	private List<Partie> p;
//	private List<String> messages;
	
	public menuController() {
		
		this.s = new ServiceMenuImpl();
		this.session = new SessionUtils().getSession();
		this.u = (Utilisateur) session.getAttribute("user");
		
		this.p = s.getPartieByUser(this.u);
		
		for(Partie p : this.p) {
			System.out.println(p.toString());
		}
	}

	public Utilisateur getU() {
		return u;
	}

	public List<Partie> getP() {
		return p;
	}

	
	
	public String loadPartie (int id) {
		
		Partie e = new Partie();
		e.setId(id);
		int index = p.indexOf(e);
		session.setAttribute("currentgame", p.get(index));
		System.out.println(p.get(index));
		return "grille";
	}
	
	public String newPartie () {
		this.session.setAttribute("navire", new HashMap<>());
		return "partie";
	}
	
	public void abandonPartie (int id) throws IOException {
		
		System.out.println(p.get(id).getUtilisateurList().size());
		if(p.get(id).getUtilisateurList().size() != 2) {			
			s.removePartie(this.p.get(id));
		}
		else {	
			Utilisateur adverse = null;
			for(Utilisateur u1 : p.get(id).getUtilisateurList()) {
				if(!u.getEmail().equals(u1.getEmail())) {
					adverse = u1;
				}
			}
			
			HistoriquePartie hp = new HistoriquePartie(p.get(id).getId(), u, adverse , adverse.getEmail() , p.get(id).getNombreCoup());
			s.removePartie(this.p.get(id));
			s.setFinPartie(hp);			
		}
		
		new SessionUtils().redirect("menuser.xhtml");
	}
}
