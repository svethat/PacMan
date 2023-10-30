import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOverScreen{

	JFrame frame = new JFrame();
	
	JLabel gameOverScreen = new JLabel(new ImageIcon("images/Game_Over_Screen.png"));
	
	GameOverScreen(){

		frame.setTitle("Game Over Screen!!!");
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		gameOverScreen.setLayout(null);
		frame.add(gameOverScreen);
	}
}
