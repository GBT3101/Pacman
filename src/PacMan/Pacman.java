package PacMan;

import javax.swing.*;

public class Pacman extends Figure {

	
	
	/*************fields*************/
	
	private static final long serialVersionUID = 775694369895542166L;
	
	private int state; //the state of pacman: 0 = normal, 1 = mighty, 2 = super.

	/***************constructors**************/
	
	public Pacman(int x, int y) {
		super(x, y);
		state = 0;
		ImageIcon pacmanIcon = new ImageIcon("pacmanLeft.gif");
		setIcon(pacmanIcon);
		this.setOpaque(false);
		this.setSize(getsize(), getsize());
		this.setPreferredSize(getSize());
		setOpaque(false);
		setBounds(getSx(), getSy(), getsize(), getsize());
		setSpeed(5);
	}
	
	/*************getters**************/
	
	public int getState(){ // gets the state of pacman
		return this.state;
	}
	
	/*************Methods***************/
	
	/**
	 * the increaseState method increasing the state of pacman, also increasing it's speed
	 * and makes it stronger (effect of 10 seconds)
	 */
	public void increaseState(){
		if(state==0){ // make Mighty
			state++;
			ImageIcon pacmanIcon = new ImageIcon("MightyPacmanLeft.gif");
			setIcon(pacmanIcon);
		}
		else if(state==1){ // make Super
			state++;
			ImageIcon pacmanIcon = new ImageIcon("SuperPacmanLeft.gif");
			setIcon(pacmanIcon);
		}
	}
	
	/**
	 * the decreaseState method decreasing the state of pacman, also decreasing it's speed
	 * and makes it weaker
	 */
	public void decreaseState(){
		if(state==2){ // make Mighty
			state--;
			ImageIcon pacmanIcon = new ImageIcon("MightyPacmanLeft.gif");
			setIcon(pacmanIcon);
		}
		else if(state==1){ //make normal
			state--;
			ImageIcon pacmanIcon = new ImageIcon("pacmanLeft.gif");
			setIcon(pacmanIcon);
		}
	}
	
	public void moveUp(){ // moves pacman up
		super.moveUp();
		ImageIcon pacmanIcon;
		pacmanIcon = new ImageIcon("pacmanUp.gif");
		if(state==1)
			pacmanIcon = new ImageIcon("MightyPacmanUp.gif");
		if(state==2)
			pacmanIcon = new ImageIcon("SuperPacmanUp.gif");
		setIcon(pacmanIcon);
	}

	public void moveDown(){ // moves pacman down
		super.moveDown();
		ImageIcon pacmanIcon = new ImageIcon("pacmanDown.gif");
		if(state==1)
			pacmanIcon = new ImageIcon("MightyPacmanDown.gif");
		if(state==2)
			pacmanIcon = new ImageIcon("SuperPacmanDown.gif");
		setIcon(pacmanIcon);
		
	}

	public void moveLeft(){ // moves pacman left
		super.moveLeft();
		ImageIcon pacmanIcon = new ImageIcon("pacmanLeft.gif");
		if(state==1)
			pacmanIcon = new ImageIcon("MightyPacmanLeft.gif");
		if(state==2)
			pacmanIcon = new ImageIcon("SuperPacmanLeft.gif");
		setIcon(pacmanIcon);
	}

	public void moveRight(){ // moves pacman right
		super.moveRight();
		ImageIcon pacmanIcon = new ImageIcon("pacmanRight.gif");
		if(state==1)
			pacmanIcon = new ImageIcon("MightyPacmanRight.gif");
		if(state==2)
			pacmanIcon = new ImageIcon("SuperPacmanRight.gif");
		setIcon(pacmanIcon);
		
	}

	
	
	
	
}
