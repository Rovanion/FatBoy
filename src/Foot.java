import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Foot {
	//FIELDS
	private Image footImage;
	private double boyPosx, boyPosy;
	
	//CONSTRUCTOR
	public Foot()
	{
		try {
			footImage = ImageIO.read(getClass().getResource("Foot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//PROPERTIES
	
	//METHODS
	public void update(Controller controller)
	{
		if (controller.keys[KeyEvent.VK_RIGHT]|| controller.keys[KeyEvent.VK_D]) 
		{
			
		}
		if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A])
		{

		}
		if (controller.keys[KeyEvent.VK_UP] || controller.keys[KeyEvent.VK_W])
		{
			
		}
	}
	
	public void render(Graphics2D g, double posX, double posY)
	{
		boyPosx = posX;
		boyPosy = posY;
		g.translate(boyPosx,boyPosy);
		g.drawImage(footImage, 0, (int)(Settings.height()*0.03), (int)(Settings.width()*0.05), (int)(Settings.height()*0.04), null);
		g.translate(-boyPosx,-boyPosy);
		
	}

}
