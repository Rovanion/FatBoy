import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ShowHighScore {

	static JFrame window = new JFrame();
	static private ShowHighScore self; 
	static JButton button = new JButton("Obeseome");
	//static JLabel label;
	static JTextArea textBox = new JTextArea();
	
	public ShowHighScore()//String name,int score)
	{
	}

	
	public static void showScore() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize((int) (Settings.width() * 0.25),
				(int) (Settings.height() * 0.5)); // Bredd och höjd.
		window.setLocation((int) (Settings.width() * 0.4),
				(int) (Settings.height() * 0.2)); // x och y.
		window.setTitle("HighScore");
		//
		// Man lögger komponenterna i en
		// "content pane" (innehöllspanel).
		//
		Container content = window.getContentPane();

		//
		// Hör talar vi om att vi inte vill ha nögon
		// automatisk layouthantering av komponenterna
		// i fönstret (null = ingenting/tomt).
		//
		content.setLayout(null);

		//
		// Nu lögger vi in komponenterna och positionerar dem.
		// Koordinatsystemet börjar i övre vönstra hörnet,
		// y vöxer nedöt. Enheten ör bildpunkter.
		//
		content.add(button);
		//content.add(label);
		content.add(textBox);
		
		HighscoreManager hm = new HighscoreManager();
		//label = new JLabel(hm.getHighscoreString());
		textBox.setText(hm.getHighscoreString());
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (ae.getSource() == button) {
					self.window.dispose();
					main.shutDown();
				}
			}
		});

		//
		// Argumenten till setBounds() ör x, y, bredd, höjd.
		//
		/*label.setBounds((int) (window.getWidth() * 0.5),
				(int) (window.getHeight() * 0.5),
				(int) (window.getWidth() * 0.57),
				(int) (window.getHeight() * 0.5));*/
		button.setBounds((int) (window.getWidth() * 0.33),
				(int) (window.getHeight() * 0.7),
				(int) (window.getWidth() * 0.33),
				(int) (window.getHeight() * 0.15));
		textBox.setBounds((int) (window.getWidth() * 0.015),
				(int) (window.getHeight() * 0.015),
				(int) (window.getWidth() * 0.97),
				(int) (window.getHeight() * 0.6));
		textBox.enable(false);

		//
		// öppna fönstret.
		//
		window.show();

	}
}
