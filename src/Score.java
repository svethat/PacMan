import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")

public class Score extends JPanel {
	
	public static JLabel scoreLabel = new JLabel(); 
	
	public Score() { 
		
		panelSetup(); 
	}

	private void panelSetup() {
		// TODO Auto-generated method stub
		
		setBounds(0, 750, 855, 10000); 
		setBackground(Color.BLUE); 
		setLayout(null); 
		
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Cominc Sans MS", Font.BOLD, 30));
		scoreLabel.setBounds(300, 715, 1000, 50);
		
		add(scoreLabel); 
	}
}