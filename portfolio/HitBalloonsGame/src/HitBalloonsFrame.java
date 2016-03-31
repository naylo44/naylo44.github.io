import javax.swing.JFrame;


public class HitBalloonsFrame extends JFrame{
	HitBalloonsFrame() {
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HitBallonsPanel panel = new HitBallonsPanel();
		this.add(panel);
		this.setVisible(true);
	}
}
