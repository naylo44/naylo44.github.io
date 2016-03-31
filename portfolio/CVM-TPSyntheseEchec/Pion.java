public class Pion extends Piece {
	//CONSTRUCTEUR
	public Pion(String nom, String couleur) 
	{
		super(nom, couleur); //constructeur de piece
	}

	//M�THODE
	public boolean estValide(Position depart, Position arrivee)  {
		if (depart.getColonne() == arrivee.getColonne()) { 
			if(getCouleur() == "blanc") {
				if(depart.getLigne() == 1) {
					if (arrivee.getLigne() >0 && arrivee.getLigne() <= 3)
						return true;
					else
						return false;
				}
				else {
					if(depart.getLigne() == arrivee.getLigne() || arrivee.getLigne() == depart.getLigne()+1)
						return true;
					else
						return false;
				}
			}
			else {  
				if(depart.getLigne() == 6) {
					if (arrivee.getLigne() <7 && arrivee.getLigne()>= 4)
						return true;
					else 
						return false;
				}
				else {
					if(depart.getLigne() == arrivee.getLigne() || arrivee.getLigne() == depart.getLigne()-1)
						return true;
					else
						return false;
				}
			}
		}
		else {
			return false;
		}
	}
}