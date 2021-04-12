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
	
	public Case(String value) {
		super();
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
