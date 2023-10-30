/**
 * 
 */

import javax.swing.ImageIcon;

@SuppressWarnings("serial")

public class Ghost extends Mover {
	
	//1D moving
	public static final ImageIcon[] IMAGE = {
			
			new ImageIcon("images/blinky.png"), new ImageIcon("images/inky.png"), new ImageIcon("images/clyde.png"), new ImageIcon("images/pinky.png")
	};

	//constructor 
	public Ghost(int ghostNum) {
		
		//set the picture
		this.setIcon(IMAGE[ghostNum]);		
	}
}
