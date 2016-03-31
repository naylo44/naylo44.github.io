
public class Tour extends Piece {

// Classe servant � d�terminer le comportement d'une reine

  public Tour(String nom ,String couleur) {
  super(nom, couleur);
  }


  public boolean estValide (Position depart, Position arrivee)
  {
	  if (depart == arrivee) return true;
	  else if (depart.getColonne() == arrivee.getColonne()) return true;
	  else if (depart.getLigne() == arrivee.getLigne()) return true;
	  
	  
	  else return false;
 
  }



}

