
public class Fou extends Piece {

// Classe servant � d�terminer le comportement d'une reine

  public Fou(String nom ,String couleur) {
  super(nom, couleur);
  }


  public boolean estValide (Position depart, Position arrivee)
  {
	  if (depart == arrivee) return true;
	  else if (( Math.abs(depart.getColonne()-arrivee.getColonne()) == Math.abs(depart.getLigne()-arrivee.getLigne()) )) return true;
	  
	  else return false;
 
  }



}

