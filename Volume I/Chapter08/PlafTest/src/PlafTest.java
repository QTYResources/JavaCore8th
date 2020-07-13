import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @version 1.32 2007-06-12
 * @author Cay Horstmann
 */
public class PlafTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new PlafFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}

/**
 * A frame with a button panel for changing look and feel
 */
class PlafFrame extends JFrame {
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private JPanel buttonPanel;
	
	public PlafFrame() {
		setTitle("PlafTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		buttonPanel = new JPanel();
		
		LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : infos) {
			makeButton(info.getName(), info.getClassName());
		}
		
		add(buttonPanel);
	}
	
	/**
	 * Makes a button to change the pluggable look and feel.
	 * @param name the button name
	 * @param plafName the name of the look and feel class
	 */
	void makeButton(String name, final String plafName) {
		// add button to panel
		JButton button = new JButton(name);
		buttonPanel.add(button);
		
		// set button action
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// button action: switch to the new look and feel
				try {
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(PlafFrame.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}