package PacMan;

import javax.swing.ImageIcon;

public class SpecialFood extends Food {
	
	private static final long serialVersionUID = 9125576422481129307L;

	/**************Constructor*************/
	
	/**
	 * the special food is almost the same of normal food, but it has another picture, 
	 * and special features in the game class.
	 * @param x
	 * @param y
	 */
	public SpecialFood(int x,int y){
		super(x,y);
		ImageIcon pacmanIcon = new ImageIcon("Sfood.png");
		food.setIcon(pacmanIcon);
		food.setSize(Isize, Isize);
		food.setPreferredSize(getSize());
		this.add(food);
		setSize(Isize, Isize);
		setPreferredSize(getSize());
	}
}
