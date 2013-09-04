package PacMan;

import javax.swing.*;

public abstract class Figure extends JLabel {


	/*************Fields**************/

	private static final long serialVersionUID = 6520650187879385755L;
	
	private Position dot; // Represents the position of the Figure
	private final int size = 25; // represents the size of the figure
	private  int Sspeed = 5; // represents the speed of the figure
	private int direction; // 0 - right, 1 - left, 2 - up, 3 - down.
	private boolean moveable; // true if the figure is moveable

	/******************Constructors**************/

	public Figure(int x, int y){
		this.dot = new Position(x,y);
		dot.setSpeed(Sspeed);
		direction = -1;
		moveable=true;
		this.setLocation(getSx(), getSy());
	}

	/***************setters - getters**************/

	public void setDot(int x,int y){ // sets the position of the figure
		int s = dot.getSpeed();
		dot = new Position(x,y);
		dot.setSpeed(s);
		this.setLocation(getSx(), getSy());
		this.repaint();
	}
	
	public Position getDot(){
		return dot;
	}
	
	public void setSpeed(int s){
		this.dot.setSpeed(s);
	}
	
	public int getSpeed(){
		return this.dot.getSpeed();
	}

	public int getSx(){ // starting position of x
		return dot.getX();
	}

	public int getEx(){ // ending position of x
		return dot.getX()+size;
	}

	public int getSy(){ // starting position of y
		return dot.getY();
	}

	public int getEy(){ // ending position of y
		return dot.getY()+size;
	}
	
	public int getsize(){
		return size;
	}

	public void setDirection(int d){ // sets the direction of the figure
		direction=d;
	}

	public int getDirection(){ // get the direction of the figure
		return direction;
	}

	public void setMoveable(boolean b){
		moveable=b;
	}
	
	public boolean isMoveable(){
		return moveable;
	}
	

	
	public int getOppositeDirection(){ // returns the opposite direction of the figure
		if (direction==0)
			return 1;
		if (direction==1)
			return 0;
		if (direction==2)
			return 3;
		if(direction==3)
			return 2;
		return -1;
	}


	/****************Methods****************/

	public void moveUp(){ // moving the figure upwards
		dot.decreaseY();
	}

	public void moveDown(){ // moving the figure downwards
		dot.increaseY();
	}

	public void moveLeft(){ // moving the figure leftwards
		dot.decreaseX();
	}

	public void moveRight(){ // moving the figure rightwards
		dot.increaseX();
	}

	public void move(){ // moves the figure
		if(moveable){
			if(direction==0)
				moveRight();
			if(direction==1)
				moveLeft();
			if(direction==2)
				moveUp();
			if(direction==3)
				moveDown();
			}
		this.setLocation(this.getSx(), this.getSy());
		repaint();
		}
	

	/**
	 * A boolean method
	 * return true - if This figure touches f figures
	 * return false - there's no touch between This figure and f figure
	 * this method using the Pythagoras formula
	 * @param f
	 * @return
	 */
	public boolean touch(Figure f){ 
		return 25 >= (Math.sqrt(Math.pow((this.getSx()+5)-(f.getSx()+5),2) + 
					Math.pow((this.getSy()+5-f.getSy()+5),2))); //Pythagoras formula
	}
	
	/**
	 * the "create" method Gives This figure the data of the figure f.
	 * @param f
	 */
	public void create(Figure f){
		this.dot = new Position(f.getSx(),f.getSy());
		this.setLocation(f.getSx(), f.getSy());
		setSpeed(f.getSpeed());
		this.setDirection(f.getDirection());
		this.moveable=true;
	}

	public abstract void increaseState();

	public abstract void decreaseState();


}
