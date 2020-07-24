import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @version 1.03 2007-06-12
 * @author Cay Horstmann
 */
public class ColorChooserTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ColorChooserFrame frame = new ColorChooserFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with a color chooser panel
 */
class ColorChooserFrame extends JFrame {

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public ColorChooserFrame() {
		setTitle("ColorChooserTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// add color chooser panel to frame

		ColorChooserPanel panel = new ColorChooserPanel();
		add(panel);
	}

}

/**
 * A panel with buttons to pop up three types of color choosers
 */
class ColorChooserPanel extends JPanel {

	public ColorChooserPanel() {
		JButton modalButton = new JButton("Modal");
		modalButton.addActionListener(new ModalListener());
		add(modalButton);

		JButton modelessButton = new JButton("Modeless");
		modelessButton.addActionListener(new ModelessListener());
		add(modelessButton);

		JButton immediateButton = new JButton("Immediate");
		immediateButton.addActionListener(new ImmediateListener());
		add(immediateButton);
	}

	/**
	 * This listener pops up a modal color chooser
	 */
	private class ModalListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			Color defaultColor = getBackground();
			Color selected = JColorChooser.showDialog(ColorChooserPanel.this, "Set background", defaultColor);
			if (selected != null)
				setBackground(selected);
		}

	}

	/**
	 * This listener pops up a modeless color chooser. The panel color is changed
	 * when the user clicks the Ok button.
	 */
	private class ModelessListener implements ActionListener {

		private JDialog dialog;
		private JColorChooser chooser;

		public ModelessListener() {
			chooser = new JColorChooser();
			dialog = JColorChooser.createDialog(ColorChooserPanel.this, "Background Color", false /* not modal */,
					chooser, new ActionListener() // OK
					// button
					// listener
					{
						public void actionPerformed(ActionEvent event) {
							setBackground(chooser.getColor());
						}
					}, null /* no Cancel button listener */);
		}

		public void actionPerformed(ActionEvent event) {
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}

	}

	/**
	 * This listener pops up a modeless color chooser. The panel color is changed
	 * immediately when the user picks a new color.
	 */
	private class ImmediateListener implements ActionListener {

		private JDialog dialog;
		private JColorChooser chooser;

		public ImmediateListener() {
			chooser = new JColorChooser();
			chooser.getSelectionModel().addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent event) {
					setBackground(chooser.getColor());
				}
			});

			dialog = new JDialog((Frame) null, false /* not modal */);
			dialog.add(chooser);
			dialog.pack();
		}

		public void actionPerformed(ActionEvent event) {
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}

	}

}
