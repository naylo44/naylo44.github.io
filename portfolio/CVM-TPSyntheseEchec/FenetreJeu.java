import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FenetreJeu extends JFrame{
	Echiquier e;        //echiquier
	JLabel [][] tab;    //tableau de JLabels

	JPanel jPanel1 = new JPanel();  // panel du haut
	JPanel jPanel2 = new JPanel();  // panel du bas ( grille )
	GridLayout gridLayout1 = new GridLayout();

	JButton boutonDebuter = new JButton();
	JTextField champTexte = new JTextField();
	public FenetreJeu(){   // constructeur appelle méthode JBInit
		try{
			jbInit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception{

		tab = new JLabel[8][8];   // création du tableau de JLabel
		e = new Echiquier();      // création de l'échiquier

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(568, 585));
		this.setTitle("Jeu d'Echecs");
		jPanel1.setBounds(new Rectangle(5, 10, 550, 45));
		jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		jPanel1.setLayout(null);
		jPanel2.setBounds(new Rectangle(5, 65, 550, 465));
		jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		jPanel2.setLayout(gridLayout1);
		gridLayout1.setColumns(8);
		gridLayout1.setRows(8);
		this.getContentPane().add(jPanel2, null);
		jPanel1.add(champTexte, null);
		jPanel1.add(boutonDebuter, null);
		this.getContentPane().add(jPanel1, null);

		boutonDebuter.setBounds(new Rectangle(15, 10, 130, 25));
		boutonDebuter.setText("DEBUTER");

		champTexte.setBounds(new Rectangle(160, 10, 320, 25));

		// les écouteurs
		GestionnaireEvenement gest = new GestionnaireEvenement ();
		boutonDebuter.addMouseListener(gest);

		for ( int i = 0; i <8; i++ ){
			for ( int j = 0; j <8 ; j++ ){
				tab[i][j] = new JLabel(); // création du JLabel
				jPanel2.add(tab[i][j]);  // ajouter au Panel
				tab[i][j].setOpaque(true);
				tab[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // pour que les pieces apparaissent au centre de la case
				tab[i][j].addMouseListener(gest);  // ajouter l'écouteur aux sources


				if ( (i+j) % 2 == 0 )
					tab[i][j].setBackground(Color.lightGray );  //couleur des cases
				else
					tab[i][j].setBackground(Color.darkGray );
			}
		}
	}
	// main pour pouvoir exécuter l'interface graphique
	  public static void main ( String [] args )
	  {
	  FenetreJeu j = new FenetreJeu ();
	  j.setVisible(true);
	  j.setLocation(100, 130);
	  j.setDefaultCloseOperation(EXIT_ON_CLOSE);  // ferme le processus associé
	  }

	// classe privée (interne) pour la gestion d'Événements
	private class GestionnaireEvenement extends MouseAdapter{

		Piece pieceTampon;
		ImageIcon iconeTampon;
		int ligneClic;
		int colonneClic;
		Position depart, arrivee;
		String couleurControle = "blanc";
		boolean aBouge = false;
		
		public void mouseClicked(MouseEvent eve){
			if (eve.getSource() == boutonDebuter ) {
				e.debuter();
				
				for(int i=0; i<8;i++) {
					for (int j=0;j<8;j++) {
						tab[i][j].setIcon ( null);
					}
				}
				
				
				tab[0][0].setIcon ( new ImageIcon ("Icones/TB.gif"));
				tab[0][1].setIcon ( new ImageIcon ("Icones/CB.gif"));
				tab[0][2].setIcon ( new ImageIcon ("Icones/FB.gif"));
				tab[0][3].setIcon ( new ImageIcon ("Icones/RB.gif"));
				tab[0][4].setIcon ( new ImageIcon ("Icones/DB.gif"));
				tab[0][5].setIcon ( new ImageIcon ("Icones/FB.gif"));
				tab[0][6].setIcon ( new ImageIcon ("Icones/CB.gif"));
				tab[0][7].setIcon ( new ImageIcon ("Icones/TB.gif"));
				tab[7][0].setIcon ( new ImageIcon ("Icones/TN.gif"));
				tab[7][1].setIcon ( new ImageIcon ("Icones/CN.gif"));
				tab[7][2].setIcon ( new ImageIcon ("Icones/FN.gif"));
				tab[7][3].setIcon ( new ImageIcon ("Icones/RN.gif"));
				tab[7][4].setIcon ( new ImageIcon ("Icones/DN.gif"));
				tab[7][5].setIcon ( new ImageIcon ("Icones/FN.gif"));
				tab[7][6].setIcon ( new ImageIcon ("Icones/CN.gif"));
				tab[7][7].setIcon ( new ImageIcon ("Icones/TN.gif"));

				//Pions
				for ( int i = 0; i <8; i++ ) {
					tab[1][i].setIcon(new ImageIcon("Icones/PB.gif"));
					tab[6][i].setIcon(new ImageIcon("Icones/PN.gif"));
				}
				champTexte.setText(couleurControle);
			}
			
			else { // donc a clique sur un Label
				//trouver lequel
				aBouge = false;
				
				
				for ( int i = 0; i < 8 ; i++ ){
					for ( int j = 0; j<8; j++ ){
						if (eve.getSource() == tab[i][j]){
							ligneClic = i;
							colonneClic = j;
						}
					}
				}
				champTexte.setText(couleurControle);
				
				// 1er cas : clique sur une case occupee meme couleur que la couleur controle
				if (e.getCase(ligneClic, colonneClic).estOccupee()) {
					// Si couleur meme que coleur controle
					if (e.getCase(ligneClic, colonneClic).getPiece().getCouleur() == couleurControle) {
						// CAS 1: tampon vide : cas Depart
						if (pieceTampon==null) {
							//initialiser position depart
							depart = new Position ( ligneClic, colonneClic );
							//prendre l'icone et la mettre dans le tampon, prendre la piece et la mettre dans le tampon
							iconeTampon = (ImageIcon)tab[ligneClic][colonneClic].getIcon();
							pieceTampon = e.getCase(ligneClic, colonneClic ).getPiece();
							//enlever le tampon de la place d'origine
							tab[ligneClic][colonneClic].setIcon(null);
						}
						else {
							replacePiece();
						}
						
					// Couleur differente de couleur controle
					}
					else {
						// CAS 3 :Si tampon pas vide 
						if(pieceTampon != null) {
							arrivee = new Position(ligneClic, colonneClic);
							// Si déplacement valide
							if (pieceTampon.estValide(depart, arrivee)) {
								// Si chemin possible
								if (e.cheminPossible(depart, arrivee)) {
									deplacePiece();
									aBouge = true;
								}
								else {
									replacePiece();
								}
							
							// Si deplacement non valide, verifier si une capture par un pion est possible
							}
							else if (pieceTampon.getNom().contains("p") && e.captureParUnPionPossible(depart, arrivee)){
								deplacePiece();
								aBouge = true;
							}
							else {
								replacePiece();
							}
						}
					}
					
				// CAS 2 : Si case cliquee non occupee
				}
				else {
					if(pieceTampon != null){
						arrivee = new Position(ligneClic, colonneClic);
						// Si déplacement valide
						if (pieceTampon.estValide(depart, arrivee)){
							System.out.println("Movement valide");
							// Si chemin possible
							if (e.cheminPossible(depart, arrivee)){
								deplacePiece();
								aBouge = true;
							}
							else {
								replacePiece();
							}
						}
						else {
							replacePiece();
						}
					}
				}
			}
		}
	
		private void replacePiece(){
			tab[depart.getLigne()][depart.getColonne()].setIcon(iconeTampon);
			iconeTampon = null;
			pieceTampon = null;
			if (aBouge == true) {
				alterne();
				champTexte.setText(couleurControle);
			}
		}
		
		private void deplacePiece(){
			e.getCase(ligneClic, colonneClic).ajouterPiece(pieceTampon);
		 	pieceTampon = null;
		 	e.getCase(depart.getLigne(), depart.getColonne()).enleverPiece();
		 	tab[ligneClic][colonneClic].setIcon(iconeTampon);
		 	iconeTampon = null;
		 	alterne(); // fournie
		 	champTexte.setText(couleurControle);
		}
		
		public void alterne (){
			if (couleurControle == "blanc")
				couleurControle = "noir";
			else
				couleurControle = "blanc";
		}
	
	}//Fin classe interne

}//Fin FenetreJeu