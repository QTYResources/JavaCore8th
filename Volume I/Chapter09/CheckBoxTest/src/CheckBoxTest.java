import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class CheckBoxTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				CheckBoxFrame frame = new CheckBoxFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}

/**
 * A frame with a sample text label and check boxes for selecting font attributes.
 */
class CheckBoxFrame extends JFrame {
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static final int FONTSIZE = 12;
	
	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;
	
	public CheckBoxFrame() {
		setTitle("CheckBoxTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// add the sample text label
		
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
		add(label, BorderLayout.CENTER);
		
		// this listener sets the font attribute of 
		// the label to the check box state
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int mode = 0; 
				if (bold.isSelected()) mode += Font.BOLD;
				if (italic.isSelected()) mode += Font.ITALIC;
				label.setFont(new Font("serif", mode, FONTSIZE));
			}
		};
		
		// add the check boxes
		
		JPanel buttonPanel = new JPanel();
		
		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		buttonPanel.add(bold);
		
		
		italic = new JCheckBox("Italic");
		italic.addActionListener(listener);
		buttonPanel.add(italic);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
}