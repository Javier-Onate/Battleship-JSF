package model;

public class Tir {

	private int x;
	private int y;
	private String emailJoueur;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getEmailJoueur() {
		return emailJoueur;
	}
	public void setEmailJoueur(String emailJoueur) {
		this.emailJoueur = emailJoueur;
	}
	
	public Tir(int x, int y, String emailJoueur) {
		super();
		this.x = x;
		this.y = y;
		this.emailJoueur = emailJoueur;
	}
	
	public Tir() {
		super();
	}
	@Override
	public String toString() {
		return "Tir [x=" + x + ", y=" + y + ", emailJoueur=" + emailJoueur + "]";
	}	
	
}
