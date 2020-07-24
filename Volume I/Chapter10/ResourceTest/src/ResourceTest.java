import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @version 1.4 2007-04-30
 * @author Cay Horstmann
 */
public class ResourceTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ResourceTestFrame frame = new ResourceTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

/**
 * A frame that loads image and text resources.
 */
class ResourceTestFrame extends JFrame {

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;

	public ResourceTestFrame() {
		setTitle("ResourceTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		URL aboutURL = getClass().getResource("about.gif");
		
		Image img = Toolkit.getDefaultToolkit().getImage(aboutURL);
		setIconImage(img);

		JTextArea textArea = new JTextArea();
		InputStream stream = getClass().getResourceAsStream("about.txt");
		Scanner in = new Scanner(stream);
		while (in.hasNext())
			textArea.append(in.nextLine() + "\n");
		add(textArea);
	}

}