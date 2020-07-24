import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @version
 * @author xiaot
 *
 */
public class FillTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new FillFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}

/**
 * A frame that contains a component with drawings
 */
class FillFrame extends JFrame {
	
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	
	public FillFrame() {
		setTitle("FillTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add panel to frame
		
		FillComponent component = new FillComponent();
		add(component);
	}

}

/**
 * A component that displays fill rectangles and ellipses.
 */
class FillComponent extends JComponent {
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// draw a rectangle
		
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		
		Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
		g2.setPaint(Color.red);
		g2.fill(rect);
		
		// draw the enclosed ellipse
		
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.setPaint(new Color(0, 128, 128));  // a dull blue-green
		g2.fill(ellipse);
	}
	
}