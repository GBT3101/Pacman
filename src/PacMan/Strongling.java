package PacMan;

import javax.swing.*;

public class Strongling extends Weakling {
	
	/**************Fields************/
	
	private static final long serialVersionUID = 306907714925795669L;

	/*************Constructors*************/
	
	public Strongling(int x, int y) {
		super(x, y);
		ImageIcon pacmanIcon = new ImageIcon("strongling.gif");
		setIcon(pacmanIcon);
	}
	
	/**************Methods****************/
	
	public void increasestate(){ // increase the state of the strongling, make it dangerous again.
		if(getState()<=0){ // make normal
			incState();
			ImageIcon pacmanIcon = new ImageIcon("Strongling.gif");
			setIcon(pacmanIcon);
		}
	}

	public void decreasestate(){ // decrease the state of the strongling, weaken it or killing it.
		if(getState()==1){ // make vulnerable
			decState();
			ImageIcon pacmanIcon = new ImageIcon("stronglingweak.gif");
			setIcon(pacmanIcon);
		}
		
		else if(getState()==0){ // kill
			decState();
			ImageIcon pacmanIcon = new ImageIcon("stronglingDead.gif");
			setIcon(pacmanIcon);
		}
	}
	
}
