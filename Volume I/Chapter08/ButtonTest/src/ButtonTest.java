import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class ButtonTest {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new ButtonFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}


/**
 * A frame with a button panel
 */
class ButtonFrame extends JFrame {
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private JPanel buttonPanel;
	
	public ButtonFrame() {
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// create buttons
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");
		
		buttonPanel = new JPanel();
		
		// add buttons to panel
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		
		// add panel to frame
		add(buttonPanel);
		
		// create button actions
		ColorAction yellowAction = new ColorAction(Color.yellow);
		ColorAction blueAction = new ColorAction(Color.blue);
		ColorAction redAction = new ColorAction(Color.red);
		
		// associate actions with buttons
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}
	
	/**
	 * An action listener that sets the panel's background color.
	 */
	private class ColorAction implements ActionListener {
		
		private Color backgroundColor;
		
		public ColorAction(Color c) {
			backgroundColor = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			buttonPanel.setBackground(backgroundColor);
		}
	}
}