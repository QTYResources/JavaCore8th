import javax.swing.JFrame;

/**
 * @version 1.32 2007-6-12
 * @author Cay Horstmann
 */
public class SimpleFrameTest {
	
	public static void main(String[] args) {
		SimpleFrame frame = new SimpleFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class SimpleFrame extends JFrame {
	
	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
}
