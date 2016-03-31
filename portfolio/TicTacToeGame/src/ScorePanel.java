import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			
			g.drawString("Player One = " + Integer.toString(TicTacToe.PlayerOneWins) + "    ", 0, 10);
			g.drawString("Player Two = " + Integer.toString(TicTacToe.PlayerTwoWins) + "    ", 100, 10);
			
	}
		
		
}
