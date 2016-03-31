import java.util.Vector;

public class Echiquier implements MethodesEchiquier {
	private Case location [][];
	private Vector<Case> milieu;
	public boolean obstrue;

	public Echiquier() {
		location = new Case[8][8];
		for (int i =0; i< 8; i++) {
			for (int j = 0; j<8; j++) {
				location[i][j] = new Case();
			}
		}
	}
	
	public void delAll()
	{
		for (int i=0 ; i<8;i++)
		{
			for(int j =0; j<8;j++)
			{
				location[i][j].ajouterPiece(null);
			}
		}
	}

	//Méthode à compléter 
	public void debuter() {
		delAll();			//Delete all, cause errors
		milieu = new Vector<Case>();
		int k;
		for (k=0; k < 8; k++) {
			location[1][k].ajouterPiece(new Pion("p" + k, "blanc"));
			location[6][k].ajouterPiece(new Pion("p" + k, "noir"));
		}
		//Les tours blanches
		location[0][0].ajouterPiece(new Tour("t1", "blanc"));
		location[0][7].ajouterPiece(new Tour("t2", "blanc"));
		//noires
		location[7][0].ajouterPiece(new Tour("t1", "noir"));
		location[7][7].ajouterPiece(new Tour("t2", "noir"));
		
		//les cavaliers blancs
		location[0][1].ajouterPiece(new Cavalier("c1", "blanc"));
		location[0][6].ajouterPiece(new Cavalier("c2", "blanc"));
		//noirs
		location[7][1].ajouterPiece(new Cavalier("c1", "noir"));
		location[7][6].ajouterPiece(new Cavalier("c2", "noir"));
		
		//Les fous blancs
		location[0][2].ajouterPiece(new Fou("f1", "blanc"));
		location[0][5].ajouterPiece(new Fou("f2", "blanc"));
		//noirs
		location[7][2].ajouterPiece(new Fou("f1", "noir"));
		location[7][5].ajouterPiece(new Fou("f2", "noir"));
		
		//Les rois blanc
		location[0][3].ajouterPiece(new Roi("k", "blanc"));
		//noirs
		location[7][3].ajouterPiece(new Roi("k", "noir"));
		
		//Les reines blanche
		location[0][4].ajouterPiece(new Reine("r", "blanc"));
		//noire
		location[7][4].ajouterPiece(new Reine("r", "noir"));
		
	}

	public Case getCase ( int ligne, int colonne )
	{
		return location[ligne][colonne];
	}

	//Méthode à compléter 
	public boolean captureParUnPionPossible(Position depart, Position arrivee) 
	{
		int diffLigne = arrivee.getLigne() - depart.getLigne();
		int diffColonne = arrivee.getColonne() - depart.getColonne();
		if (Math.abs(diffLigne) == Math.abs(diffColonne))  //diagonale pion
			if(location[depart.getLigne()][depart.getColonne()].getPiece().getCouleur() == "blanc")
				if(diffLigne == 1 && location[arrivee.getLigne()][arrivee.getColonne()].getPiece().getCouleur()=="noir")
					return true;
				else
					return false;
			else
			{ 
				if (diffLigne == -1 && location[arrivee.getLigne()][arrivee.getColonne()].getPiece().getCouleur()=="blanc")
					return true;
				else
					return false;
			}
		else
			return false;
		
	}

	//Méthode à compléter   
	
	public boolean cheminPossible(Position depart, Position arrivee) 
	{
		boolean blnCheminPossible = true;
		int diffLigne = arrivee.getLigne() - depart.getLigne();
		int diffColonne = arrivee.getColonne() - depart.getColonne();
		
		if (diffColonne == 0)//verticale
		{ 
			if (diffLigne < 0)
				for (int i = depart.getLigne()-1; i > Math.abs(arrivee.getLigne()); i-- )
				{
					if (location[i][depart.getColonne()].estOccupee())
						blnCheminPossible = false;
				}
			else
			{  
				for (int i = depart.getLigne()+1; i < Math.abs(arrivee.getLigne()); i++ )
				{
					if (location[i][depart.getColonne()].estOccupee())
						blnCheminPossible = false;
				}
			}
		}
		else if (diffLigne == 0)//horizontale
		{
			if (diffLigne < 0)
				for (int i = depart.getColonne()-1; i > Math.abs(diffColonne); i-- )
				{
					if (location[depart.getLigne()][i].estOccupee())
						blnCheminPossible = false;
				}
			else
				for (int i = depart.getColonne()+1; i < Math.abs(diffColonne); i++ )
				{
					if (location[depart.getLigne()][i].estOccupee())
						blnCheminPossible = false;
				}
		}
		else if (Math.abs(diffLigne) == Math.abs(diffColonne))//diagonale
		{
			if (diffLigne < 0 && diffColonne < 0)
				for (int i = 1; i < Math.abs(diffColonne); i++ )
				{
					if (location[depart.getLigne()-i][depart.getColonne()-i].estOccupee())
						blnCheminPossible = false;
				}
			else if (diffLigne > 0 && diffColonne > 0)  
				for (int i = 1; i < Math.abs(diffColonne); i++ )
				{
					if (location[depart.getLigne()+i][depart.getColonne()+i].estOccupee())
						blnCheminPossible = false;
				}
			else if (diffLigne < 0 && diffColonne > 0)
				for (int i = 1; i < Math.abs(diffColonne); i++ )
				{
					if (location[depart.getLigne()-i][depart.getColonne()+i].estOccupee())
						blnCheminPossible = false;
				}
			else if (diffLigne > 0 && diffColonne < 0) 
				for (int i = 1; i < Math.abs(diffColonne); i++ )
				{
					if (location[depart.getLigne()+i][depart.getColonne()-i].estOccupee())
						blnCheminPossible = false;
				}
		}
		
		if (blnCheminPossible)
		{
			if(location[depart.getLigne()][depart.getColonne()].getPiece().getNom().charAt(0)== 'p')
			{
				if (location[arrivee.getLigne()][arrivee.getColonne()].estOccupee()){ // case arrive non occupee
					return false;
				}else
					return true;
			}
			else 			
				return true;
		}
		else
		{
			return false;	
		}
	}
	
	
	
	
	//NON FONCTIONELLE (ESSAI AVEC DISTANCES)
/*	public boolean cheminPossible ( Position  depart , Position arrivee) {
		obstrue = false;
		if (location[depart.getLigne()][depart.getColonne()].getPiece().estValide(depart, arrivee)) {
			if (location[depart.getLigne()][depart.getColonne()].getPiece().getNom().contains("c")) {
				return true;
			}
			
			//Essai de get les cases entre points si en diag ou en ligne (Je ne suis vraiment pas clair)
			//Pour toutes les cases, si dist(A,C) + dist(B,C) == dist(A,B), le point/case C est sur la ligne/diag
			milieu.clear();
			for (int i = 0; i<8; i++) {
				for (int j=0; j<8; j++) {
					
					if (Math.sqrt(Math.pow((depart.getLigne()-i),2) + Math.pow((depart.getColonne()-j),2)) == location[depart.getLigne()][depart.getColonne()].getPiece().norme(depart, arrivee)) {
						milieu.add(location[i][j]);
					}
				}
			}
			
			for (int i = 0; i<milieu.size(); i++) {
				if (milieu.elementAt(i).estOccupee()) {
					obstrue = false;
					return false;
				}
			}
			
			if (obstrue) return false;
			else return true;
		}
		else return false;
	}

	/*public boolean roquePossible (Position depart, Position arrivee ) 
{
}

public boolean priseEnPassantPossible( Position depart, Position arrivee )
{
}

public boolean promotionPossible (Position depart, Position arrivee)
{
if ( location [depart.getLigne()][depart.getColonne()].getPiece().getCouleur() == "blanc" )
/*
public static void main ( String [] args )
{
Echiquier e = new Echiquier ();
e.debuter();
	 */
}