import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.FileSaveService;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

// javax.jnlp 在 javaws.jar包中，javaws.jar 在 jre/lib 文件夹下

/**
 * A calculator with a calculation history that can be deployed as a Java Web
 * Start application.
 * 
 * @version 1.02 2007-06-12
 * @author Cay Horstmann
 */
public class WebStartCalculator {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CalculatorFrame frame = new CalculatorFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

/**
 * A frame with a calculator panel and a menu to load and save the calculator
 * history.
 */
class CalculatorFrame extends JFrame {

	private CalculatorPanel panel;

	public CalculatorFrame() {
		setTitle();
		panel = new CalculatorPanel();
		add(panel);

		JMenu fileMenu = new JMenu("File");

		JMenuItem openItem = fileMenu.add("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				open();
			}
		});

		JMenuItem saveItem = fileMenu.add("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				save();
			}
		});
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);

		pack();
	}

	/**
	 * Gets the title from the persistent store or asks the user for the title if
	 * there is no prior entry.
	 */
	public void setTitle() {
		try {
			String title = null;

			BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
			URL codeBase = basic.getCodeBase();

			PersistenceService service = (PersistenceService) ServiceManager.lookup("javax.jnlp.PersistenceService");
			URL key = new URL(codeBase, "title");

			try {
				FileContents contents = service.get(key);
				InputStream in = contents.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				title = reader.readLine();
			} catch (FileNotFoundException e) {
				title = JOptionPane.showInputDialog("Please supply a frame title:");
				if (title == null)
					return;

				service.create(key, 100);
				FileContents contents = service.get(key);
				OutputStream out = contents.getOutputStream(true);
				PrintStream printOut = new PrintStream(out);
				printOut.print(title);
			}
			setTitle(title);
		} catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(this, e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	/**
	 * Opens a history file and updates the display.
	 */
	public void open() {
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			FileContents contents = service.openFileDialog(".", new String[] { "txt" });

			JOptionPane.showMessageDialog(this, contents.getName());
			if (contents != null) {
				InputStream in = contents.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line;
				while ((line = reader.readLine()) != null) {
					panel.append(line);
					panel.append("\n");
				}
			}
		} catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	/**
	 * Saves the calculator history to a file.
	 */
	public void save() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printOut = new PrintStream(out);
			printOut.print(panel.getText());
			InputStream data = new ByteArrayInputStream(out.toByteArray());
			FileSaveService service = (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");
			service.saveFileDialog(".", new String[] { "txt" }, data, "calc.txt");
		} catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

}