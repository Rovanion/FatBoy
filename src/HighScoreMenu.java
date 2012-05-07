import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HighScoreMenu
{
    public static void inputName()
    {
        //
        // Skapa ett fönster.
        //
        JFrame fönster = new JFrame();
        fönster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fönster.setSize(300, 400);  // Bredd och höjd.
        fönster.setLocation(100, 100);  // x och y.
        fönster.setTitle("Fönster med komponenter");

        //
        // Skapa knappen och textrutan.
        //
        JButton knapp = new JButton("Klicka mej");
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
        textruta.setBounds(20, 20, 250, 250);
        knapp.setBounds(20, 290, 250, 40);
        
        //
        // Öppna fönstret.
        //
        fönster.show();
    }
}
