package PacMan;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class Stage extends JPanel{

	private static final long serialVersionUID = -1347565080438203957L;

	/**************Fields************/

	private Cage cage; // Represents the cage in the middle of the stage.
	private final Board b = new Board(); // Represent the board of the game (the Map).
	private Section[][] stage; // Represents all the sections in the stage.
	private Food[][] foods; // Represents all the food in the game.
	private JLayeredPane layeredPane; // the layered panel in the stage.
	private JLabel background; // the background of the stage.

	/**************Constructors*********/
	public Stage(){
		layeredPane = new JLayeredPane();
		cage = new Cage(); // creating the cage.
		cage.setOpaque(false);
		cage.setBounds(230, 275, 100, 100);
		background = new JLabel(); // creating the background
		ImageIcon icon = new ImageIcon("board.png");
		background.setIcon(icon);
		background.setOpaque(false);
		background.setBounds(0, 0, 560, 620);
		layeredPane.setPreferredSize(new Dimension(560,620));
		layeredPane.add(background, new Integer(0)); // the background is the first layer.
		layeredPane.add(cage,new Integer(2)); // the cage is the most top layer.

		init(); // Initializing the whole stage.

		layeredPane.setOpaque(true);
		this.add(layeredPane);
		this.setVisible(true);
	}
	
	/************Getters-Setters************/
	
	public Food getFood(int i, int j){
		return foods[i][j];
	}

	public Section getSection(int i, int j){
		return stage[i][j];
	}
	
	
	
	/************Methods**************/

	/**
	 * the init method analyzing the whole stage, by translating the board class.
	 * analayzing the availability of each section and builds portal and special food labels.
	 */
	public void init(){

		JPanel foodPanel = new JPanel(); // creating panel for the food labels
		foodPanel.setLayout(new GridLayout(31,28)); // this is the layout of all the foods in the stage.
		this.stage = new Section[b.template.length][b.template[0].length]; // initializing the sections matrix
		this.foods = new Food[b.template.length][b.template[0].length]; // initializing the food matrix

		for (int i=0; i<b.template.length; i++)
			for (int j=0; j<b.template[0].length; j++){
				// the 2 loops going all over the 2 matrix, and initialize it.

				int f = b.template[i][j]; // the int value of this index
				int x = j * 20; // making x coordinate
				int y = i * 20; // making y coordinate

				if(f==0){ 
					// the int value is 0, there's no section and no food, and the slot is unavailable.
					stage[i][j]=null;
					foods[i][j]=null;
					JLabel emptyLabel = new JLabel(new ImageIcon("empty.png"));
					emptyLabel.setOpaque(false);
					foodPanel.add(emptyLabel);
				}
				if (f==1){
					// the int value is 1, there's an empty section.
					stage[i][j] = new Section(x,y);
					foods[i][j]=null;
					checkSection(stage[i][j],i,j); // determine the turns in this section
					JLabel emptyLabel = new JLabel(new ImageIcon("empty.png"));
					emptyLabel.setOpaque(false);
					foodPanel.add(emptyLabel);
				}
				if(f==2){
					// the int value is 2, there's a section with normal food in it.
					stage[i][j] = new Section(x,y);
					foods[i][j] = new Food(x,y);
					checkSection(stage[i][j],i,j); // determine the turns in this section
					foodPanel.add(foods[i][j]);
				}
				if(f==3){
					// the int value is 3, there's a section with special food in it.
					stage[i][j] = new Section(x,y);
					foods[i][j] = new SpecialFood(x+5,y+5);
					checkSection(stage[i][j],i,j); // determine the turns in this section
					foodPanel.add(foods[i][j]);
				}
				if(f==4){
					// the int value is 4, there's an empty portal.
					stage[i][j] = new Portal(x,y);
					if((i==0)&&(j==14))
						stage[i][j].setRight(); // this is the eastern portal
					if((i==27)&&(j==14))
						stage[i][j].setLeft(); // this is the western portal
					JLabel emptyLabel = new JLabel(new ImageIcon("empty.png"));
					emptyLabel.setOpaque(false);
					foodPanel.add(emptyLabel);
				}

			}

		foodPanel.setBounds(0, 0, 560, 620);
		foodPanel.setOpaque(false);
		layeredPane.add(foodPanel, new Integer(1)); // the food panel is the middle layer.


	}
	
	/**
	 * the checkSection method receives a section and it's position in the matrix.
	 * it checks it's neighbors and determine it's availability
	 * @param s
	 * @param i
	 * @param j
	 */
	public void checkSection(Section s, int i, int j){
		if(b.template[i-1][j]!=0){ // upper neighbor is not null
			s.setUp();
		}
		if(b.template[i+1][j]!=0){ // bottom neighbor is not null
			s.setDown();
		}
		if(b.template[i][j-1]!=0){ // left neighbor is not null
			s.setLeft();
		}
		if(b.template[i][j+1]!=0){ // right neighbor is not null
			s.setRight();
		}
	}

	public Weakling cageExit(){ // takes out a monster in the cage.
		return cage.exit();
	}

	public void cageEnter(Weakling w){ // enters a monster to the cage.
		cage.enter(w);
	}

	public boolean cageEmpty(){ // true if the cage is empty
		return cage.isEmpty();
	}


}
