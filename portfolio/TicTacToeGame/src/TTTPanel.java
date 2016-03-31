import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class TTTPanel extends JPanel{
	boolean x = false;
	boolean o = false;
	boolean played = false;
			
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
			
			if (x) {
				g.drawLine(0,0, this.getWidth(), this.getHeight());
				g.drawLine(0, this.getHeight(),this.getWidth(),0);
			}
			
			if (o) g.drawOval(0, 0, this.getWidth(), this.getHeight());
			
			
			
			
	}
		void reset() {
			this.o=false;
			this.x=false;
			this.played=false;
			repaint();
		}
		
		
}
