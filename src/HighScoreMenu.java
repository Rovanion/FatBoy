import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;


public class HighScoreMenu extends Applet implements ActionListener
{
//	private Image TitleScreen;
//	private boolean showMenu;
	 
	Panel pa = new Panel();
	
	Button done = new Button ("Done");
	TextField inputfield = new TextField(20); 
	
	
	
//	public HighScoreMenu(Image img)
//	{
//		TitleScreen = img;
//		
//	}
//	
//	public boolean isShowMenu() {
//		return showMenu;
//	}
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	   	
	}
	
//	public void init(){
//		
//		
//		textfield.addActionListener(this);
//		add(textfield);
//		
//		done.addActionListener(this);
//		add( done);
//		
//		
//		
//	}
	


//	public void setShowMenu(boolean showMenu) {
//		this.showMenu = showMenu;
//	}

	
//	
//	public void render(Graphics2D g, int width, int height)
//	{
//		g.drawImage(TitleScreen, 0, 0, width, height, null);
//	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == done){
			String name = inputfield,getText();
			main.shutDown();
 
		}
	}

}
