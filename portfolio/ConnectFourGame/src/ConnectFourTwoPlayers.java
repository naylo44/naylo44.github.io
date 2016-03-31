import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ConnectFourTwoPlayers extends JApplet implements ActionListener
{
	public ConnectFourTwoPlayers()
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
		frame.add(new ConnectFourTwoPlayers());
		frame.setTitle("Exercise18_34");
		frame.setSize(500 , 500);
		frame.setLocationRelativeTo(null); //Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		game.restart();
	}

	static class game extends JPanel 
	{
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
		static Boolean canClick = true;
		
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
			addMouseListener (new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent arg0){
					// TODO Auto-generated method stub
					
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
					repaint();
				}}}
			});
			
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
			if(search()[2] != 0 && search()[4] != 0 || search()[3] != 0 && search()[5] != 0)
			{
				canClick = false;

				int s1 = search()[0];
				int s2 = search()[1];
				int s3 = search()[2];
				int s4 = search()[3];
				int s5 = search()[4];
				int s6 = search()[5];
				int s7 = search()[6];
				int s8 = search()[7];

				game[s1][s2] = 2;
				game[s3][s4] = 2;
				game[s5][s6] = 2;
				game[s7][s8] = 2;
							
				flash();
				repaint();
			}
		}
				
		int[] search()
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
						return new int[] {x, y, x + 1, y, x + 2, y, x + 3, y};
				}
			
			for (int x = 0; x <= 6; x++)
				for (int y = 0; y <= 2; y++)
				{
					search1 = game[x][y];
					search2 = game[x][y + 1];
					search3 = game[x][y + 2];
					search4 = game[x][y + 3];
					
					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
						return new int[] {x, y, x , y+1, x, y+2, x, y+3};
				}
			
			for (int y = 0; y <= 2; y++)
				for (int x = 0; x <= 3; x++)
				{
					search1 = game[x][y];
					search2 = game[x+1][y+1];
					search3 = game[x+2][y+2];
					search4 = game[x+3][y+3];
					
					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
						return new int[] {x, y, x+1, y+1, x+2, y+2, x+3, y+3};
				}
			
			for (int y = 0; y <= 2; y++)
				for (int x = 3; x <= 6; x++)
				{
					search1 = game[x][y];
					search2 = game[x-1][y+1];
					search3 = game[x-2][y+2];
					search4 = game[x-3][y+3];
					
					if(search1 == search2 && search2 == search3 && search3 == search4 && search1 != 0)
						return new int[] {x, y, x-1, y+1, x-2, y+2, x-3, y+3};
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
		}
	}
}