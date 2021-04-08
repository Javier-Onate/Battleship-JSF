package controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.DragDropEvent;

import com.mezza.navire.EnsembleNavire;
import com.mezza.navire.InterfaceEnsembleNavire;
import com.mezza.navire.Navire;

import model.Partie;
import model.Point;
import model.Utilisateur;
import service.ServicePlacement;
import service.ServicePlacementImpl;
import service.ServiceUtilisateur;
import service.SessionUtils;

@ManagedBean(name = "placementBean")
public class placementController {

	private Utilisateur u;
	private ServicePlacement s;
	private HttpSession session;
	private String message;

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}
	
	public String getMessage() {
		return this.message;
	}

	public placementController() {
		
		this.s = new ServicePlacementImpl();

		this.session = new SessionUtils().getSession();
		this.u = (Utilisateur) this.session.getAttribute("user");
	}

	public void onDrop(DragDropEvent dragDropEvent) {
		String dargId = dragDropEvent.getDragId();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String left = params.get(dargId + "_left");
		String right = params.get(dargId + "_right");
		String top = params.get(dargId + "_top");
		String bot = params.get(dargId + "_bot");
		String id = params.get("_dragId");
		
		
		System.out.println(dargId + " L = " + left + " R = "+ right + " T = " + top + " B = " + bot);
		
		// Ici on mes les navires dans la session (hashmap, en gros on instancie les navires)
		Map<String, Point> navires = (Map<String, Point>) this.session.getAttribute("navire");
		
		
		int y = Integer.parseInt(top);
		String nom = "Contre_Torpilleur";
		switch (dargId) {
			case ("porte-avion"): {
				y = Integer.parseInt(top) + 240;
				nom = "Porte_Avion";
				break;
			}
			case ("croiseur"): {
				 y = Integer.parseInt(top) + 180;
				 nom = "Croiseur";
				 break;
			}
			case ("sous-marin"): {
				y = Integer.parseInt(top) + 60;
				nom = "Sous_Marin";
				break;
			}
			case ("torpilleur"): {
				y = Integer.parseInt(top) + 120;
				nom = "Torpilleur";
				break;
			}
		}
		Point p = new Point((Integer.parseInt(left))/60, (y)/60, false, nom);
		navires.put(dargId, p);
		
	}

	public String Grille() {
		
		InterfaceEnsembleNavire en = new EnsembleNavire();
		boolean success = true;
		Navire n = null;
		
		Map<String, Point> navires = (Map<String, Point>) this.session.getAttribute("navire");
		
		
		if(navires.size() != 5) {
			this.message = "Tous les bateaux ne sont pas placé !";
			success = false;
		}
		else {
			for(Entry<String, Point> p : navires.entrySet()) {							
				
				boolean result = en.add(p.getValue().getNom(), p.getValue().getX(), p.getValue().getY(), false);
				
				if(result == false) {
					this.message = "Un ou plusieurs bateaux se chevauchent ! Attention, il doit y avoir une case d'écart entre les bateaux.";
					success = false;
					break;
				}					
			}
		}
		if (success) {
			this.message = "";
			System.out.println(en.toString());
						
			Partie p = s.creerPartie(this.u);
			en.SaveFiles("D:\\Javier\\FORMATION-JAVA\\eclipse-workspace\\battleshipjsf\\fichierClient", u.getEmail(), p.getId());
			
			this.session.setAttribute("currentgame", p);
			return "grille";
		}
		return null;
	}
}
