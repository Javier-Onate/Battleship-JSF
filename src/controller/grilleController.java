package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mezza.navire.CaseNavire;
import com.mezza.navire.EnsembleNavire;
import com.mezza.navire.InterfaceEnsembleNavire;
import com.mezza.navire.Navire;

import model.Case;
import model.HistoriquePartie;
import model.Partie;
import model.Tir;
import model.Utilisateur;
import service.ServiceGrille;
import service.ServiceGrilleImpl;
import service.SessionUtils;

@ManagedBean(name = "grilleBean")
public class grilleController {

	private Utilisateur joueur;
	private Utilisateur joueurAdverse;
	private HttpSession session;
	private Partie p;
	private List<Case> grillejoueur;
	private List<Case> grillejoueurAdverse;
	private EnsembleNavire naviresjoueur;
	private EnsembleNavire naviresjoueurAdverse;
	private List<Tir> tirsjoueur1;
	private List<Tir> tirsjoueur2;
	private int compteur = 0;
	private Gson g = null;
	private ServiceGrille s;
	private List<Tir> tirsjoueur;
	private List<Tir> tirsjoueurAdverse;

	public List<Case> getGrillejoueur() {
		return grillejoueur;
	}

	public List<Case> getGrillejoueurAdverse() {
		return grillejoueurAdverse;
	}

	public grilleController() {
		super();
		this.g = new Gson();
		this.session = new SessionUtils().getSession();
		this.p = (Partie) this.session.getAttribute("currentgame");
		this.grillejoueurAdverse = new ArrayList<>();
		this.grillejoueur = new ArrayList<>();
		this.joueur = (Utilisateur) this.session.getAttribute("user");
		this.tirsjoueur1 = new ArrayList<>();
		this.tirsjoueur2 = new ArrayList<>();
		this.s = new ServiceGrilleImpl();
		this.tirsjoueur = new ArrayList<>();
		this.tirsjoueurAdverse = new ArrayList<>();

		if (p.getTirjoueur1() != null) {
			this.tirsjoueur1 = g.fromJson(p.getTirjoueur1(), new TypeToken<ArrayList<Tir>>() {
			}.getType());
			if (this.tirsjoueur1.get(0).getEmailJoueur().equals(this.joueur.getEmail())) {
				this.tirsjoueur = this.tirsjoueur1;
			} else {
				this.tirsjoueurAdverse = this.tirsjoueur1;
			}
		}
		if (p.getTirjoueur2() != null) {
			this.tirsjoueur2 = g.fromJson(p.getTirjoueur2(), new TypeToken<ArrayList<Tir>>() {
			}.getType());
			if (this.tirsjoueur2.get(0).getEmailJoueur().equals(this.joueur.getEmail())) {
				this.tirsjoueur = this.tirsjoueur2;
			} else {
				this.tirsjoueurAdverse = this.tirsjoueur2;
			}
		}

//		for(Tir t : this.tirsjoueur) {
//			System.out.println(t.toString() + "tirsjoueur");
//		}
//		
//		for(Tir t : this.tirsjoueurAdverse) {
//			System.out.println(t.toString() + "tirsjoueurAdverse");
//		}

		if (this.p.getUtilisateurList().size() == 2) {
			for (Utilisateur u : this.p.getUtilisateurList()) {
				if (!u.getEmail().equals(this.joueur.getEmail())) {
					this.joueurAdverse = u;
				}
			}
			this.naviresjoueurAdverse = new EnsembleNavire();
			this.naviresjoueurAdverse.FilesToNavires(
					"D:\\Javier\\FORMATION-JAVA\\eclipse-workspace\\battleshipjsf\\fichierClient",
					joueurAdverse.getEmail(), this.p.getId());
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {

					Case c = new Case(j, i, "yellow", j + "" + i);
					this.grillejoueurAdverse.add(c);

				}
			}
		} else {
			this.joueurAdverse = null;

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Case c = new Case(j, i, "yellow", j + "" + i);
					this.grillejoueurAdverse.add(c);
				}
			}
		}
		this.naviresjoueur = new EnsembleNavire();
		this.naviresjoueur.FilesToNavires("D:\\Javier\\FORMATION-JAVA\\eclipse-workspace\\battleshipjsf\\fichierClient",
				joueur.getEmail(), this.p.getId());

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (naviresjoueur.contains(new CaseNavire(j, i, 0))) {
					Case c = new Case(j, i, "green", j + "" + i);
					this.grillejoueur.add(c);
				} else {
					Case c = new Case(j, i, "yellow", j + "" + i);
					this.grillejoueur.add(c);
				}
			}
		}

		if (this.tirsjoueur.size() != 0) {
			for (Tir t : this.tirsjoueur) {
				int index = this.grillejoueurAdverse.indexOf(new Case(t.getX() + "" + t.getY()));
				this.grillejoueurAdverse.get(index).setColor("Black");
			}
		}
		if (this.tirsjoueurAdverse.size() != 0) {
			System.out.println("je suis la");
			for (Tir t : this.tirsjoueurAdverse) {
				int index = this.grillejoueur.indexOf(new Case(t.getX() + "" + t.getY()));
				this.grillejoueur.get(index).setColor("Black");
			}
		}
		for (Navire navire : this.naviresjoueur) {
			for (CaseNavire c : navire.getParts()) {
				if (c.estDetruit()) {
					int index = this.grillejoueur.indexOf(new Case(c.getX() + "" + c.getY()));
					this.grillejoueur.get(index).setColor("Brown");
				}
			}
		}

		List<Navire> naviredetruit = this.naviresjoueur.NavireDetruit();
		if (naviredetruit.size() != 0) {
			for (Navire navire : naviredetruit) {
				for (CaseNavire c : navire.getParts()) {
					if (c.estDetruit()) {
						int index = this.grillejoueur.indexOf(new Case(c.getX() + "" + c.getY()));
						this.grillejoueur.get(index).setColor("Red");
					}
				}
			}
		}

		// Tout ce qui ne doit pas ce charger si moins de deux joueurs
		if (p.getUtilisateurList().size() == 2) {

			for (Navire navire : this.naviresjoueurAdverse) {
				for (CaseNavire c : navire.getParts()) {
					if (c.estDetruit()) {
						int index = this.grillejoueurAdverse.indexOf(new Case(c.getX() + "" + c.getY()));
						this.grillejoueurAdverse.get(index).setColor("Brown");
					}
				}
			}

			naviredetruit = this.naviresjoueurAdverse.NavireDetruit();
			if (naviredetruit.size() != 0) {
				for (Navire navire : naviredetruit) {
					for (CaseNavire c : navire.getParts()) {
						if (c.estDetruit()) {
							int index = this.grillejoueurAdverse.indexOf(new Case(c.getX() + "" + c.getY()));
							this.grillejoueurAdverse.get(index).setColor("Red");
						}
					}
				}
			}
		}
	}

	public String tirer(int x, int y) {
		if (p.getTourJoueur().equals(this.joueur.getEmail())) {
			Tir t = new Tir(x, y, this.joueur.getEmail());
			this.tirsjoueur.add(t);

			String reponse = this.naviresjoueurAdverse.toucher(x, y);

			if (reponse.equals("Raté")) {
				int index = this.grillejoueurAdverse.indexOf(new Case(t.getX() + "" + t.getY()));
				this.grillejoueurAdverse.get(index).setColor("Black");
			} else {

				int index = this.grillejoueurAdverse.indexOf(new Case(t.getX() + "" + t.getY()));
				this.grillejoueurAdverse.get(index).setColor("Brown");

			}
			List<Navire> naviredetruit = this.naviresjoueurAdverse.NavireDetruit();
			if (naviredetruit.size() != 0) {
				for (Navire navire : naviredetruit) {
					for (CaseNavire c : navire.getParts()) {
						if (c.estDetruit()) {
							int index = this.grillejoueurAdverse.indexOf(new Case(c.getX() + "" + c.getY()));
							this.grillejoueurAdverse.get(index).setColor("Red");
						}
					}
				}
			}

		
			if(this.p.getTirjoueur1() != null) {
				if (this.tirsjoueur1.get(0).getEmailJoueur().equals(this.joueur.getEmail())) {
					this.p.setTirjoueur1(g.toJson(this.tirsjoueur));
				} else {
					this.p.setTirjoueur2(g.toJson(this.tirsjoueur));
				}
			}
			else {
				this.p.setTirjoueur1(g.toJson(this.tirsjoueur));
			}
			
//			this.p.setTirjoueur1(g.toJson(this.tirsjoueur));
			System.out.println(p.getTirjoueur1());
			this.naviresjoueurAdverse.SaveFiles(
					"D:\\Javier\\FORMATION-JAVA\\eclipse-workspace\\battleshipjsf\\fichierClient",
					this.joueurAdverse.getEmail(), this.p.getId());

			this.p.setNombreCoup(this.p.getNombreCoup() + 1);
			this.p.setTourJoueur(this.joueurAdverse.getEmail());
			this.s.setPartie(p);
			this.session.setAttribute("currentgame", p);

			System.out.println(session.getAttribute("currentgame"));
			return "grille";
		} else {
			return "menuser";
		}

	}
	

	public String abandonPartie () {
		System.out.println(p.getUtilisateurList().size());
		if(p.getUtilisateurList().size() != 2) {			
			s.removePartie(this.p);
		}
		else {			
			HistoriquePartie hp = new HistoriquePartie(p.getId(), p.getUtilisateurList().get(1), p.getUtilisateurList().get(0), this.joueurAdverse.getEmail(), p.getNombreCoup());
			s.removePartie(this.p);
			s.setFinPartie(hp);			
		}
		
		return "menuser";
	}

}
