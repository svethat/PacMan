/**
 * This class represent the game board and includes methods to 
 * handle keyboard events and game actions
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Board extends JPanel implements KeyListener, ActionListener{
	
	// creates game timer
	private Timer gameTimer = new Timer(250, this);
	
	// set animate timer 
	private Timer animateTimer = new Timer(50, this);
	
	// creates image constants 
	private static final ImageIcon WALL = new ImageIcon("images/Wall.bmp");	
	
	// creates food constants
	private static final ImageIcon FOOD = new ImageIcon("images/food.png");	
	
	//creates Power Pellets constants
	private static final ImageIcon POWERPELLET = new ImageIcon("images/Power_up.png");
	
	// creates blank spots constants
	private static final ImageIcon Blank = new ImageIcon("images/Black.bmp");	

	// creates door constants
	private static final ImageIcon Door = new ImageIcon("images/Black.bmp");	

	// creates skull constants
	private static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");
	
	private static final ImageIcon gameOverScreen = new ImageIcon("images/game_over.png");
	
	private static final ImageIcon frightened_ghost = new ImageIcon("images/frightened_ghost.png");
	
	private static final ImageIcon Score = new ImageIcon("images/score.jpg");
	
	private static final ImageIcon hearts = new ImageIcon("images/Pac-Man Hearts.png");
		
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
	
	private static final Icon BLANK = null;

	private static final Icon DOOR = null;	
	
	JLabel scoreLabel = new JLabel("Score: 0");
	
	//public String ScoreCount = "Score: " + score;

	//set up arrays
	
	// creates maze array, which holds the letter characters from the file
	private char[][] maze = new char[25][27];
	
	// cell array which will hold the images 
	private JLabel [][] cell = new JLabel [25][27];
	
	//pacMan class
	private PacMan pacMan;
	
	//gosht array
	private Ghost[] ghost = new Ghost[4];
	
	//variable for the  food
	private int pellets = 0;
	
	private int powerPellets;
	
	// varibale for the scorse of the food
	private int score = 0;
	
	//the variable for the PacMans steps
	private int pStep;
		
	private boolean running = false;
	
	boolean fleeing = true;
	
	private Thread thread;
	
	static File file2  = new File("sounds/Pacman moving.wav");
	AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
	Clip film2 = AudioSystem.getClip();
	
	// construtor method for thr board
	
	public Board() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		//sets layout
		setLayout(new GridLayout(25,27));
		setBackground(Color.black);
		
		//creates pacMan object
		pacMan = new PacMan();
		
		ghost[0] = new Ghost(0);
		ghost[1] = new Ghost(1);
		ghost[2] = new Ghost(2);
		ghost[3] = new Ghost(3);
		
		//load the action baord
		loadBoard();
		
		themeSong();
		
		pacmanMovingSound();
	}
	
	//method to lead in the information from file
	private void loadBoard() {
		
		//create a variable for the row
		int row = 0;
		
		running = true;
		
		//create the scanner
		Scanner input;
		
		// opening up
		try {
			
			input = new Scanner(new File("maze.txt"));
			
			//read through the file
			while(input.hasNext()) {
				
				//rip apart every like
				maze[row] = input.nextLine().toCharArray();
				
				//going column by column
				for (int column = 0; column < maze[row].length; column++) {
				
					//
					cell[row][column] = new JLabel();
					
					//depending on the letter...
					
					//check to see which letter
					if (maze[row][column] == 'W')
						
						cell[row][column].setIcon(WALL);
					
					//sets the food
					else if (maze[row][column] == 'F') {
			
						
						cell[row][column].setIcon(FOOD);
						
						//counts the pellets
						pellets++;
					}
					
					else if(maze[row][column] == 'V') {
						
						cell[row][column].setIcon(POWERPELLET);
						
						//counts the pellets
						powerPellets++;
					}
					
					else if(maze[row][column] == 'N') {
						
						cell[row][column].setIcon(Score);
					}
					
					//sets the door
					else if  (maze[row][column] == 'D')
						
						cell[row][column].setIcon(DOOR);
					
					//sets the blanks
					else if (maze[row][column] == 'X')
						
						cell[row][column].setIcon(BLANK);
					
					else if(maze[row][column] == 'H')
						
						cell[row][column].setIcon(hearts);
					
					//sets the pacMan
					else if (maze[row][column] == 'P') {
						
						cell[row][column].setIcon(pacMan.getIcon());
					
						//where pac man is
						pacMan.setRow(row);
						pacMan.setColumn(column);
						pacMan.setDirection(0); //facing left 
					}
						else if (maze[row][column] == '0' || maze[row][column] =='1' || maze[row][column] =='2'|| maze[row][column] == '3') {
						
						//creats a variable to convert into numeric values
						int ghostNum = Character.getNumericValue(maze[row][column]);
					
						cell[row][column].setIcon(ghost[ghostNum].getIcon());
							
					ghost[ghostNum].setRow(row);
					ghost[ghostNum].setColumn(column);
				}
					//sets a grid layout
					add(cell[row][column]);		
				}
					//go up by own for the rows
					row++;	
			}
				//cloes file
				input.close();
			
		//close up	
		} catch(FileNotFoundException error) {
			
			System.out.println("File not found");	
		}
	}
	
	private void themeSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//Plays theme song
		File file1  = new File("sounds/Pacman Theme Song.wav");
		AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
		Clip film1 = AudioSystem.getClip();
		film1.open(audioStream1);
		film1.start(); 
	}
	
	private void pacmanMovingSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//Plays pacMan moving sound
		film2.open(audioStream2);
		film2.start();
	}
	
	private void collidedPellets() {
		
		for(int pelletsNum = 0; pelletsNum < pellets; pelletsNum++) {
			
			//if(pellets[pelletsNum].getRow() == pacMan.getRow()) {
				
			//}
		}
	}
	
	//user presses the key
	@Override
	public void keyPressed(KeyEvent key) {
		
		//is it running
		if (running = true && pacMan.isDead() == false)
			gameTimer.start();
	
		//are they allowed to moves
		if (pacMan.isDead() == false && score != pellets){
		
			//which direction
			int direction = key.getKeyCode() - 37;
			
			if (direction == 0 && maze[pacMan.getRow()][pacMan.getColumn() -1] != 'W') //left
				pacMan.setDirection(0);
			
			else if (direction == 1 && maze[pacMan.getRow()-1][pacMan.getColumn()] != 'W') //up
				pacMan.setDirection(1);
			
			else if (direction == 2 && maze[pacMan.getRow()][pacMan.getColumn() +1] != 'W') //right
				pacMan.setDirection(2);
			
			else if (direction == 3 && maze[pacMan.getRow()+1][pacMan.getColumn()] != 'W') //down
				pacMan.setDirection(3);
		}
		
			//Pauses game
			if(key.getKeyCode() == KeyEvent.VK_ENTER) {
				gameTimer.stop();
				animateTimer.stop();
				film2.stop();
			}
			//Resume game
			if(key.getKeyCode() == KeyEvent.VK_R) {
				film2.start();
			}
			
			//restart the timers
			if(key.getKeyCode() == KeyEvent.VK_SPACE) {
				if (pacMan.isDead()) {
					running = true;
					animateTimer.restart();
					gameTimer.restart();
					pellets = 0;
					score = 0;
					pStep = 0;
					try {
						new Board();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						 //TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
				repaint();
		}
	}
	
	public void Score(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("Ariale", Font.BOLD,40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + pellets,  (900 - metrics.stringWidth("Score: " + pellets))/2, g.getFont().getSize());
	    //System.out.println("Is Happening ");
	}
	
	public void eatsPowerpellet() {
		
		//if () {
			
			//cell[ghost[0].getRow()][ghost[0].getColumn()].setIcon(frightened_ghost);
			//cell[ghost[1].getRow()][ghost[1].getColumn()].setIcon(frightened_ghost);
			//cell[ghost[2].getRow()][ghost[2].getColumn()].setIcon(frightened_ghost);
			//cell[ghost[3].getRow()][ghost[3].getColumn()].setIcon(frightened_ghost);
		//}
	}
	
	// this method must remain to staisfy the keyListerner code interface
	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
			
	}
	
	// this method must remain to staisfy the keyListerner code interface
	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}
	
	public void flee() {
		
		if(fleeing) {
			cell[ghost[0].getRow()][ghost[0].getColumn()].setIcon(frightened_ghost);
			cell[ghost[1].getRow()][ghost[1].getColumn()].setIcon(frightened_ghost);
			cell[ghost[2].getRow()][ghost[2].getColumn()].setIcon(frightened_ghost);
			cell[ghost[3].getRow()][ghost[3].getColumn()].setIcon(frightened_ghost);
		}
	}
	
	//public void notFlee() {
		
		//if(!fleeing) {
			//cell[ghost[0].getRow()][ghost[0].getColumn()].setIcon();
			//cell[ghost[1].getRow()][ghost[1].getColumn()].setIcon();
			//cell[ghost[2].getRow()][ghost[2].getColumn()].setIcon();
			//cell[ghost[3].getRow()][ghost[3].getColumn()].setIcon();
		//}
	//}
	
	private void performMove( Mover mover) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
		//to see if the mover will touch the door way
		if (mover.getColumn() == 1) {
			mover.setColumn(24);
			cell[12][1].setIcon(DOOR);
		} else if (mover.getColumn() == 25) {
			mover.setColumn(2);
			cell[12][25].setIcon(DOOR);
		}
		
		if (maze[mover.getNextRow()][mover.getNextColumn()] != 'W') {
			
			//if the mover is pacMan
			if (mover == pacMan)
			animateTimer.start();
			
			//
			else {
				if (maze[mover.getRow()][mover.getColumn()]== 'F')
				    	cell[mover.getRow()][mover.getColumn()].setIcon(FOOD);
				
				else
				    cell[mover.getRow()][mover.getColumn()].setIcon(BLANK);    

				mover.move();
				
				if (collided())
					death();
				
				else 
				    cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());    
				}
			}
		}	
	
		private boolean collided() {
		
		for(int ghostNum = 0; ghostNum < 3; ghostNum++){
			
			if (ghost[ghostNum].getRow()==pacMan.getRow() && ghost[ghostNum].getColumn() == pacMan.getColumn())
				return true;
			}
		
		return false;
		
		}

		private void death() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//pacMan died
		pacMan.setDead(true);
		
		//stops the game
		stopGame();	
		
		cell[pacMan.getRow()][pacMan.getColumn()].setIcon(SKULL);
		//cell[hearts.getRow()][hearts.getColumn()].setIcon(SKULL);
		
		}
			
		//stopping the game
		private void stopGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
			
			File file3 = new File("sounds/Pacman killed.wav");
			AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(file3);
			Clip film3 = AudioSystem.getClip();
			film3.open(audioStream3);
			
			//if the game stops
			if (pacMan.isDead() || score == pellets) {
				//turns off the animation
				animateTimer.stop();
				//turns off the timers
				gameTimer.stop();
				film3.start();
				film2.stop();
			}
		}
		
		//public void pelletsEaten() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			
			//if(score == pellets) {
				//new Board();
			//}
		//}
	
		//move the ghost
		private void moveGhost() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			
			//for loop going through the ghost
			for(Ghost currentGhost: ghost) {
			
				int direction = 0;
				
				//
				do {
					
				direction = (int)(Math.random()*4);
			} while (Math.abs(currentGhost.getDirection() - direction) == 2);
			
				//
				currentGhost.setDirection(direction);
				
				//moving of the ghost
				performMove(currentGhost);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == gameTimer) {

				try {
					performMove(pacMan);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					moveGhost();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//animation timer
			} else if (event.getSource() == animateTimer) {
				
				//animate pacMan
				animatePacMan();
				
				// p step makes a click
				pStep++;
				
				//the steps
				if (pStep == 3)
					pStep = 0;		
			}
			
			if(pacMan.isDead()) {
				GameOverScreen screen = new GameOverScreen();
				try {
					gameOverSound();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		private void gameOverSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			
			File file4 = new File("sounds/Game Over Voice - Sound Effect.wav");
			AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(file4);
			Clip film4 = AudioSystem.getClip();
			film4.open(audioStream4);
			film4.start();
		}
		
		//animate pacMan
		private void animatePacMan() {
			
			//step 0 for moving pacMan- opeing his mouth
			if (pStep == 0) {
				
				//movemnt of the mouth
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon
				(PacMan.IMAGE[pacMan.getDirection()][1]);
				
				//animate timer
				animateTimer.setDelay(100);
			}
			
			else if (pStep == 1) {

				//make blank
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(Blank);

			}
			
			else if (pStep == 2) {
				
				// move pacMan
				pacMan.move();
				
				//check if he landed on food
				if (maze[pacMan.getRow()][pacMan.getColumn()]=='F') {
					
					//Score 
					score++;
					
					maze[pacMan.getRow()][pacMan.getColumn()]='E';
				}
				//stop the animateTimer
				animateTimer.stop();
				
				//if pacMAn dies
				if(pacMan.isDead())
					cell[pacMan.getRow()][pacMan.getColumn()].setIcon(SKULL);

				//close the mouth 
				else
					
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon
				(PacMan.IMAGE[pacMan.getDirection()][0]);	
			}
		}
	}