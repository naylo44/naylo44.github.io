import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicTacToe extends JFrame{ 
	static int turn = 0;
	static boolean singleplayer = true;
	static TicTacToe frame;
	static int PlayerOneWins = 0;
	static int PlayerTwoWins = 0;
	static int gameWon = 0;
	static int move = 0;
	static boolean blocked = false;
	static int aiMove = 0;
	static int turns = 0;
	static final TTTPanel case1 = new TTTPanel();
	static final TTTPanel case2 = new TTTPanel();
	static final TTTPanel case3 = new TTTPanel();
	static final TTTPanel case4 = new TTTPanel();
	static final TTTPanel case5 = new TTTPanel();
	static final TTTPanel case6 = new TTTPanel();
	static final TTTPanel case7 = new TTTPanel();
	static final TTTPanel case8 = new TTTPanel();
	static final TTTPanel case9 = new TTTPanel();
	static ScorePanel score = new ScorePanel();


	TicTacToe() {

		this.setSize(500,580);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel game = new JPanel();

		JPanel newGame = new JPanel();
		JLabel newSingleplayer = new JLabel("|      New Single Player Game        |");

		newSingleplayer.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				singleplayer = true;
				gameWon = 0;
				turn=0;
				case1.reset();
				case2.reset();
				case3.reset();
				case4.reset();
				case5.reset();
				case6.reset();
				case7.reset();
				case8.reset();
				case9.reset();
				frame.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});




		JLabel newMultiplayer = new JLabel("|        New Two Player Game      |");
		newMultiplayer.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				singleplayer = false;
				gameWon=0;
				turn=0;
				case1.reset();
				case2.reset();
				case3.reset();
				case4.reset();
				case5.reset();
				case6.reset();
				case7.reset();
				case8.reset();
				case9.reset();
				frame.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});





		case1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case1.played) return;
				else if (turn==0 && gameWon==0) {
					case1.x=true;
					turn=1;
					case1.played=true;
					aiMove=0;
				}
				else if (turn==1 && case1.x==false && gameWon==0) {
					case1.o=true;
					turn=0;
					case1.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case2.played) return;
				else if (turn==0 && gameWon==0) {
					case2.x=true;
					turn=1;
					case2.played=true;
					aiMove=0;
				}
				else if (turn==1 && case2.x==false && gameWon==0) {
					case2.o=true;
					turn=0;
					case2.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case3.played) return;
				else if (turn==0 && gameWon==0) {
					case3.x=true;
					turn=1;
					case3.played = true;
					aiMove=0;
				}
				else if (turn==1 && case3.x==false && gameWon==0) {
					case3.o=true;
					turn=0;
					case3.played = true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case4.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case4.played) return;
				else if (turn==0 && gameWon==0) {
					case4.x=true;
					turn=1;
					case4.played=true;
					aiMove=0;
				}
				else if (turn==1 && case4.x==false && gameWon==0) {
					case4.o=true;
					turn=0;
					case4.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case5.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case5.played) return;
				else if (turn==0 && gameWon==0) {
					case5.x=true;
					turn=1;
					case5.played=true;
					aiMove=0;
				}
				else if (turn==1 && case5.x==false && gameWon==0) {
					case5.o=true;
					turn=0;
					case5.played = true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case6.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case6.played) return;
				else if (turn==0 && gameWon==0) {
					case6.x=true;
					turn=1;
					case6.played=true;
					aiMove=0;
				}
				else if (turn==1 && case6.x==false && gameWon==0) {
					case6.o=true;
					turn=0;
					case6.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case7.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case7.played) return;
				else if (turn==0 && gameWon==0) {
					case7.x=true;
					turn=1;
					case7.played=true;
					aiMove=0;
				}
				else if (turn==1 && case7.x==false && gameWon==0) {
					case7.o=true;
					turn=0;
					case7.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case8.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case8.played) return;
				else if (turn==0 && gameWon==0) {
					case8.x=true;
					turn=1;
					aiMove=0;
					case8.played=true;
				}
				else if (turn==1 && case8.x==false && gameWon==0) {
					case8.o=true;
					turn=0;
					case8.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		case9.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (case9.played) return;
				else if (turn==0 ) {
					case9.x=true;
					aiMove=0;
					turn=1;
					case9.played = true;
				}
				else if (turn==1 && case9.x==false && gameWon==0) {
					case9.o=true;
					turn=0;
					case9.played=true;
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		newGame.add(newSingleplayer, BorderLayout.WEST);
		newGame.add(newMultiplayer, BorderLayout.EAST);

		this.add(newGame, BorderLayout.NORTH);
		game.setLayout(new GridLayout(3,3));
		game.add(case1);
		game.add(case2);
		game.add(case3);
		game.add(case4);
		game.add(case5);
		game.add(case6);
		game.add(case7);
		game.add(case8);
		game.add(case9);
		this.add(game, BorderLayout.CENTER);


		score.setSize(this.getWidth(), 50);

		this.add(score, BorderLayout.SOUTH);


		this.setVisible(true);
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		frame = new TicTacToe();
		frame.setVisible(true);



		while (true) {
			Thread.sleep(10);
			if (turn==0)  {
				blocked = false;
			}
			//vertical wins X
			if (case1.x && case4.x && case7.x && gameWon==0) {
				PlayerOneWins++;
				gameWon=1;
			}
			if (case2.x && case5.x && case8.x && gameWon==0) {
				PlayerOneWins++;
				gameWon=1;
			}
			if (case3.x && case6.x && case9.x && gameWon==0) {
				PlayerOneWins++;
				gameWon=1;
			}

			//horizontal wins X
			if (case1.x && case2.x && case3.x && gameWon==0) {
				PlayerOneWins++;
				gameWon=1;
			}
			if (case4.x && case5.x && case6.x && gameWon==0){
				PlayerOneWins++;
				gameWon=1;
			}
			if (case7.x && case8.x && case9.x && gameWon==0) {
				PlayerOneWins++;
				gameWon=1;
			}

			//diagonal wins X
			if (case1.x && case5.x && case9.x && gameWon==0){
				gameWon=1;
				PlayerOneWins++;
			}
			if (case3.x && case5.x && case7.x && gameWon==0) {
				PlayerOneWins++;
				gameWon = 1;
			}


			//vertical wins O
			if (case1.o && case4.o && case7.o && gameWon ==0 ){
				PlayerTwoWins++;
				gameWon=1;
			}
			if (case2.o && case5.o && case8.o && gameWon ==0 ){
				gameWon=1;
				PlayerTwoWins++;
			}
			if (case3.o && case6.o && case9.o && gameWon ==0 ) {
				gameWon=1;
				PlayerTwoWins++;
			}

			//horizontal wins O
			if (case1.o && case2.o && case3.o && gameWon == 0) {
				gameWon=1;
				PlayerTwoWins++;
			}
			if (case4.o && case5.o && case6.o && gameWon == 0) {
				gameWon=1;
				PlayerTwoWins++;
			}
			if (case7.o && case8.o && case9.o && gameWon==0) {
				gameWon=1;
				PlayerTwoWins++;
			}

			//diagonal wins O
			if (case1.o && case5.o && case9.o && gameWon==0){
				gameWon=1;
				PlayerTwoWins++;
			}
			if (case3.o && case5.o && case7.o && gameWon ==0) {
				gameWon=1;
				PlayerTwoWins++;
			}

			
			
			
			if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
				if (case5.o==false && case5.x==false && blocked ==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case5.o==false && case5.x && blocked==false) {
					if (case1.o==false && case1.x==false) {
						case1.o=true;
						blocked=true;
						aiMove=1;
						turn=0;
					}
					
					
				}
				
				if (case1.x && case5.o && case9.x && blocked==false && case6.o==false && case6.x==false) {
					case6.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case3.x && case5.o && case7.x && blocked==false && blocked==false && case4.o==false && case4.x==false) {
					case4.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.o && case2.o && case3.o==false && blocked==false && case3.x==false) {		//horizontal blocking last case
					case3.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case4.o && case5.o && case6.o ==false && blocked==false && case6.x==false) {
					case6.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case7.o && case8.o && case9.o==false && blocked==false && case9.x==false) {
					case9.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case3.o && case2.o && case1.o==false && blocked==false && case1.x==false) {		//horizontal blocking first case
					case1.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case6.o && case5.o && case4.o==false && blocked==false && case4.x==false) {
					case4.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case9.o && case8.o && case7.o == false && blocked==false && case7.x==false) {
					case7.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case1.o && case3.o && case2.o==false && blocked==false && case2.x==false) {		//middle blocking first case
					case2.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case4.o && case6.o && case5.o==false && blocked==false && case5.x==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case7.o && case9.o && case8.o == false && blocked==false && case8.x==false) {
					case8.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.o && case4.o && case7.o==false && blocked==false && case7.x==false) {		//last case vertical blocking
					case7.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case2.o && case5.o && case8.o==false && blocked==false && case8.x==false) {
					case8.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.o && case6.o && case9.o==false && blocked==false && case9.x==false) {
					case9.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.o && case7.o && case4.o==false && blocked==false && case4.x==false) {		//middle case vertical blocking
					case4.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case2.o && case8.o && case5.o==false && blocked==false && case5.x==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.o && case9.o && case6.o==false && blocked==false && case6.x==false) {
					case6.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case4.o && case7.o && case1.o==false && blocked==false && case1.x==false) {		//first case vertical blocking
					case1.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case5.o && case8.o && case2.o==false && blocked==false && case2.x==false) {
					case2.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case6.o && case9.o && case3.o==false && blocked==false && case3.x==false) {
					case3.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case1.o && case5.o && case9.o==false && blocked==false && case9.x==false) {		//last case diagonal check
					case9.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.o && case5.o && case7.o==false && blocked==false && case7.x==false) {
					case7.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.o && case9.o && case5.o==false && blocked==false && case5.x==false) {		//middle case diagonal check
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.o && case7.o && case5.o==false && blocked==false && case5.x==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case5.o && case9.o && case1.o==false && blocked==false && case1.x==false) {		//first case diagonal check
					case1.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case5.o && case7.o && case3.o==false && blocked==false && case3.x==false) {
					case3.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
			}
			
			
			if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
				if (case5.o==false && case5.x==false && blocked ==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case1.x && case2.x && case3.o==false && blocked==false) {		//horizontal blocking last case
					case3.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case4.x && case5.x && case6.o ==false && blocked==false) {
					case6.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case7.x && case8.x && case9.o==false && blocked==false) {
					case9.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case3.x && case2.x && case1.o==false && blocked==false) {		//horizontal blocking first case
					case1.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case6.x && case5.x && case4.o==false && blocked==false) {
					case4.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case9.x && case8.x && case7.o == false && blocked==false) {
					case7.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case1.x && case3.x && case2.o==false && blocked==false) {		//middle blocking first case
					case2.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case4.x && case6.x && case5.o==false && blocked==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case7.x && case9.x && case8.o == false && blocked==false) {
					case8.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.x && case4.x && case7.o==false && blocked==false) {		//last case vertical blocking
					case7.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case2.x && case5.x && case8.o==false && blocked==false) {
					case8.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.x && case6.x && case9.o==false && blocked==false) {
					case9.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.x && case7.x && case4.o==false && blocked==false) {		//middle case vertical blocking
					case4.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case2.x && case8.x && case5.o==false && blocked==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.x && case9.x && case6.o==false && blocked==false) {
					case6.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case4.x && case7.x && case1.o==false && blocked==false) {		//first case vertical blocking
					case1.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case5.x && case8.x && case2.o==false && blocked==false) {
					case2.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case6.x && case9.x && case3.o==false && blocked==false) {
					case3.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case1.x && case5.x && case9.o==false && blocked==false) {		//last case diagonal check
					case9.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.x && case5.x && case7.o==false && blocked==false) {
					case7.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				if (case1.x && case9.x && case5.o==false && blocked==false) {		//middle case diagonal check
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case3.x && case7.x && case5.o==false && blocked==false) {
					case5.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				if (case5.x && case9.x && case1.o==false && blocked==false) {		//first case diagonal check
					case1.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				if (case5.x && case7.x && case3.o==false && blocked==false) {
					case3.o=true;
					blocked=true;
					aiMove=1;
					turn=0;
				}
				
				
				frame.repaint();
			}
			while (turn==1 && aiMove==0 && blocked==false) {
				move=(int) (Math.random()*9);

				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 0 && case1.x==false && case1.o==false) {
						case1.o=true;
						turn=0;
						aiMove=1;
					}
				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 1 && case2.x==false && case2.o==false) {
						case2.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 2 && case3.x==false && case3.o==false) {
						case3.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 3 && case4.x==false && case4.o==false) {
						case4.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 4 && case5.x==false && case5.o==false) {
						case5.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 5 && case6.x==false && case6.o==false) {
						case6.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 6 && case7.x==false && case7.o==false) {
						case7.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 7 && case8.x==false && case8.o==false) {
						case8.o=true;
						turn=0;
						aiMove=1;
					}

				}
				move=(int) (Math.random()*9);
				if (singleplayer && gameWon==0 && turn==1 && aiMove==0) {
					if (move == 8 && case9.x==false && case9.o==false) {
						case9.o=true;
						turn=0;
						aiMove=1;
					}
				}
			}
			frame.repaint();
			score.repaint();
			frame.revalidate();
			score.revalidate();

		}
	}
}