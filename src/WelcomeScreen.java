import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomeScreen extends JFrame implements ActionListener{

	static final int CANVAS_WIDTH  = 900;
	static final int CANVAS_HEIGHT  = 900;
	JButton startButton = new JButton(new ImageIcon("images/start-button.png"));
	JButton aboutButton = new JButton("Start");
	JLabel gettingChased = new JLabel(new ImageIcon("GIF/Pacman getting chased2.gif"));
	JLabel beingChased = new JLabel(new ImageIcon("GIF/Pacman chasing.gif"));
	JLabel welcomeText = new JLabel("Welcome to Pac-man");
	
	WelcomeScreen(){
		
		this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		this.setTitle("Welcome Screen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.BLACK);
		this.setVisible(true);
		
		gettingChased.setBounds(210,100,480,480);		
		this.add(gettingChased);
		
		welcomeText.setBounds(170,20,600,100);
		welcomeText.setFont(new Font("ink free", Font.BOLD, 60));
		welcomeText.setForeground(new Color(255,255,0));
		this.add(welcomeText);
		//this.add(beingChased);
		
		//addKeyListener();
		//add();
		
		//Making the screen visable 
		startButton.setBounds(200,550,64,64);
		this.add(startButton);
		startButton.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == startButton) {
			try {
				PacManGUI pacManGUI = new PacManGUI();
				this.dispose();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}