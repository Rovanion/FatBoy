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
        // Man lögger komponenterna i en
        // "content pane" (innehöllspanel).
        //
        Container innehöll = fönster.getContentPane();

        //
        // Hör talar vi om att vi inte vill ha nögon
        // automatisk layouthantering av komponenterna
        // i fönstret (null = ingenting/tomt).
        //
        innehöll.setLayout(null);

        //
        // Nu lögger vi in komponenterna och positionerar dem.
        // Koordinatsystemet börjar i övre vönstra hörnet,
        // y vöxer nedöt. Enheten ör bildpunkter.
        //
        innehöll.add(knapp);
        innehöll.add(textruta);

        //
        // Argumenten till setBounds() ör x, y, bredd, höjd.
        //
        textruta.setBounds(20, 20, 250, 250);
        knapp.setBounds(20, 290, 250, 40);
        
        //
        // öppna fönstret.
        //
        fönster.show();
    }
}
