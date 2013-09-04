package PacMan;

public class Section extends Figure{


	/*************Fields**************/

	private static final long serialVersionUID = 6211268410777929103L;

	private boolean dUp; // represent the availability of moving up
	private boolean dDown; // represent the availability of moving down
	private boolean dLeft; // represent the availability of moving left
	private boolean dRight; // represent the availability of moving right
	private int turnsCounter; // represent how many turns this section have

	/***********Constructors***************/

	public Section(int x, int y) {
		super(x, y);
		dUp = false;
		dDown = false;
		dLeft = false;
		dRight = false;
		turnsCounter=0;
	}

	/************Setters / Getters*****************/

	public void setUp(){ // set availability of moving up in this section
		dUp=true;
		turnsCounter++;
	}

	public void setDown(){ // set availability of moving down in this section
		dDown=true;
		turnsCounter++;
	}

	public void setLeft(){ // set availability of moving left in this section
		dLeft=true;
		turnsCounter++;
	}

	public void setRight(){ // set availability of moving right in this section
		dRight=true;
		turnsCounter++;
	}
	
	public boolean up(){
		return dUp;
	}

	public boolean down(){
		return dDown;
	}

	public boolean left(){
		return dLeft;
	}

	public boolean right(){
		return dRight;
	}
	
	public int getCountTurns(){ // returns the number of turns this section have.
		return turnsCounter;
	}

	/************Methods**************/

	/**
	 * the possible method set the moveable boolean in the figure f,
	 * true if there's a path in his direction, false if not.
	 * @param f
	 */
	public void possible(Figure f){
		boolean ans=false;
		if(f.getDirection()==0 && dRight)
			ans=true;
		if(f.getDirection()==1 && dLeft)
			ans=true;
		if(f.getDirection()==2 && dUp)
			ans=true;
		if(f.getDirection()==3 && dDown)
			ans=true;
		f.setMoveable(ans);
		//Proper settings: sets the figure right on the grid
		if((f.getDirection()==0 || f.getDirection()==1) && ans)
			f.setDot(f.getSx(), this.getSy()); 
		if((f.getDirection()==2 || f.getDirection()==3) && ans)
			f.setDot(this.getSx(), f.getSy()); 
	}
	
	public void increaseState() { // make all paths available.
		dUp=true;
		dDown=true;
		dLeft=true;
		dRight=true;
	}

	public void decreaseState() {
		//empty method, abstract.
	}

}
