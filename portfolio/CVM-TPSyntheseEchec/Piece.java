public abstract class Piece 
{
	private String nom;
	private String couleur;

	public Piece(String nom, String couleur)  {
		setNom(nom);
		setCouleur(couleur);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur)  {
		if ((couleur == "noir") || (couleur == "blanc"))
			this.couleur = couleur;
	}

	public double norme(Position depart, Position arrivee)  {
		return Math.pow((depart.getLigne() - arrivee.getLigne()), 2) + Math.pow((depart.getColonne() - arrivee.getColonne()), 2);
	}

	public abstract boolean estValide(Position depart, Position arrivee);
}