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

		// H�r skapas det ett f�rnster f�r att ta in namn och l�gga till det
		// till po�ngen"
	
		hm.addScore(inputName, points);

			//
			// Skapa ett f�nster.
			//
			JFrame f�nster = new JFrame();
			f�nster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f�nster.setSize(200, 150); // Bredd och h�jd.
			f�nster.setLocation(100, 100); // x och y.
			f�nster.setTitle("Ange ditt namn");

			//
			// Skapa knappen och textrutan.
			//
			JButton knapp = new JButton("Klar");
			JTextArea textruta = new JTextArea();

			//
			// Man l�gger komponenterna i en
			// "content pane" (inneh�llspanel).
			//
			Container inneh�ll = f�nster.getContentPane();

			//
			// H�r talar vi om att vi inte vill ha n�gon
			// automatisk layouthantering av komponenterna
			// i f�nstret (null = ingenting/tomt).
			//
			inneh�ll.setLayout(null);

			//
			// Nu l�gger vi in komponenterna och positionerar dem.
			// Koordinatsystemet b�rjar i �vre v�nstra h�rnet,
			// y v�xer ned�t. Enheten �r bildpunkter.
			//
			inneh�ll.add(knapp);
			inneh�ll.add(textruta);

			//
			// Argumenten till setBounds() �r x, y, bredd, h�jd.
			//
			textruta.setBounds(10, 10,180 ,20);
			knapp.setBounds(65, 40, 70, 40);

			//
			// �ppna f�nstret.
			//
			f�nster.show();
		
		
		System.out.print(hm.getHighscoreString());
		
//System.exit(0);

	}
}
