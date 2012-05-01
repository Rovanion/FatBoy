import java.awt.Button;
import java.awt.Container;
import java.awt.TextField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class main {

	/**
	 * Main jump starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameCanvas newCanvas = new GameCanvas();
		CreateFrame frame = new CreateFrame(newCanvas);
	}

	public static void endGame() {
		HighscoreManager hm = new HighscoreManager();
		String inputName = null;
		int points = 0;

		// Här skapas det ett förnster för att ta in namn och lägga till det
		// till poängen"
	
		hm.addScore(inputName, points);

			//
			// Skapa ett fönster.
			//
			JFrame fönster = new JFrame();
			fönster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fönster.setSize(200, 150); // Bredd och höjd.
			fönster.setLocation(100, 100); // x och y.
			fönster.setTitle("Ange ditt namn");

			//
			// Skapa knappen och textrutan.
			//
			JButton knapp = new JButton("Klar");
			JTextArea textruta = new JTextArea();

			//
			// Man lägger komponenterna i en
			// "content pane" (innehållspanel).
			//
			Container innehåll = fönster.getContentPane();

			//
			// Här talar vi om att vi inte vill ha någon
			// automatisk layouthantering av komponenterna
			// i fönstret (null = ingenting/tomt).
			//
			innehåll.setLayout(null);

			//
			// Nu lägger vi in komponenterna och positionerar dem.
			// Koordinatsystemet börjar i övre vänstra hörnet,
			// y växer nedåt. Enheten är bildpunkter.
			//
			innehåll.add(knapp);
			innehåll.add(textruta);

			//
			// Argumenten till setBounds() är x, y, bredd, höjd.
			//
			textruta.setBounds(10, 10,180 ,20);
			knapp.setBounds(65, 40, 70, 40);

			//
			// Öppna fönstret.
			//
			fönster.show();
		
		
		System.out.print(hm.getHighscoreString());
		
//System.exit(0);

	}
}
