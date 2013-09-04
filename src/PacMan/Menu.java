package PacMan;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
class Menu extends JFrame implements MouseListener, KeyListener{
	
	private static final long serialVersionUID = 680811279121705093L;
	
	int posX=0,posY=0;
	Game game;

	public Menu() {

		setUndecorated(true);

		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(6,1));
		menuPanel.add(new JLabel());
		menuPanel.add(new JLabel());
		menuPanel.add(new JLabel());
		menuPanel.add(new JLabel());
		JButton startGame = new JButton(new ImageIcon("startGame.png"));
		startGame.setOpaque(false);
		startGame.setContentAreaFilled(false);
		startGame.setBorderPainted(false);
		menuPanel.add(startGame);
		JButton quit = new JButton(new ImageIcon("menuQuit.png"));
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		menuPanel.add(quit);
		menuPanel.setOpaque(false);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("main.png")));
		setLayout(new FlowLayout());
		
		game = new Game();
		addKeyListener(game.getController());


		startGame.setBackground(Color.black);
		startGame.setBorderPainted(false);
		startGame.addActionListener(new ActionListener() { // action for the quit button
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				game.setVisible(true);
				
				
			}
		});
		
		
		quit.setBackground(Color.black);
		quit.setBorderPainted(false);
		quit.addActionListener(new ActionListener() { // action for the quit button
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

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

		add(menuPanel);
		pack();
	}



	public static void main(String args[]) {
		new Menu();
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
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
	
}