import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class HitBallonsPanel extends JPanel{
	int rotation = 0;
	public static int multiple = 1;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Hit Targets: " + MainClass.hitTarget, 10, 10);
		g.drawString("Bullets Fired : " + MainClass.bulletFired, 10, 30);
		if (MainClass.bulletFired!=0 && MainClass.hitTarget!=0) g.drawString("Accuracy: " + ( (double)MainClass.hitTarget/ (double)MainClass.bulletFired) *100 + "%", 10, 50);
		
		if(multiple==1) {
			this.addKeyListener(listener);
			multiple=0;
		}

		for (int i = 0; i<MainClass.listLength(); i++) {
			if ((MainClass.list.get(i).y) <= 0 || (MainClass.list.get(i).x) >= getWidth() || (MainClass.list.get(i).x) <= 0) {
				MainClass.list.remove(i);
				MainClass.oneProjectile=0;
			}
		}
		
		for (int i = 0; i<MainClass.targetList.size(); i++) {
			for (int j = 0; j<MainClass.listLength(); j++) {
				if (MainClass.list.get(i).y <= MainClass.targetList.get(j).y+30 && MainClass.list.get(i).y+10 >= MainClass.targetList.get(j).y && MainClass.list.get(i).x+10 >= MainClass.targetList.get(j).x && MainClass.list.get(i).x<=MainClass.targetList.get(j).x+30) {
					MainClass.targetList.remove(j); 
					MainClass.list.remove(j); 
					MainClass.oneProjectile=0;
					MainClass.hitTarget++;
					MainClass.oneTarget = 0;
					g.drawOval(200, 75, 7, 7);
					g.drawOval(150, 250, 10, 10);
					g.drawOval(25, 200, 10, 10);
				}
			}
		}
		
		for (int i = 0; i<MainClass.targetList.size(); i++) {
			if ((MainClass.targetList.get(i).y) < 0 || (MainClass.targetList.get(i).x) > getWidth() || (MainClass.targetList.get(i).x) < 0) {
				MainClass.targetList.remove(i);
				Random rand=new Random();
				int x=rand.nextInt(2); 
				MainClass.direction = x;
				MainClass.oneTarget = 0;
			}
		}
		
		for (int j = 0; j<MainClass.listLength(); j++) {
			Projectile e = MainClass.list.get(j);
			Projectile.MoveProjectile(e);
		}
		
		for (int j = 0; j<MainClass.targetList.size(); j++) {
			Target e = MainClass.targetList.get(j);
			Target.MoveTarget(e);
		}
		
		for (int i = 0; i<MainClass.listLength(); i++) {
			g.fillOval(MainClass.list.get(i).x-5, MainClass.list.get(i).y, 10, 10);
		}
		
		for (int i = 0; i<MainClass.targetList.size(); i++) {
			g.fillOval(MainClass.targetList.get(i).x, MainClass.targetList.get(i).y, Target.width, Target.height);
		}
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.black);
		
		AffineTransform old = g2d.getTransform();
		g2d.rotate(Math.toRadians(rotation), getWidth() / 2, getHeight());
		g2d.fillRect(getWidth() / 2 - 4, 9 * getHeight() / 10, 8, getHeight() / 10);
		g2d.setTransform(old);
		
		this.requestFocus();
		g.dispose();
	}
	
	
	KeyListener listener = new KeyListener() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
					int key = e.getKeyCode();
					if (key == KeyEvent.VK_RIGHT) {
						if (rotation < 90) rotation = rotation + 5;
					}
					if (key == KeyEvent.VK_LEFT) {
						if (rotation > -90) rotation = rotation - 5;
					}

					if (key == KeyEvent.VK_SPACE && MainClass.oneProjectile==0) {
						MainClass.bulletFired++;
						MainClass.list.add(new Projectile(rotation, getWidth()/2, getHeight()));
						MainClass.oneProjectile = 1;
					}
					
					repaint();
			}
		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	};
	
}