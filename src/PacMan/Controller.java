package PacMan;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Controller extends JPanel implements KeyListener, ActionListener {


	private static final long serialVersionUID = -3850136654326981952L;

	/**************Fields*************/

	private JLayeredPane layeredPane; // Represents the layered panel of the controller
	/////////////Figures:
	private Pacman pacman; // Represents Pacman
	private Weakling w1; // Represents Inky
	private Weakling w2; // Represents Blinky
	private Strongling s1; // Represents Clyde
	private Strongling s2; // Represent Pinky
	//////////Counters and savers:
	private double seconds; // represents the seconds pass so far in the game
	private double secondSaver; // saving the seconds as general indicator.
	private double secondMonster; //saving the seconds when a monster is dead as indicator.
	private double secondDead; // saving the seconds when pacman is dead as indicator.
	private int counterEaten; // counting the food eaten so far.
	private int lives; // counting the lives of pacman.
	//////////Boolean indicators:
	private boolean special; // true if the game is in special state.
	private boolean waitingForMonster; // true if a monster should be released
	private boolean gameOver; // true if the game is over.
	private boolean dead; // true if pacman is dead.
	/////////////////////
	private Stage stage; // Represent the stage of the game
	private Timer timer; // Represents the timer of the game
	private final int delay = 50; // represent the delay of the timer.

	/***************Constructors*************/

	public Controller(){
		timer = new Timer(delay,this); // initialize timer
		layeredPane = new JLayeredPane(); // creating the layered panel
		stage = new Stage(); // initialize the stage
		stage.setBounds(0, 0, 560,620);
		init(); // Initializing the figures
		layeredPane.add(stage, new Integer(0)); // the stage is the bottom layer
		special = false;
		waitingForMonster = true;
		gameOver = false;
		dead = false;
		counterEaten = 0;
		lives=3;
		secondDead = -3;
		secondSaver = 0;
		this.add(layeredPane);
		this.addKeyListener(this);

	}


	/************Getters************/

	public int getLives(){ // returns the number of pacman's lives
		return lives;
	}
	/**************Methods***********/

	public void init(){ // Initializing the monsters positions
		w1 = new Weakling(260,220); // creates Inky out of the cage.
		pacman = new Pacman(270,460); // creates pacman
		w2 = new Weakling(-25, -25); // creates Blinky out of the board, but actually he's in the cage.
		s1 = new Strongling(-25,-25); // creates Clyde out of the board, but actually he's in the cage.
		s2 = new Strongling(-25,-25); // creates Pinky out of the board, but actually he's in the cage.
		layeredPane.setPreferredSize(new Dimension(560,620));
		layeredPane.add(pacman, new Integer(1)); // pacman is in layer number 1
		layeredPane.add(w1,new Integer(2)); // Inky is in layer number 2
		layeredPane.add(w2, new Integer(3)); // Blinky is in layer number 3
		layeredPane.add(s1, new Integer(4)); // Clyde is in layer number 4
		layeredPane.add(s2, new Integer(5)); // Pinky is in layer number 5
		stage.cageEnter(w2);
		stage.cageEnter(s1);
		stage.cageEnter(s2);
	}

	/**
	 * the release method takes a monster out of the cage and put it in the stage
	 */
	public void release(){
		Weakling w = stage.cageExit();
		w.setDot(270,220);
		w.setMoveable(true);
		if( w instanceof Strongling){ // if the monster is a strong one, add it to s1 / s2
			if( s1.getSx()== -25 ){
				s1 = new Strongling(w.getSx(),w.getSy());
				s1.setMoveable(true);
				layeredPane.add(s1, new Integer(4));
				if(pacman.getState()==2)
					s1.decreasestate();
				return;
			}
			if( s2.getSx()== -25 ){
				s2 = new Strongling(w.getSx(),w.getSy());
				s2.setMoveable(true);
				layeredPane.add(s2, new Integer(5));
				if(pacman.getState()==2)
					s2.decreasestate();
				return;
			}
		}
		else{ // the monster is a weakling, add it to w1 / w2
			if( w1.getSx()== -25 ){
				w1 = new Weakling(w.getSx(),w.getSy());
				w1.setMoveable(true);
				layeredPane.add(w1, new Integer(2));
				if(pacman.getState()>=1)
					w1.decreaseState();
				return;
			}
			if( w2.getSx()== -25 ){
				w2 = new Weakling(w.getSx(),w.getSy());
				w2.setMoveable(true);
				layeredPane.add(w2, new Integer(3));
				if(pacman.getState()>=1)
					w2.decreaseState();
				return;
			}
		}
	}

	/**
	 * the dead method kill pacman and initialize the monsters position.
	 */
	public void dead(){
		specialStateOver();
		dead=true;
		lives--;
		w1.setDot(260,220);
		while(!stage.cageEmpty()) // empty the cage to fill it properly
			release();
		stage.cageEnter(w2);
		stage.cageEnter(s1);
		stage.cageEnter(s2);
		w2.setDot(-25, -25);
		s1.setDot(-25, -25);
		s2.setDot(-25, -25);
		pacman.setMoveable(false);
		ImageIcon pacmanIcon = new ImageIcon("pacmanDead.gif");
		pacman.setIcon(pacmanIcon);
		
	}

	public void monsterDead(Weakling w){ // kill the monster w.
		w.decreaseState();
	}



	public boolean checkDeadTouch(){ // boolean method returns true if pacman is dead.
		boolean ans = false;
		// Weak monsters touch
		if((pacman.touch(w1) & pacman.getState()==0 & w1.getState()!=-1)
				|| (pacman.touch(w2) & pacman.getState()==0 & w2.getState()!=-1))
			ans = true;
		// Strong monster touch
		if((pacman.touch(s1) & pacman.getState()<2 & s1.getState()!=-1)
				|| (pacman.touch(s2) & pacman.getState()<2 & s2.getState()!=-1))
			ans = true;
		return ans;
	}

	public void killTouch(){ // check if pacman killed a monster, and kill it.
		if(pacman.touch(w1) & pacman.getState()>=1 & w1.getState()!=-1){
			monsterDead(w1);
		}
		if(pacman.touch(w2) & pacman.getState()>=1 & w2.getState()!=-1){
			monsterDead(w2);
		}
		if(pacman.touch(s1) & pacman.getState()==2 & s1.getState()!=-1){
			monsterDead(s1);
		}
		if(pacman.touch(s2) & pacman.getState()==2 & s2.getState()!=-1){
			monsterDead(s2);
		}

	}

	public void specialState(){ // special state of 10 seconds if pacman ate special food.
		if(pacman.getState()<2) // increase pacman's state
			pacman.increaseState();
		if(pacman.getState()==1){ // decrease weaklings state
			w1.decreaseState();
			w2.decreaseState();
		}
		if(pacman.getState()==2){ // decrease strongling state
			s1.decreasestate();
			s2.decreasestate();
		}
		special=true; // now it's a special state
	}

	public void specialStateOver(){ // end a certain special state.
		if(pacman.getState()>0){
			pacman.decreaseState();
			if(pacman.getState()<2){
				if(s1.getState()!=-1)
				s1.increasestate();
				if(s2.getState()!=-1)
				s2.increasestate();
				this.secondSaver+=5; // less time being Mighty
			}
			if(pacman.getState()<1){
				if(w1.getState()!=-1)
				w1.increaseState();
				if(w2.getState()!=-1)
				w2.increaseState();
				special=false; // no more special state
			}
		}
	}

	public void winGame(){ // Won the game, show the win game picture.
		ImageIcon gameOver = new ImageIcon("gameWon.png");
		JLabel over = new JLabel(gameOver);
		over.setOpaque(true);
		over.setBounds(0	, 0, 560, 620);
		layeredPane.add(over, new Integer(6)); // add the picture on top
		this.gameOver=true;
		this.repaint();
	}

	public void pacmanMove(){ // moves pacman, and checking sections and food
		int j = translate(pacman.getSx()); // translating x to j
		int i = translate(pacman.getSy());// translating y to iS
		if((stage.getFood(i, j)!=null) && (stage.getFood(i, j).isVisible())){
			if (stage.getFood(i, j).eated(pacman)) // eat the food in this section
				counterEaten++; // update counter
			if(counterEaten == 244) // all food has been eaten
				winGame();
			if((!(stage.getFood(i, j).isVisible()) && (stage.getFood(i, j) instanceof SpecialFood))){
				specialState(); // special food has been eaten, make special state.
				secondSaver=seconds; // save the second in order to stop the special state in time
			}
		}
		if(stage.getSection(i, j)!=null){
			stage.getSection(i, j).possible(pacman); // check if pacman can move in that direction
			if(stage.getSection(i, j) instanceof Portal) // pacman entered a portal
				((Portal) stage.getSection(i, j)).portal(pacman);  // portal pacman
		}
		pacman.move(); // move pacman
	}

	public void monsterMove(Weakling w){ // moves a monster, by it's intelligence and state.
		if(w.getSx()!= -25){ // the monster is on stage
			int j = translate(w.getSx()); // translate x to j
			int i = translate(w.getSy()); // translate y to i
			if(!(stage.getSection(i, j) instanceof Portal)){ // the section is no portal
				stage.getSection(i, j).possible(w); // check if it possible to move
				if(w.getState()!=-1){ // the monster is dead
					if (w instanceof Strongling){ // the monster is a strongling
						if(stage.getSection(i, j)!=w.getVisitedSection())
							if(pacman.getState()!=2)// pacman is no dangerous for strongling
								smartMove(w,stage.getSection(i, j), false); // chase pacman
							else // pacman is dangerous
								smartMove(w,stage.getSection(i, j), true); // run from pacman				
					}
					else{ // the monster is a weakling
						if(stage.getSection(i, j)!=w.getVisitedSection())
							if(pacman.getState()<1) // pacman is a danger for weakling
								stupidMove(w,stage.getSection(i, j)); // move randomly
							else // pacman is a danger
								smartMove(w,stage.getSection(i, j), true); // run from pacman
					}
				}
				else{ // the monster should go to the cage
					if(stage.getSection(i, j)!=w.getVisitedSection())
						goHome(w,stage.getSection(i, j));
				}
				if(w.getSx()!=-25){ // the monster didn't got home yet
					w.setVisitedSection(stage.getSection(i, j)); // save the section as visited for AI reasons
					w.move(); // move the monster
				}
			}
			else // the section is a portal
				((Portal) stage.getSection(i, j)).portal(w); // portal the monster
		}
	}
	public void stupidMove(Weakling w, Section s){ // move monster w to a random possible direction
		int d=5; // initialize direction
		int oldDirection = w.getOppositeDirection(); // save the opposite direction
		while(!w.isMoveable() || (d==oldDirection)){ // don't stop until you have a possible direction
			d = (int ) (Math.random()*4); // randomize a number between 0 to 3
			w.setDirection(d); // set d as a direction
			s.possible(w); // check if it's a possible direction, make the monster moveable if so
		}
	}

	/**
	 * The smartMove method is the AI of the game, the monster can chase pacman or run from him in the same method.
	 * if scare is true, the monster will run away from pacman.
	 * if scare is false, the monster will chase pacman no matter what.
	 * 
	 * The method is using pythagoras formulas in order to calculate distances from pacman in possible directions
	 * than choosing the most close / far direction depends on it's state.
	 * @param w
	 * @param s
	 * @param scare
	 */
	public void smartMove(Weakling w, Section s, boolean scare){
		double[] preference = new double[4]; // creating preference integer array, every index is a direction, every value is a distance.
		if(scare){ // the monster scares from pacman, the values is minimum
			for (int i=0; i<preference.length; i++) 
				if (w.getOppositeDirection()==i) preference[i]=Integer.MIN_VALUE; // opposite direction is no possibility for us
			if (!(s.right())) preference[0]=Integer.MIN_VALUE;
			if (!(s.left())) preference[1]=Integer.MIN_VALUE;
			if (!(s.up())) preference[2]=Integer.MIN_VALUE;
			if (!(s.down())) preference[3]=Integer.MIN_VALUE;
		}
		else{ // the monster want to catch pacman
			for (int i=0; i<preference.length; i++) 
				if (w.getOppositeDirection()==i) preference[i]=Integer.MAX_VALUE; // opposite direction is no possibility for us
			if (!(s.right())) preference[0]=Integer.MAX_VALUE;
			if (!(s.left())) preference[1]=Integer.MAX_VALUE;
			if (!(s.up())) preference[2]=Integer.MAX_VALUE;
			if (!(s.down())) preference[3]=Integer.MAX_VALUE;
		}

		if(scare){
			for (int i=0; i<preference.length; i++) {
				if (preference[i] != Integer.MIN_VALUE) { // checks if the direction is possible
					//calculate distance, different formulas depending the direction
					if (i==0) {
						preference[0] =  Math.sqrt(Math.pow((w.getSx()+10)-(pacman.getSx()),2) + 
								Math.pow((w.getSy()-pacman.getSy()),2));
					}
					if (i==1) {
						preference[1] = Math.sqrt(Math.pow((w.getSx()-10)-(pacman.getSx()),2) + 
								Math.pow((w.getSy()-pacman.getSy()),2));
					}
					if (i==2) {
						preference[2] =  Math.sqrt(Math.pow((w.getSx())-(pacman.getSx()),2) + 
								Math.pow((w.getSy()-10-pacman.getSy()),2));
					}
					if (i==3) {
						preference[3] =  Math.sqrt(Math.pow((w.getSx())-(pacman.getSx()),2) + 
								Math.pow((w.getSy()+10-pacman.getSy()),2));
					}
				}
			}
		}
		else{
			for (int i=0; i<preference.length; i++) {
				if ((preference[i] != Integer.MAX_VALUE)) {// checks if the direction is possible
					//calculate distance, different formulas depending the direction
					if (i==0) {
						preference[0] =  Math.sqrt(Math.pow((w.getSx()+10)-(pacman.getSx()),2) + 
								Math.pow((w.getSy()-pacman.getSy()),2));
					}
					if (i==1) {
						preference[1] = Math.sqrt(Math.pow((w.getSx()-10)-(pacman.getSx()),2) + 
								Math.pow((w.getSy()-pacman.getSy()),2));
					}
					if (i==2) {
						preference[2] =  Math.sqrt(Math.pow((w.getSx())-(pacman.getSx()),2) + 
								Math.pow((w.getSy()-10-pacman.getSy()),2));
					}
					if (i==3) {
						preference[3] =  Math.sqrt(Math.pow((w.getSx())-(pacman.getSx()),2) + 
								Math.pow((w.getSy()+10-pacman.getSy()),2));
					}
				}
			}
		}

		double min =Integer.MAX_VALUE; // to find the minimum value
		int minI = Integer.MAX_VALUE; // to find the smart direction
		double max =Integer.MIN_VALUE; // to find the maximum value
		int maxI = Integer.MIN_VALUE; // to find the smart direction (scare move)
		for(int i=0; i<preference.length; i++){ // loop to find maximum and minimum
			if(preference[i]<min){
				min = preference[i];
				minI=i;
			}
			if(preference[i]>max){
				max = preference[i];
				maxI=i;
			}
		}
		if(scare)
			w.setDirection(maxI); // set a running away direction
		else
			w.setDirection(minI); // set a chasing direction
	}
	
	/**
	 * the goHome method is after a monster is dead.
	 * making it running back to the cage and waiting for revival.
	 * @param w
	 * @param s
	 */
	public void goHome(Weakling w, Section s){
		w.setSpeed(10);
		if(s != stage.getSection(11, 14)){
			double[] preference = new double[4]; // creating preference integer array, every index is a direction, every value is a distance.
			for (int i=0; i<preference.length; i++) 
				if (w.getOppositeDirection()==i) preference[i]=Integer.MAX_VALUE; // opposite direction is no possibility for us
			if (!(s.right())) preference[0]=Integer.MAX_VALUE;
			if (!(s.left())) preference[1]=Integer.MAX_VALUE;
			if (!(s.up())) preference[2]=Integer.MAX_VALUE;
			if (!(s.down())) preference[3]=Integer.MAX_VALUE;

			for (int i=0; i<preference.length; i++) {
				if ((preference[i] != Integer.MAX_VALUE)) {// checks if the direction is possible
					//calculate distance, different formulas depending the direction
					if (i==0) {
						preference[0] =  Math.sqrt(Math.pow((w.getSx()+1)-(280),2) + 
								Math.pow((w.getSy()-220),2));
					}
					if (i==1) {
						preference[1] = Math.sqrt(Math.pow((w.getSx()-1)-(280),2) + 
								Math.pow((w.getSy()-220),2));
					}
					if (i==2) {
						preference[2] =  Math.sqrt(Math.pow((w.getSx())-(280),2) + 
								Math.pow((w.getSy()-1-220),2));
					}
					if (i==3) {
						preference[3] =  Math.sqrt(Math.pow((w.getSx())-(280),2) + 
								Math.pow((w.getSy()+1-220),2));
					}
				}
			}
			double min =Integer.MAX_VALUE; // to find the minimum value
			int minI = Integer.MAX_VALUE; // to find the smart direction
			for(int i=0; i<preference.length; i++){ // loop to find maximum and minimum
				if(preference[i]<min){
					min = preference[i];
					minI=i;
				}
			}
			w.setDirection(minI); // set a direction
		} else{ // the monster arrived to the cage
			w.setDot(-25, -25);
			stage.cageEnter(w);
		}

	}

	public int translate(int x){ // this method is to translate a specific pixel to a specific index of the matrix.
		int j = x+8;
		while (j%20!=0)
			j=j-1;
		j=j/20;
		return j;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {// every 0.05 seconds
		seconds+=0.05; // update seconds
		if(pacman.getState()==0) // normal
			pacman.setSpeed(5);
		if(pacman.getState()==1) // mighty
			pacman.setSpeed(6);
		if(pacman.getState()==2) // super
			pacman.setSpeed(8);
		if(seconds > secondDead+1){ // only one second after pacman died
			if(dead){ // pacman is dead :(
				pacman.setDot(270, 460);
				dead=false;
				if(lives==0){ // no more lives, the game is over.
					ImageIcon gameOver = new ImageIcon("gameOver.png");
					JLabel over = new JLabel(gameOver);
					over.setOpaque(true);
					over.setBounds(0	, 0, 560, 620);
					layeredPane.add(over, new Integer(6)); // game over is the top layer
					this.gameOver=true;
					this.repaint();
				}
				ImageIcon pacmanIcon = new ImageIcon("pacmanLeft.gif"); // revive pacman
				pacman.setIcon(pacmanIcon);
			}
			if((seconds > secondSaver+10) & (special)){ // ending special state if 10 seconds are over
				specialStateOver();
				if(pacman.getState()==1)
					secondSaver = seconds;
			}
			if (!stage.cageEmpty()&& waitingForMonster){ // more monsters to release
				secondMonster = seconds; // update the seconds saver for monster releasing
				waitingForMonster=false; 
			}
			if(!waitingForMonster && seconds > secondMonster+5){ // release monster
				release();
				waitingForMonster=true;
			}
			pacmanMove(); // moves pacman
			monsterMove(w1); // move w1
			monsterMove(w2); // move w2
			monsterMove(s1); // move s1
			monsterMove(s2); // move s2
			if(checkDeadTouch()){ // a monster killed pacman
				dead(); // kill pacman
				secondDead=seconds; // save the time for revival
			}
			killTouch(); // check if pacman touch a monster
			if(gameOver) // game is over
				timer.stop(); // stop timer
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		timer.start();
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			pacman.setDirection(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			pacman.setDirection(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			pacman.setDirection(2);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			pacman.setDirection(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
