package PacMan;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class Game extends JFrame implements MouseListener, KeyListener, ActionListener{

	private static final long serialVersionUID = -7132848196338970451L;

	int posX=0,posY=0;

	private Controller upper; // The Game engine.
	private JLabel[] lives; // Represents the player remaining lives.
	public static Timer timer; 
	private int livesCounter, lifeIndex; // Counts the remaining lives.
	private JPanel livesPanel;
	private double secondsSaver; // Saves the seconds.
	private Launcher launcher; // Launches the logon screen of the game.

	

	/**
	 * Initializes the game frame.
	 */
	public Game() {

		timer = new Timer(50,this);
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		setUndecorated(true);
		setSize(560,620);
		setLocationRelativeTo(null);
		this.upper = new Controller();
		this.addKeyListener(upper);
		upper.setBounds(-5, -5, -5, -5);
		upper.setBackground(Color.black);
		JPanel lower = new JPanel();
		lower.setBackground(Color.BLACK);
		upper.setBackground(Color.black);
		lower.setLayout(new GridLayout(1,4));
		lower.setSize(560, 100);
		JLabel livesLabel = new JLabel(new ImageIcon("Lives.png"));
		livesLabel.setHorizontalTextPosition(new Integer(0));
		lower.add(livesLabel);
		
		livesPanel = new JPanel();
		livesPanel.setLayout(new GridLayout(1,3));
		lives = new JLabel[3];
		for(int i=0; i<3; i++){
			lives[i] = new JLabel(new ImageIcon("life.png"));
			livesPanel.add(lives[i]);
		}
		livesPanel.setOpaque(false);
		lower.add(livesPanel);
		lifeIndex = 2;
		livesCounter=3;
		
		JButton restart = new JButton(new ImageIcon("Restart.png"));
		restart.setBackground(Color.black);
		restart.setBorderPainted(false);
		lower.add(restart);
		restart.addActionListener(new ActionListener() { // action for the quit button
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new Game();
			}
		});

		JButton quit = new JButton(new ImageIcon("quit.png"));
		quit.setBackground(Color.black);
		quit.setBorderPainted(false);
		lower.add(quit);
		quit.addActionListener(new ActionListener() { // action for the quit button
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);			
			}
		});
		
		gamePanel.setBounds(-5, -5, -5, -5);

		gamePanel.add(upper, BorderLayout.NORTH);
		gamePanel.add(lower, BorderLayout.SOUTH);

		getContentPane().add(gamePanel);
		pack();

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX=e.getX();
				posY=e.getY();
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				//sets frame position when mouse dragged			
				setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);			
			}
		});

		timer.start();

		launcher = new Launcher();
	}


	/**
	 * Returns the game controller engine.
	 * 
	 * @return - The game controller engine.
	 */
	public Controller getController(){
		return upper;
	}

	/**
	 * Runs The game.
	 */
	public static void main(String args[]) {
		new Game();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Reacts every time the timer is triggered.
	 * used to switch between the opening screen to the game screen.
	 * and to compute the remaining lives.
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (secondsSaver>=3 && secondsSaver<3.5) {
			this.setVisible(true);
			launcher.setVisible(false);
			requestFocusInWindow(true);
		}
		if ((livesCounter>upper.getLives()) && (secondsSaver>=3)) {
			lives[lifeIndex].setVisible(false);
			lifeIndex--;
		}
		this.livesCounter=upper.getLives();
		secondsSaver+=0.05;
	}



}