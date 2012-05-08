import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class HighScoreMenu
{
	Settings set = new Settings();
    public void inputName(int highScore)
    {
        //
        // Skapa ett fönster.
        //
        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize((int)(set.width()*0.2), (int)(set.height()*0.2));  // Bredd och höjd.
        window.setLocation((int)(set.width()*0.5), (int)(set.height()*0.5));  // x och y.
        window.setTitle("Input name:");

        //
        // Skapa knappen och textrutan.
        //
        JButton button = new JButton("Fatastic");
        JTextArea textBox = new JTextArea();
        JLabel label = new JLabel("Your score:" + highScore);

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
        content.add(textBox);

        //
        // Argumenten till setBounds() ör x, y, bredd, höjd.
        //
        textBox.setBounds((int)(set.width()*0.5), (int)(set.height()*0.5),(int)(window.WIDTH*0.98),(int)(window.HEIGHT*0.8));
        button.setBounds(20, 290, 250, 40);
        
        
        //
        // öppna fönstret.
        //
        window.show();
    }
}
