package PacMan;

public class Position {
	
	/*******************Fields*****************/
	
	private int x; // Represent the position by x
	private int y; // Represent the position by y
	private int speed; // represent the speed of the object using this position
	
	/*************Constructor******************/
	
	public Position(int x,int y){
		this.x = x;
		this.y = y;
		speed = 0;
	}
	
	/*************Setters - Getters************/
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public void setSpeed(int s){
		speed=s;
	}
	
	
	public int getSpeed(){
		return speed;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	/****************Methods******************/
	
	public void increaseX(){ // Increasing the x position by the speed value
		this.x = this.x+this.speed;
	}
	
	public void increaseY(){ // increasing the y position by the speed value
		this.y = this.y+this.speed;
	}
	
	public void decreaseX(){ // decreasing the x position by the speed value
		this.x=this.x-this.speed;
	}
	
	public void decreaseY(){ // decreasing the y position by the speed value
		this.y=this.y-this.speed;
	}
	
}
