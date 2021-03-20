package formation;

import java.util.ArrayList;
import java.util.List;

public class Navire {

	class Position {
		int l;
		int c;
	}
	Position position = new Position();
	String orientation = "H";
	int longueur;
	List<Boolean>etat = new ArrayList<Boolean> ();
	
	@Override
	public String toString() {
		return "Navire [etat=" + etat + "]";
	}
	public Navire (int l, int c, int lg, String or) {
		position.c = c;
		position.l = l;
		longueur = lg;
		orientation = or;
		for (int i=0; i<longueur; i++) {
			etat.add(true);
		} 
	}
	String ImpactTir(int l, int c) {
		String rep="rate";
		if (l == position.l && (c>= position.c && c<(position.c+longueur))) {
			rep="touche";
			etat.set(c - position.c, false);
			if (!etat.contains(true)) rep = "coule";
		} 
		System.out.println(toString());
		return rep;
	}
}
