import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @version 1.33 2007-04-14
 * @author Cay Horstmann
 */
public class ImageTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new ImageFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}

/**
 * A frame with an image component
 */
class ImageFrame extends JFrame {
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ImageFrame() {
		setTitle("ImageTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add component to frame
		
		ImageComponent component = new ImageComponent();
		add(component);
	}
}

/**
 * A component that displays a tiled image
 */
class ImageComponent extends JComponent {
	
	private Image image;
	
	public ImageComponent() {
		// acquire the image
		try {
			image = ImageIO.read(new File("blue-ball.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (image == null) return;
		
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		
		// draw the image in the top-left corner
		
		g.drawImage(image, 0, 0, null);
		// tile the image across the component
		
		for (int i = 0; i * imageWidth <= getWidth(); i++) {
			for (int j = 0; j * imageHeight <= getHeight(); j++) {
				if (i + j > 0) {
					g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
				}
			}
		}
	}
}
