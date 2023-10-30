import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*.
 * Project Header
 * Name: Svetha Thananchayan
Date:
Course Code: ICS3U8-01
Title: PacMan Game
Description: This project is the create a game where a charter (PacMan) collect coins and has to win the game before getting killed by the ghosts
Major Skills: Jpanels, methods,  getters, setters, action listener, GUI, Key listener, Scanner, Image icon, colour,  
Added Features:Changes background colour, changed characters 
Areas of Concern:non

 */



/**
 * This class is used to create a new PacManGUI that will 'start a PacMan game
 */


//
public class PacManGame {

	// this is the main method 
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

		//
		new WelcomeScreen();
	}

}
