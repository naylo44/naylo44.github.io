import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainClass {
	static int reDraw = 1;
	static ArrayList<Projectile> list = new ArrayList<Projectile>();
	static ArrayList<Target> targetList = new ArrayList<Target>();
	static int spawnTarget = 0;
	static int oneTarget = 0;
	static int oneProjectile = 0;
	static int direction = 1;
	static int hitTarget = 0;
	static int bulletFired = 0;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		JFrame frame = new HitBalloonsFrame();
		
		while (true) {
			Thread.sleep(15);
			
			spawnTarget = (int) (Math.random()*100);
			frame.repaint();
			if (spawnTarget==10 && oneTarget == 0) {
				targetList.add(new Target());
				oneTarget=1;
			}
			
		}
	}
	
	public static void addList(Projectile e) {
		list.add(e);
	}
	
	public static int listLength() {
		return list.size();
	}
}