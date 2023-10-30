
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")

public class PacMan extends Mover {

	//creates a 2D array
	public static final ImageIcon[][] IMAGE = {
			
			{new ImageIcon("images/pacman_left.gif"),new ImageIcon("images/images/pacman_left.gif")},
			{new ImageIcon("images/images/pacman_up.gif"),new ImageIcon("images/pacman_up.gif")},
			{new ImageIcon("images/pacman_right.gif"),new ImageIcon("images/pacman_right.gif")},
			{new ImageIcon("images/pacman_down.gif"),new ImageIcon("images/pacman_down.gif")}
	};
	
	//constructor
	PacMan() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		this.setIcon(IMAGE[0][0]); //start PacMan facing left with a closed mouth
	}
}
