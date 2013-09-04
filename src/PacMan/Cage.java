package PacMan;

import javax.swing.*;

public class Cage extends JPanel  {


	/*************Fields*************/

	private static final long serialVersionUID = -6957161957122684477L;
	
	private Weakling w1; // Represent a weakling in the cage.
	private Weakling w2; // Represent a weakling in the cage.
	private Strongling s1; // Represent a Strongling in the cage.
	private Strongling s2; // Represent a Strongling in the cage.

	/********Constructors***********/ 

	public Cage(){ // Starts with 3 monsters inside.
		w1 = null;
		s1 = null;
		s2 = null;
	}

	/*************Method***********/
	
	/**
	 * the exit method removes a monster from the cage.
	 * it has a preference for weak monsters before strong ones.
	 * @return
	 */
	public Weakling exit(){

		Weakling ans = null;
		
		if(w1!=null){
			ans = w1;
			remove(w1);
			w1=null;
		}
		else if(w2!=null){
			ans = w2;
			remove(w2);
			w2=null;
		}
		else if(s1!=null){
			ans = s1;
			remove(s1);
			s1=null;
		}
		else if(s2!=null){
			ans = s2;
			remove(s2);
			s2=null;
		}
		this.repaint();
		ans.setDot(270,220);
		return ans;
	}
	
	/**
	 * the enter methods enters a monster w to the cage.
	 * @param w
	 */
	public void enter(Weakling w){
		if (!(w instanceof Strongling)){
			if(w1==null){
				w1 = new Weakling(0,0);
				add(w1);
			}
			else if(w2==null){
				w2 = new Weakling(25,0);
				add(w2);
			}
		}
		else{
			if(s1==null){
				s1 = new Strongling(50,0);
				add(s1);
			}
			else if(s2==null){
				s2 = new Strongling(75,0);
				add(s2);
			}
		}
		this.repaint();
	}
	
	public boolean isEmpty(){ // true if the cage is empty
		return (w1==null)&&(w2==null)&&(s1==null)&&(s2==null);
	}
	
	public boolean isFull(){ // true if the cage is full
		int counter=0;
		if(w1!=null)
			counter++;
		if(w2!=null)
			counter++;
		if(s1!=null)
			counter++;
		if(s2!=null)
			counter++;
		
		return counter>=3;
	}

}
