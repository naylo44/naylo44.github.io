
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class ConnectFour extends JApplet implements ActionListener
{
	static Random randomGenerator = new Random();
	public ConnectFour()
	{
		game game = new game();
		JButton restart = new JButton("Start Over");
		JPanel p1 = new JPanel();
		setLayout(new BorderLayout());
		p1.setLayout(new FlowLayout());

		add(game, BorderLayout.CENTER);
		p1.add(restart);
		add(p1, BorderLayout.SOUTH);

		restart.addActionListener(this);
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.add(new ConnectFour());
		frame.setTitle("Connect Four");
		frame.setSize(600 , 600);
		frame.setLocationRelativeTo(null); //Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);



	}

	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		game.restart();
	}

	
	
	
	
	
	
	static class game extends JPanel {
		static int[][] game = new int[7][6];
		static int turn = 1;
		static int repeat = 2;
		static int temp = 2;
		static int change = 0;
		static int s1 = 0;
		static int s2 = 0;
		static int s3 = 0;
		static int s4 = 0;
		static int s5 = 0;
		static int s6 = 0;
		static int s7 = 0;
		static int s8 = 0;
		static int once = 0;
		static int mouseListen = 0;
		static Boolean canClick = true;
		static Boolean canMove = true;

		game()
		{
			setLayout(new GridLayout(6,7));
		}

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//background
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, getWidth(), getHeight());

			//when mouse is clicked
			if (mouseListen==0) {
				mouseListen=1;
				addMouseListener (new MouseListener()

				{

					public void mouseReleased(MouseEvent m) {
						// TODO Auto-generated method stub
						if(canClick){

							temp -= 1;

							if (temp == 0)
							{
								repeat += 1;
								temp = repeat;

								if(m.getX() < getWidth()/7*7 && m.getX() >= getWidth()/7*6)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[6][5] != 1 && game[6][5] != -1)
										{
											game[6][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[6][5] != 0)
											if(game[6][4] != 1 && game[6][4] != -1)
											{
												game[6][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[6][4] != 0)
											if(game[6][3] != 1 && game[6][3] != -1)
											{
												game[6][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[6][3] != 0)
											if(game[6][2] != 1 && game[6][2] != -1)
											{
												game[6][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[6][2] != 0)
											if(game[6][1] != 1 && game[6][1] != -1)
											{
												game[6][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[6][1] != 0)
											if(game[6][0] != 1 && game[6][0] != -1)
											{
												game[6][0] = turn; 
												turn *= -1;
											}
									}
								}

								else if(m.getX() < getWidth()/7*6 && m.getX() >= getWidth()/7*5)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[5][5] != 1 && game[5][5] != -1)
										{
											game[5][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[5][5] != 0)
											if(game[5][4] != 1 && game[5][4] != -1)
											{
												game[5][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[5][4] != 0)
											if(game[5][3] != 1 && game[5][3] != -1)
											{
												game[5][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[5][3] != 0)
											if(game[5][2] != 1 && game[5][2] != -1)
											{
												game[5][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[5][2] != 0)
											if(game[5][1] != 1 && game[5][1] != -1)
											{
												game[5][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[5][1] != 0)
											if(game[5][0] != 1 && game[5][0] != -1)
											{
												game[5][0] = turn; 
												turn *= -1;
											}
									}
								}

								else if(m.getX() < getWidth()/7*5 && m.getX() >= getWidth()/7*4)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[4][5] != 1 && game[4][5] != -1)
										{
											game[4][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[4][5] != 0)
											if(game[4][4] != 1 && game[4][4] != -1)
											{
												game[4][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[4][4] != 0)
											if(game[4][3] != 1 && game[4][3] != -1)
											{
												game[4][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[4][3] != 0)
											if(game[4][2] != 1 && game[4][2] != -1)
											{
												game[4][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[4][2] != 0)
											if(game[4][1] != 1 && game[4][1] != -1)
											{
												game[4][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[4][1] != 0)
											if(game[4][0] != 1 && game[4][0] != -1)
											{
												game[4][0] = turn; 
												turn *= -1;
											}
									}
								}

								else if(m.getX() < getWidth()/7*4 && m.getX() >= getWidth()/7*3)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[3][5] != 1 && game[3][5] != -1)
										{
											game[3][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[3][5] != 0)
											if(game[3][4] != 1 && game[3][4] != -1)
											{
												game[3][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[3][4] != 0)
											if(game[3][3] != 1 && game[3][3] != -1)
											{
												game[3][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[3][3] != 0)
											if(game[3][2] != 1 && game[3][2] != -1)
											{
												game[3][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[3][2] != 0)
											if(game[3][1] != 1 && game[3][1] != -1)
											{
												game[3][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[3][1] != 0)
											if(game[3][0] != 1 && game[3][0] != -1)
											{
												game[3][0] = turn; 
												turn *= -1;
											}
									}
								}

								else if(m.getX() < getWidth()/7*3 && m.getX() >= getWidth()/7*2)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[2][5] != 1 && game[2][5] != -1)
										{
											game[2][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[2][5] != 0)
											if(game[2][4] != 1 && game[2][4] != -1)
											{
												game[2][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[2][4] != 0)
											if(game[2][3] != 1 && game[2][3] != -1)
											{
												game[2][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[2][3] != 0)
											if(game[2][2] != 1 && game[2][2] != -1)
											{
												game[2][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[2][2] != 0)
											if(game[2][1] != 1 && game[2][1] != -1)
											{
												game[2][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[2][1] != 0)
											if(game[2][0] != 1 && game[2][0] != -1)
											{
												game[2][0] = turn; 
												turn *= -1;
											}
									}
								}

								else if(m.getX() < getWidth()/7*2 && m.getX() >= getWidth()/7*1)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[1][5] != 1 && game[1][5] != -1)
										{
											game[1][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[1][5] != 0)
											if(game[1][4] != 1 && game[1][4] != -1)
											{
												game[1][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[1][4] != 0)
											if(game[1][3] != 1 && game[1][3] != -1)
											{
												game[1][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[1][3] != 0)
											if(game[1][2] != 1 && game[1][2] != -1)
											{
												game[1][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[1][2] != 0)
											if(game[1][1] != 1 && game[1][1] != -1)
											{
												game[1][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[1][1] != 0)
											if(game[1][0] != 1 && game[1][0] != -1)
											{
												game[1][0] = turn; 
												turn *= -1;
											}
									}
								}

								else if(m.getX() < getWidth()/7*1)
								{
									if(m.getY() < getHeight()/6*6 && m.getY() >= getHeight()/6*5)
									{
										if(game[0][5] != 1 && game[0][5] != -1)
										{
											game[0][5] = turn; 
											turn *= -1;
										}
									}

									else if(m.getY() < getHeight()/6*5 && m.getY() >= getHeight()/6*4)
									{
										if(game[0][5] != 0)
											if(game[0][4] != 1 && game[0][4] != -1)
											{
												game[0][4] = turn;
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*4 && m.getY() >= getHeight()/6*3)
									{
										if(game[0][4] != 0)
											if(game[0][3] != 1 && game[0][3] != -1)
											{
												game[0][3] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*3 && m.getY() >= getHeight()/6*2)
									{
										if(game[0][3] != 0)
											if(game[0][2] != 1 && game[0][2] != -1)
											{
												game[0][2] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*2 && m.getY() >= getHeight()/6*1)
									{
										if(game[0][2] != 0)
											if(game[0][1] != 1 && game[0][1] != -1)
											{
												game[0][1] = turn; 
												turn *= -1;
											}
									}

									else if(m.getY() < getHeight()/6*1)
									{
										if(game[0][1] != 0)
											if(game[0][0] != 1 && game[0][0] != -1)
											{
												game[0][0] = turn; 
												turn *= -1;
											}
									}
								}
								mouseListen=0;
								repaint();
							}}}

					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
			}

			// draw circles
			for (int y = 0; y <= 5; y++)
				for (int x = 0; x <= 6; x++)

					if(game[x][y] == 0)
					{
						g.setColor(Color.WHITE);
						g.fillOval(getWidth()/7*x+5, getHeight()/6*y+5, getWidth()/7-5, getHeight()/6-5);
					}
					else if (game[x][y] == 1)
					{
						g.setColor(Color.RED);
						g.fillOval(getWidth()/7*x+5, getHeight()/6*y+5, getWidth()/7-5, getHeight()/6-5);
					}
					else if (game[x][y] == -1)
					{
						g.setColor(Color.YELLOW);
						g.fillOval(getWidth()/7*x+5, getHeight()/6*y+5, getWidth()/7-5, getHeight()/6-5);
					}

					else if (game[x][y] == 2)
					{
						g.setColor(Color.GREEN);
						g.fillOval(getWidth()/7*x+5, getHeight()/6*y+5, getWidth()/7-5, getHeight()/6-5);
					}

					else if (game[x][y] == 3 && turn==-1)
					{
						g.setColor(Color.RED); 			//if my turn, flash green/red
						g.fillOval(getWidth()/7*x+5, getHeight()/6*y+5, getWidth()/7-5, getHeight()/6-5);
					}

					else if (game[x][y] == 3 && turn==1)
					{
						g.setColor(Color.YELLOW); 			//if opponent's turn, change it yellow/green
						g.fillOval(getWidth()/7*x+5, getHeight()/6*y+5, getWidth()/7-5, getHeight()/6-5);
					}

			// check wins
			if(search(game)[2] != 0 && search(game)[4] != 0 || search(game)[3] != 0 && search(game)[5] != 0)
			{
				canClick = false;
				canMove = false;

				int s1 = search(game)[0];
				int s2 = search(game)[1];
				int s3 = search(game)[2];
				int s4 = search(game)[3];
				int s5 = search(game)[4];
				int s6 = search(game)[5];
				int s7 = search(game)[6];
				int s8 = search(game)[7];

				game[s1][s2] = 2;
				game[s3][s4] = 2;
				game[s5][s6] = 2;
				game[s7][s8] = 2;

				flash();
				repaint();
			}

			//Comp moves
			if(canMove)
			{
				int[][] toCheck0 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck0[x][y] = game[x][y];
				int[][] toCheck1 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck1[x][y] = game[x][y];
				int[][] toCheck2 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck2[x][y] = game[x][y];
				int[][] toCheck3 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck3[x][y] = game[x][y];
				int[][] toCheck4 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck4[x][y] = game[x][y];
				int[][] toCheck5 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck5[x][y] = game[x][y];
				int[][] toCheck6 = new int[7][6];
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						toCheck6[x][y] = game[x][y];

				int once0 = 0;
				int once1 = 0;
				int once2 = 0;
				int once3 = 0;
				int once4 = 0;
				int once5 = 0;
				int once6 = 0;

				// IF // make each move in toCheck with "1", send to check, compare the toCheck VS game !WRITE METHOD -RETURN X,Y-, put X,Y difference in game !WRITE METHOD
				if (turn == -1)
				{
					for (int y = 5; y >= 0; y--)
					{
						if (y == 5 && toCheck0[0][y] == 0 && once0 == 0)
						{
							toCheck0[0][y] = 1;
							once0 = 1;
						}
						else if (y != 5 && toCheck0[0][y] == 0 && once0 == 0){
							if(toCheck0[0][y+1] != 0)
							{
								toCheck0[0][y] = 1;
								once0 = 1;
							}}

						if (y == 5 && toCheck1[1][y] == 0 && once1 == 0)
						{
							toCheck1[1][y] = 1;
							once1 = 1;
						}
						else if (y != 5 && toCheck1[1][y] == 0 && once1 == 0)
							if(toCheck1[1][y+1] != 0)
							{
								toCheck1[1][y] = 1;
								once1 = 1;
							}

						if (y == 5 && toCheck2[2][y] == 0 && once2 == 0)
						{
							toCheck2[2][y] = 1;
							once2 = 1;
						}
						else if (y != 5 && toCheck2[2][y] == 0 && once2 == 0)
							if(toCheck2[2][y+1] != 0)
							{
								toCheck2[2][y] = 1;
								once2 = 1;
							}

						if (y == 5 && toCheck3[3][y] == 0 && once3 == 0)
						{
							toCheck3[3][y] = 1;
							once3 = 1;
						}
						else if (y != 5 && toCheck3[3][y] == 0 && once3 == 0)
							if(toCheck3[3][y+1] != 0)
							{
								toCheck3[3][y] = 1;
								once3 = 1;
							}

						if (y == 5 && toCheck4[4][y] == 0 && once4 == 0)
						{
							toCheck4[4][y] = 1;
							once4 = 1;
						}
						else if (y != 5 && toCheck4[4][y] == 0 && once4 == 0)
							if(toCheck4[4][y+1] != 0)
							{
								toCheck4[4][y] = 1;
								once4 = 1;
							}

						if (y == 5 && toCheck5[5][y] == 0 && once5 == 0)
						{
							toCheck5[5][y] = 1;
							once5 = 1;
						}
						else if (y != 5 && toCheck5[5][y] == 0 && once5 == 0)
							if(toCheck5[5][y+1] != 0)
							{
								toCheck5[5][y] = 1;
								once5 = 1;
							}

						if (y == 5 && toCheck6[6][y] == 0 && once6 == 0)
						{
							toCheck6[6][y] = 1;
							once6 = 1;
						}
						else if (y != 5 && toCheck6[6][y] == 0 && once6 == 0)
							if(toCheck6[6][y+1] != 0)
							{
								toCheck6[6][y] = 1;
								once6 = 1;
							}
					}

					for (int y = 0; y <= 5; y++)
					{
						for (int x = 0; x <= 6; x++)
							this.getSize();
					}



					if(search(toCheck0)[2] != 0 && search(toCheck0)[4] != 0 || search(toCheck0)[3] != 0 && search(toCheck0)[5] != 0)
					{
						game[compare(game, toCheck0)[0]][compare(game, toCheck0)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck1)[2] != 0 && search(toCheck1)[4] != 0 || search(toCheck1)[3] != 0 && search(toCheck1)[5] != 0)
					{
						game[compare(game, toCheck1)[0]][compare(game, toCheck1)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck2)[2] != 0 && search(toCheck2)[4] != 0 || search(toCheck2)[3] != 0 && search(toCheck2)[5] != 0)
					{
						game[compare(game, toCheck2)[0]][compare(game, toCheck2)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck3)[2] != 0 && search(toCheck3)[4] != 0 || search(toCheck3)[3] != 0 && search(toCheck3)[5] != 0)
					{
						game[compare(game, toCheck3)[0]][compare(game, toCheck3)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck4)[2] != 0 && search(toCheck4)[4] != 0 || search(toCheck4)[3] != 0 && search(toCheck4)[5] != 0)
					{
						game[compare(game, toCheck4)[0]][compare(game, toCheck4)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck5)[2] != 0 && search(toCheck5)[4] != 0 || search(toCheck5)[3] != 0 && search(toCheck5)[5] != 0)
					{
						game[compare(game, toCheck5)[0]][compare(game, toCheck5)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck6)[2] != 0 && search(toCheck6)[4] != 0 || search(toCheck6)[3] != 0 && search(toCheck6)[5] != 0)
					{
						game[compare(game, toCheck6)[0]][compare(game, toCheck6)[1]] = -1;
						turn *= -1;
					}
				}

				// IF ELSE // make each move in toCheck with "-1", send to check, compare the toCheck VS game !WRITE METHOD -RETURN X,Y-, put X,Y difference in game !WRITE METHOD
				if (turn == -1)
				{
					toCheck0 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck0[x][y] = game[x][y];
					toCheck1 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck1[x][y] = game[x][y];
					toCheck2 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck2[x][y] = game[x][y];
					toCheck3 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck3[x][y] = game[x][y];
					toCheck4 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck4[x][y] = game[x][y];
					toCheck5 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck5[x][y] = game[x][y];
					toCheck6 = new int[7][6];
					for (int y = 0; y <= 5; y++)
						for (int x = 0; x <= 6; x++)
							toCheck6[x][y] = game[x][y];

					once0 = 0;
					once1 = 0;
					once2 = 0;
					once3 = 0;
					once4 = 0;
					once5 = 0;
					once6 = 0;

					for (int y = 5; y >= 0; y--)
					{
						if (y == 5 && toCheck0[0][y] == 0 && once0 == 0)
						{
							toCheck0[0][y] = -1;
							once0 = 1;
						}
						else if (y != 5 && toCheck0[0][y] == 0 && once0 == 0)
							if(toCheck0[0][y+1] != 0)
							{
								toCheck0[0][y] = -1;
								once0 = 1;
							}

						if (y == 5 && toCheck1[1][y] == 0 && once1 == 0)
						{
							toCheck1[1][y] = -1;
							once1 = 1;
						}
						else if (y != 5 && toCheck1[1][y] == 0 && once1 == 0)
							if(toCheck1[1][y+1] != 0)
							{
								toCheck1[1][y] = -1;
								once1 = 1;
							}

						if (y == 5 && toCheck2[2][y] == 0 && once2 == 0)
						{
							toCheck2[2][y] = -1;
							once2 = 1;
						}
						else if (y != 5 && toCheck2[2][y] == 0 && once2 == 0)
							if(toCheck2[2][y+1] != 0)
							{
								toCheck2[2][y] = -1;
								once2 = 1;
							}

						if (y == 5 && toCheck3[3][y] == 0 && once3 == 0)
						{
							toCheck3[3][y] = -1;
							once3 = 1;
						}
						else if (y != 5 && toCheck3[3][y] == 0 && once3 == 0)
							if(toCheck3[3][y+1] != 0)
							{
								toCheck3[3][y] = -1;
								once3 = 1;
							}

						if (y == 5 && toCheck4[4][y] == 0 && once4 == 0)
						{
							toCheck4[4][y] = -1;
							once4 = 1;
						}
						else if (y != 5 && toCheck4[4][y] == 0 && once4 == 0)
							if(toCheck4[4][y+1] != 0)
							{
								toCheck4[4][y] = -1;
								once4 = 1;
							}

						if (y == 5 && toCheck5[5][y] == 0 && once5 == 0)
						{
							toCheck5[5][y] = -1;
							once5 = 1;
						}
						else if (y != 5 && toCheck5[5][y] == 0 && once5 == 0)
							if(toCheck5[5][y+1] != 0)
							{
								toCheck5[5][y] = -1;
								once5 = 1;
							}

						if (y == 5 && toCheck6[6][y] == 0 && once6 == 0)
						{
							toCheck6[6][y] = -1;
							once6 = 1;
						}
						else if (y != 5 && toCheck6[6][y] == 0 && once6 == 0)
							if(toCheck6[6][y+1] != 0)
							{
								toCheck6[6][y] = -1;
								once6 = 1;
							}

					}

					if(search(toCheck0)[2] != 0 && search(toCheck0)[4] != 0 || search(toCheck0)[3] != 0 && search(toCheck0)[5] != 0)
					{
						game[compare(game, toCheck0)[0]][compare(game, toCheck0)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck1)[2] != 0 && search(toCheck1)[4] != 0 || search(toCheck1)[3] != 0 && search(toCheck1)[5] != 0)
					{
						game[compare(game, toCheck1)[0]][compare(game, toCheck1)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck2)[2] != 0 && search(toCheck2)[4] != 0 || search(toCheck2)[3] != 0 && search(toCheck2)[5] != 0)
					{
						game[compare(game, toCheck2)[0]][compare(game, toCheck2)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck3)[2] != 0 && search(toCheck3)[4] != 0 || search(toCheck3)[3] != 0 && search(toCheck3)[5] != 0)
					{
						game[compare(game, toCheck3)[0]][compare(game, toCheck3)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck4)[2] != 0 && search(toCheck4)[4] != 0 || search(toCheck4)[3] != 0 && search(toCheck4)[5] != 0)
					{
						game[compare(game, toCheck4)[0]][compare(game, toCheck4)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck5)[2] != 0 && search(toCheck5)[4] != 0 || search(toCheck5)[3] != 0 && search(toCheck5)[5] != 0)
					{
						game[compare(game, toCheck5)[0]][compare(game, toCheck5)[1]] = -1;
						turn *= -1;
					}

					else if(search(toCheck6)[2] != 0 && search(toCheck6)[4] != 0 || search(toCheck6)[3] != 0 && search(toCheck6)[5] != 0)
					{
						game[compare(game, toCheck6)[0]][compare(game, toCheck6)[1]] = -1;
						turn *= -1;
					}
				}



				// IF ELSE // ???check if it would make red win???

				// IF ELSE RANDOM MOVE
				if(turn == -1)
				{
					toCheck0 = game;
					toCheck1 = game;
					toCheck2 = game;
					toCheck3 = game;
					toCheck4 = game;
					toCheck5 = game;
					toCheck6 = game;

					once0 = 0;
					once1 = 0;
					once2 = 0;
					once3 = 0;
					once4 = 0;
					once5 = 0;
					once6 = 0;

					int rx = randomGenerator.nextInt(5);
					boolean set = false;

					for(int y = 0; y <= 5; y++)
						if(y == 5 && !set && game[rx][y] == 0)
						{
							game[rx][y] = -1;
							turn *= -1;
							set = true;
						}
						else if( y < 5 && game[rx][y+1] != 0 && !set && game[rx][y] == 0)
						{
							game[rx][y] = -1;
							turn *= -1;
							set = true;
						}
					repaint();
				}
				repaint();
			}
			repaint();
		}

		int[] compare(int[][] game, int[][] check)
		{
			for (int y = 0; y <= 5; y++)
				for (int x = 0; x <= 6; x++)
					if(game[x][y] != check[x][y])
						return new int[] {x, y};

			return new int[] {0, 0};
		}

		int[] search(int[][] game)
		{
			int search1 = 0;
			int search2 = 0;
			int search3 = 0;
			int search4 = 0;

			for (int y = 0; y <= 5; y++)
				for (int x = 0; x <= 3; x++)
				{
					search1 = game[x][y];
					search2 = game[x + 1][y];
					search3 = game[x + 2][y];
					search4 = game[x + 3][y];

					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
					{
						return new int[] {x, y, x + 1, y, x + 2, y, x + 3, y};
					}
				}

			for (int x = 0; x <= 6; x++)
				for (int y = 0; y <= 2; y++)
				{
					search1 = game[x][y];
					search2 = game[x][y + 1];
					search3 = game[x][y + 2];
					search4 = game[x][y + 3];

					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
					{
						return new int[] {x, y, x , y+1, x, y+2, x, y+3};
					}
				}

			for (int y = 0; y <= 2; y++)
				for (int x = 0; x <= 3; x++)
				{
					search1 = game[x][y];
					search2 = game[x+1][y+1];
					search3 = game[x+2][y+2];
					search4 = game[x+3][y+3];

					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
					{
						return new int[] {x, y, x+1, y+1, x+2, y+2, x+3, y+3};
					}
				}

			for (int y = 0; y <= 2; y++)
				for (int x = 3; x <= 6; x++)
				{
					search1 = game[x][y];
					search2 = game[x-1][y+1];
					search3 = game[x-2][y+2];
					search4 = game[x-3][y+3];

					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
					{
						return new int[] {x, y, x-1, y+1, x-2, y+2, x-3, y+3};
					}
				}

			return new int[]{0,0,0,0,0,0,0,0};
		}

		static int flash = 0;
		static void flash()
		{
			if(flash == 0)
			{
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						if(game[x][y] == 3)
							game[x][y] = 2;
				flash = 1;
			}

			else if(flash == 1)
			{
				for (int y = 0; y <= 5; y++)
					for (int x = 0; x <= 6; x++)
						if(game[x][y] == 2)
							game[x][y] = 3;
				flash = 0;
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		static void restart()
		{
			mouseListen = 0;
			game = new int[7][6];
			turn = 1;
			repeat = 2;
			temp = 2;
			change = 0;
			s1 = 0;
			s2 = 0;
			s3 = 0;
			s4 = 0;
			s5 = 0;
			s6 = 0;
			s7 = 0;
			s8 = 0;
			once = 0;
			canClick = true;
			canMove = true;
		}
	}
}
