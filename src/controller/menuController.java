package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

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
	}

	public Utilisateur getU() {
		return u;
	}

	public List<Partie> getP() {
		return p;
	}

	
//	public void setMessages () {
//		for(Partie p1 : this.p) {
//			
//			
//		}
//	}	
	
	public void loadPartie (int id) {
		
		Partie e = new Partie();
		e.setId(id);
		int index = p.indexOf(e);
		session.setAttribute("partie", p.get(index));
		System.out.println(p.get(index));
	}
	
	public String newPartie () {
		
		return "";
	}
}
