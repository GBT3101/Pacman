package PacMan;

public class Portal extends Section {

	private static final long serialVersionUID = 5883101636336813733L;
	
	/************Constructor************/
	
	public Portal(int x, int y) {
		super(x, y);
	}
	
	
	
	/**************Methods**************/
	
	/**
	 * the portal Method sends the figure f to another position, depends on it's
	 * position by x manners.
	 * @param f
	 */
	public void portal(Figure f){
		int speed = f.getSpeed();
		if(this.getSx()<200)
			f.setDot(500,280);
		else
			f.setDot(20, 280);
		f.setSpeed(speed);
	}

}
