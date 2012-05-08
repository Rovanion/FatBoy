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
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 400);  // Bredd och höjd.
        window.setLocation(100, 100);  // x och y.
        window.setTitle("Fönster med komponenter");

        //
        // Skapa knappen och textrutan.
        //
        JButton knapp = new JButton("Klicka mej");
        JTextArea textruta = new JTextArea();

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
        content.add(textruta);

        //
        // Argumenten till setBounds() ör x, y, bredd, höjd.
        //
        textruta.setBounds(20, 20, 250, 250);
        knapp.setBounds(20, 290, 250, 40);
        
        //
        // öppna fönstret.
        //
        window.show();
    }
}
