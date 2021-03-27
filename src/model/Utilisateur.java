package model;



public class Utilisateur {

	private int id;
	private String email;
	private String prenom;	
	private String nom;
	private String password;
	
	public Utilisateur(String email, String prenom, String nom, String password) {
		this.email = email;
		this.prenom = prenom;
		this.nom = nom;
		this.password = password;
	}

    public Utilisateur() {
		super();
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", email=" + email + ", prenom=" + prenom + ", nom=" + nom + ", password="
				+ password + "]";
	}

    
}
