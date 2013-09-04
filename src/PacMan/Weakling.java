package PacMan;

import javax.swing.*;

public class Weakling extends Figure {

	/************Fields****************/

	private static final long serialVersionUID = 2065856038112746841L;
	
	private int state; //the state of the monster: 1 = normal, 0 = vulnerable, -1 = dead.
	private Section visited; // Represents the last section visited by the weakling (for the AI)

	/******************Constructors********/

	public Weakling(int x, int y) {
		super(x, y);
		state = 1;
		ImageIcon pacmanIcon = new ImageIcon("weakling.gif");
		setIcon(pacmanIcon);
		this.setSize(25, 25);
		this.setPreferredSize(getSize());
		setOpaque(false);
		setBounds(getSx(), getSy(), 25, 25);
		this.setMoveable(false);
		this.setDirection(0);

	}

	/************* Getters***********/
	
	public int getState(){ // get the state of the weakling
		return state;
	}
	
	public void setVisitedSection(Section s){ // set the last visited section by this monster
		this.visited = s;
	}
	
	public Section getVisitedSection(){ // get the last visited section by this monster
		return visited;
	}
	
	

	/************Methods*****************/
	
	
	protected void incState(){ // increase the int value of the state
		state=1;
	}
	
	protected void decState(){ // decrease the int value of the state
		state--;
	}
	
	public void increaseState(){ // increasing the state of a weakling, making it dangerous again.
		if(state<=0){ // make normal
			incState();
			ImageIcon pacmanIcon = new ImageIcon("weakling.gif");
			setIcon(pacmanIcon);
		}
		
	}

	public void decreaseState(){ // decreasing the state of a weakling, making it valnurable or killing it
		if(state==1){ // make vulnerable
			state--;
			ImageIcon pacmanIcon = new ImageIcon("weaklingweak.gif");
			setIcon(pacmanIcon);
		}
		else if(state==0){ // kill
			state--;
			ImageIcon pacmanIcon = new ImageIcon("weaklingDead.gif");
			setIcon(pacmanIcon);
		}
		
	}
	
	
	
	public void create(Weakling w){ // get the data from weakling w and put it to this data
		super.create(w);
		state = 1;
		this.setSize(25, 25);
		this.setPreferredSize(getSize());
		this.setMoveable(w.isMoveable());
		setOpaque(false);
		setBounds(getSx(), getSy(), 25, 25);
		
	}
	
	

	


}
