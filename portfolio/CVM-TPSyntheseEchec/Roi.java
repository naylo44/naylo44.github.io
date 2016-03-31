
public class Roi extends Piece {

// Classe servant � d�terminer le comportement d'une reine

  public Roi(String nom ,String couleur) {
  super(nom, couleur);
  }


  public boolean estValide (Position depart, Position arrivee)
  {
	  if (depart == arrivee) return true;
	  else if (Math.abs(depart.getColonne()-arrivee.getColonne()) == 1 && depart.getLigne() == arrivee.getLigne()) return true;
	  else if (Math.abs(depart.getLigne()-arrivee.getLigne()) == 1 && depart.getColonne() == arrivee.getColonne()) return true;
	  else if (Math.abs(depart.getColonne()-arrivee.getColonne()) == 0 && depart.getLigne() == arrivee.getLigne()) return true;
	  else if (Math.abs(depart.getLigne()-arrivee.getLigne()) == 1 && depart.getColonne() == arrivee.getColonne()) return true;

	  else if (Math.abs(depart.getColonne()-arrivee.getColonne()) == 1 && Math.abs(depart.getLigne() - arrivee.getLigne()) == 1) return true;
	  else if (Math.abs(depart.getLigne()-arrivee.getLigne()) == 1 && Math.abs(depart.getColonne() - arrivee.getColonne()) == 1) return true;

	  else return false;
 
  }



}

