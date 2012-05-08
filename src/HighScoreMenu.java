import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HighScoreMenu {

	static JFrame window = new JFrame();
	static JButton knapp = new JButton("Klar");
	static JTextArea textruta = new JTextArea();
	static JLabel label = new JLabel("Ange ditt namn:");
	static String name = new String();

	public static void inputName() {

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize((int) (Settings.width() * 0.25),
				(int) (Settings.height() * 0.25)); // Bredd och höjd.
		window.setLocation((int) (Settings.width() * 0.4),
				(int) (Settings.height() * 0.4)); // x och y.
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
		content.add(knapp);
		content.add(label);
		content.add(textruta);
		knapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (ae.getSource() == knapp) {
					name = textruta.getText();
					main.shutDown();
				}
			}
		});

		//
		// Argumenten till setBounds() ör x, y, bredd, höjd.
		//
		label.setBounds((int) (window.getWidth() * 0.015),
				(int) (window.getHeight() * 0.005),
				(int) (window.getWidth() * 0.97),
				(int) (window.getHeight() * 0.20));
		textruta.setBounds((int) (window.getWidth() * 0.015),
				(int) (window.getHeight() * 0.20),
				(int) (window.getWidth() * 0.97),
				(int) (window.getHeight() * 0.10));
		knapp.setBounds((int) (window.getWidth() * 0.33),
				(int) (window.getHeight() * 0.35),
				(int) (window.getWidth() * 0.33),
				(int) (window.getHeight() * 0.20));

		//
		// öppna fönstret.
		//
		window.show();

	}

	public static String getName() {
		return name;
	}

}
