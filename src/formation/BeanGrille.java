    package formation;
     
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;
     
    import javax.faces.bean.ManagedBean;
    import javax.faces.bean.SessionScoped;
     
    @ManagedBean (name="grille")
    @SessionScoped
    public class BeanGrille {
       
        private List<String> cases = new ArrayList<String> ();
        private List<String> casesratees = new ArrayList<String> ();
        private List<String> casestouchees = new ArrayList<String> ();
        private List<String> coulees = new ArrayList<String> ();
        List<List<String>> lignes = null;
        Navire navire;
        
        
        public List<List<String>> getLignes() {
            return lignes;
        }        
        
     
        public List<String> getCases() {
			return cases;
		}

		private void generer ()
        {
        	// 10 * 10        	
        	lignes = new ArrayList<> ();
        	for (int l=0; l<10; l++) {
        		// creer une ligne de 10 cases
            	List<String> ligne = new ArrayList<String> ();
                for (int c=0; c<10 ; c++) {
                	ligne.add(String.format ("(%d,%d)", l,c));
                }
                lignes.add(ligne);
        	} 
        	// Génère le navire
        	navire = new Navire(0, 0, 5, "H");
        }
       
        public BeanGrille ()
        {
        	// Génère la grille
            generer ();          
        }
       
               
        public void tir (String unecase)
        {
//        	casesselectionnees.add(unecase);
        	// Il faut vérifier qu'on a touché le navire
        	// Touché, raté, coulé
        	// Convertire une chaine "(11,12)" => 11 et 12
        		
        	String [] morceaux = unecase.substring(1, unecase.length()-1).split(","); 
        	int l = Integer.parseInt(morceaux[0]);
        	int c = Integer.parseInt(morceaux[1]);
        	String rep = navire.ImpactTir(l,c);
        	if (rep.equals("rate")) {
        		casesratees.add(unecase);
        	}
        	else if (rep.equals("touche")) {
        		casestouchees.add(unecase);
        	}
        	else if (rep.equals("coule")) {
        		casestouchees.add(unecase);
        		coulees.addAll(casestouchees);
        		casestouchees.clear();
        	}
        	
        }
        
        public String couleur (String unecase) {
        	String couleurcase = "cyan";
//            System.out.println(unecase);
            
            if (casesratees.contains(unecase))
            {               
            	couleurcase = "black";
            } 
            if (casestouchees.contains(unecase)) {
            	couleurcase = "yellow";
            }
            if (coulees.contains (unecase)) {
            	couleurcase="red";
            }
            return couleurcase;
        }
       
        public void reset ()    {
            generer ();
            casestouchees.clear();
            casesratees.clear();
            coulees.clear();
        }
     
    }

