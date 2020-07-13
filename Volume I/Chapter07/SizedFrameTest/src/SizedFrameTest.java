import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @version 1.32 2007-04-14
 * @author author Cay Horstmann
 */
public class SizedFrameTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SizedFrame frame = new SizedFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class SizedFrame extends JFrame {
	
	public SizedFrame() {
		// get screen dimensions
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// set frame width, height and let platform pick screen location
		
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		
		// set frame icon and title
		
		Image img = kit.getImage("icon.gif");
		setIconImage(img);
		setTitle("SizedFrame");
	}
}
