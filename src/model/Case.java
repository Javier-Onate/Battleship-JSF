package model;

public class Case {

	private int x;
	private int y;
	private String color;
	private String value;	
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}	
	
	public String getValue() {
		return value;
	}
	
	public Case(int x, int y, String color, String value) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.value = value;
	}
	public Case() {
		super();
	}
	
}
