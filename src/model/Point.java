package model;

public class Point {
	private int x;
	private int y;
	private boolean orientation;
	private String nom;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isOrientation() {
		return orientation;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Point(int x, int y, boolean orienation, String nom) {
		super();
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
