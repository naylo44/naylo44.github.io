public class Reine extends Piece {
	public Reine(String nom, String couleur)  {
		super(nom, couleur);
	}

	public boolean estValide(Position depart, Position arrivee) {
		System.out.println("In estValide de la reine");
		if (depart == arrivee) return true;
		else if (( Math.abs(depart.getColonne()-arrivee.getColonne()) == Math.abs(depart.getLigne()-arrivee.getLigne()) )) return true;
		else if (depart.getColonne() == arrivee.getColonne()) return true;
		else if (depart.getLigne() == arrivee.getLigne()) return true;

		else return false;	
	}
}