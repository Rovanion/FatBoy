import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HighScoreMenu
{
    public static void inputName()
    {
        //
        // Skapa ett f�nster.
        //
        JFrame f�nster = new JFrame();
        f�nster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f�nster.setSize(300, 400);  // Bredd och h�jd.
        f�nster.setLocation(100, 100);  // x och y.
        f�nster.setTitle("F�nster med komponenter");

        //
        // Skapa knappen och textrutan.
        //
        JButton knapp = new JButton("Klicka mej");
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
        textruta.setBounds(20, 20, 250, 250);
        knapp.setBounds(20, 290, 250, 40);
        
        //
        // �ppna f�nstret.
        //
        f�nster.show();
    }
}
