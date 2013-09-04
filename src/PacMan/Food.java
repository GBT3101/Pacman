package PacMan;

import javax.swing.*;

public class Food extends JLabel {

	private static final long serialVersionUID = 1L;

	

	/***********Fields**********/
	
	protected int Isize=20; // represents the size of the food.
	private int eaten; // 1 = if the food has been eaten, 0 = if not.
	private Position p; // the position of the food
	protected JLabel food; // a JLabel to represent the food's image

	/*******************Constructors************/

	public Food(int x, int y){
		food = new JLabel();
		p = new Position(x,y);
		eaten=0;
		ImageIcon pacmanIcon = new ImageIcon("food.png");
		food.setIcon(pacmanIcon);
		food.setSize(Isize, Isize);
		food.setPreferredSize(getSize());
		this.add(food);
		setSize(Isize, Isize);
		setPreferredSize(getSize());

	}

	/************Getters/Setters***********/

	public int getEaten(){ // returns the eaten indicator
		return eaten;
	}

	public int getSx(){ // returns the starting x position
		return p.getX();
	}

	public int getEx(){ // returns the ending x position
		return p.getX()+Isize;
	}

	public int getSy(){ // returns the starting y position
		return p.getY();
	}

	public int getEy(){ // returns the ending x position
		return p.getY()+Isize;
	}

	/***************Methods***********/
	
	/**
	 * the eated method receives a pacman and returns if there's a touch in it.
	 * the method is using the Pythagoras formula
	 * @param pacman
	 * @return
	 */
	public boolean eated(Pacman pacman){
		boolean ans=false;
		
		if(eaten==0){ // if it hasn't been eaten yet
			ans = 25 >= (Math.sqrt(Math.pow((this.getSx()+5)-(pacman.getSx()+5),2) + 
					Math.pow((this.getSy()+5-pacman.getSy()+5),2))); // Pythagoras formula
			if(ans){ // delete the food now.
				eaten++;
				food.setVisible(false);
				this.setVisible(false);
			}
		}
		return ans;
	}
	
}
