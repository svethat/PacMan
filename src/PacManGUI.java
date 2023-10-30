/**
 *  this class creates a PacMan GUI that extends the JFrame class. It has a Board (JPanel) and
 *  include a constuctor method that sets up the frame and adds a key listerner to the board.
 *
 */

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

@SuppressWarnings("serial")

public class PacManGUI extends JFrame{
	
	//create the board
	Board board = new Board();
	
	static final int CANVAS_WIDTH  = 900;
	static final int CANVAS_HEIGHT  = 900;
	
	// consturctor method
	
	PacManGUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		//this sets the size
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		setTitle("PacMan - Svetha Thananchayan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setLocationRelativeTo(null);
		setResizable(false);
		
		//
		addKeyListener(board);
		add(board);
		
		//makeing the screen visable 
		setVisible(true);
	}
}