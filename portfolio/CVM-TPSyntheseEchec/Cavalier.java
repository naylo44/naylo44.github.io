public class Cavalier extends Piece {
	//CONSTRUCTEUR
	public Cavalier(String nom, String couleur) 
	{
		super(nom, couleur);
	}

	//M�THODES
	public boolean estValide(Position depart, Position arrivee) 
	{
		if (norme(depart, arrivee) ==5 || norme(depart, arrivee)==0)
			return true;
		else
			return false;
	}
}