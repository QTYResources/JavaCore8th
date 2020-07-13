import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The component that draws the balls.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class BallComponent extends JPanel {
	
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	/**
	 * Add a ball to the component.
	 * @param b
	 */
	public void add(Ball b) {
		balls.add(b);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);// erase background
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}	
}
