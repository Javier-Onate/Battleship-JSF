package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import com.mezza.navire.CaseNavire;
import com.mezza.navire.EnsembleNavire;
import com.mezza.navire.InterfaceEnsembleNavire;

import model.Case;
import model.Partie;
import model.Utilisateur;
import service.SessionUtils;

@ManagedBean (name="grilleBean")
public class grilleController {

	private Utilisateur joueur;
	private Utilisateur joueurAdverse;
	private HttpSession session;
	private Partie p;
	private List<Case> grillejoueur;
	private List<Case> grillejoueurAdverse;
	private EnsembleNavire naviresjoueur;
	private EnsembleNavire naviresjoueurAdverse;
	
	
	public List<Case> getGrillejoueur() {
		return grillejoueur;
	}

	public List<Case> getGrillejoueurAdverse() {
		return grillejoueurAdverse;
	}

	public grilleController() {
		super();
		this.session = new SessionUtils().getSession();
		this.p = (Partie) this.session.getAttribute("currentgame");
		this.grillejoueurAdverse = new ArrayList<>();
		this.grillejoueur = new ArrayList<>();
		this.joueur = (Utilisateur) this.session.getAttribute("user");
		
		if(this.p.getUtilisateurList().size() == 2) {
			for(Utilisateur u : this.p.getUtilisateurList()) {
				if(u.getEmail() != joueur.getEmail()) {
					this.joueurAdverse = u;
				}
			}
			this.naviresjoueurAdverse = new EnsembleNavire();
			this.naviresjoueurAdverse.FilesToNavires("D:\\Javier\\FORMATION-JAVA\\eclipse-workspace\\battleshipjsf\\fichierClient", joueurAdverse.getEmail(), this.p.getId());
			for(int i=0; i<10 ; i++) {
				for(int j=0; j<10 ; j++) {
					
					Case c = new Case(i, j, "yellow", i + "" + j);
					this.grillejoueurAdverse.add(c);
					
				}
			}
		}
		else {
			this.joueurAdverse = null;
			
			for(int i=0; i<10 ; i++) {
				for(int j=0; j<10 ; j++) {
					Case c = new Case(i, j, "yellow", i + "" + j);
					this.grillejoueurAdverse.add(c);
				}
			}
		}
		this.naviresjoueur = new EnsembleNavire();
		this.naviresjoueur.FilesToNavires("D:\\Javier\\FORMATION-JAVA\\eclipse-workspace\\battleshipjsf\\fichierClient", joueur.getEmail(), this.p.getId());
		
		for(int i=0; i<10 ; i++) {
			for(int j=0; j<10 ; j++) {
				if (naviresjoueur.contains(new CaseNavire(i,j,0))) {
					Case c = new Case(j, i, "green", i + "" + j);
					this.grillejoueur.add(c);
				}
				else {
					Case c = new Case(j, i, "yellow", i + "" + j);
					this.grillejoueur.add(c);
				}
			}
		}
	}
	
	public void tirer (int x, int y){
		System.out.println(x + " " + y);
	}
	
}
