package PacMan;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Launcher extends JFrame {
	
	private static final long serialVersionUID = -7949539876450449197L;
	
	private JPanel launchImage;

	public Launcher() {
		
		launchImage = new JPanel();
		launchImage.add(new JLabel(new ImageIcon("main.png")));
		launchImage.setVisible(true);
		setUndecorated(true);
		setSize(560,620);
		launchImage.setOpaque(true);
		setLocationRelativeTo(null);
		getContentPane().add(launchImage);
		launchImage.setOpaque(true);
		setVisible(true);
		
	}
}
