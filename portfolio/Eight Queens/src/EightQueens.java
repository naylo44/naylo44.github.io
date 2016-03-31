import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
 
public class EightQueens extends JFrame
{
	public static int SIZE = 0; // Chessboard size
	 // queens are placed at (i, queens)
	// -1 indicates that no queen is currently placed in the ith row
	// Initially, place a queen at (0, 0) in the 0th row
	
	
	static Random randomGenerator = new Random();
	private static int[] queenss = {randomGenerator.nextInt(7), -1, -1, -1, -1, -1, -1, -1, -1, -1 ,-1, -1 ,-1 ,-1 ,-1};
	static int k = 0;
	static ChessBoard cb = new ChessBoard();
	static EightQueens frame = new EightQueens();
	
	public static void main(String[] args)
	{
		
		frame.setTitle("Eight Queens");
		frame.setSize(500 , 500);
		frame.setLocationRelativeTo(null); //Center the frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(new MouseListener()
		{

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				search();
				frame.repaint();		
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
       
	public EightQueens() {
		if (search()) // Search for a solution
			add(cb, BorderLayout.CENTER);
		else
			JOptionPane.showMessageDialog(null, "No solution found");
	}
       
	/** Search for a solution */
	private static boolean search() {
		// k - 1 indicates the number of queens placed so far
		// We are looking for a position in the kth row to place a queen
		int k = SIZE;
		
			// Find a position to place a queen in the kth row
			int j = findPosition(k);
			if (j < 0) {
				queenss[k] = -1;
				k--; // back track to the previous row
			} 
			else {
				queenss[k] = j;
				k++;
			}
			
		SIZE = k;
		
		if (k == -1) {
			return false; // No solution
		} 
		else {
			return true; // A solution is found
		}
	}
       
	public static int findPosition(int k) {
		int start = queenss[k] + 1; // Search for a new placement
               
		for (int j = start; j < 8; j++) {
			if (isValid(k, j)) {
				return j; // (k, j) is the place to put the queen now
			}
		}
		return -1;
	}
       
	/** Return true if a queen can be placed at (row, column) */
	public static boolean isValid(int row, int column) {
		for (int i = 1; i <= row; i++) {
			if (queenss[row - i] == column // Check column
					|| queenss[row - i] == column - i // Check up-left diagonal
					|| queenss[row - i] == column + i) // Check up-right diagonal
				return false; // There is a conflict, so false
		}
		return true; // No conflict, so true
	}
       
	static class ChessBoard extends JPanel {
		BufferedImage queenImage;
               
		ChessBoard() {
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
			try {                
				queenImage = ImageIO.read(new File("src/Icones/queen.jpg"));
			} 

			catch (Exception ex) {
				// handle exception...
				JFrame f = new JFrame();
				f.setLayout(new BorderLayout());
				JLabel l = new JLabel("             Image not found");
				f.add(l, BorderLayout.CENTER);
				f.setTitle("Image not found");
				f.setSize(200 , 100);
				f.setLocationRelativeTo(null); //Center the frame
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		}
       		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
            
			g.setColor(Color.GRAY);
			g.fillRect(0, (getHeight()+5) / 8 * SIZE, getWidth(), getHeight() / 8);
			g.setColor(Color.BLACK);
			
			// Paint the queens
			for (int i = 0; i < SIZE; i++) 
			{
				int j = queenss[i]; // The position of the queen in row i
				g.drawImage(queenImage, j * getWidth() / 8, i * getHeight() / 8, getWidth() / 8, getHeight() / 8, this);
			}
			           
			// Draw the horizontal and vertical lines
			for (int i = 1; i < 8; i++) {
				g.drawLine(0, i * getHeight() / 8, getWidth(), i * getHeight() / 8);
				g.drawLine(i * getWidth() / 8, 0, i * getWidth() / 8, getHeight());
			}
		}
	}
}