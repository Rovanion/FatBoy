import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;


public class LeftFoot {
	//FIELDS
	private Image footImage;
	private double boyPosx, boyPosy;
	private double dx=0, dy=0;
	private double maxX=1.5;
	private double centerX=0;
	private double minX=-1.5;
	private double maxY;
	private double centerY=(int)(Settings.height()*0.03);
	private double minY;
	private double jumpY;
	private double fatLevel;
	private boolean step=false;
	private boolean jump=false;
	private int footSizeX;
	private int footSizeY;
	private double stepSize = Settings.width()*0.01;
	private double staggerSize = Settings.height()*0.01;
	
	//CONSTRUCTOR
	public LeftFoot()
	{
		dx=centerX;
		dy=centerY;
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
		footSizeX=(int)(Settings.width()*0.05*(fatLevel/2));
		footSizeY=(int)(Settings.height()*0.04);
		if (controller.keys[KeyEvent.VK_UP] || controller.keys[KeyEvent.VK_W])
		{
			jump=true;
			footSizeX=(int)(Settings.width()*0.05*(fatLevel/2)/2);
			footSizeY=(int)(Settings.height()*0.09);
		}
		else if (controller.keys[KeyEvent.VK_RIGHT]|| controller.keys[KeyEvent.VK_D]) 
		{
			if(!jump)
				moveFoot();
		}
		else if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A])
		{
			if(!jump)
				moveFoot();
		}
		
		else
		{
			if(!jump)
				stagger();
		}
	}
	
	public void setJump(boolean jump) 
	{
		dy=centerY;
		dx=centerX;
		this.jump = jump;
	}

	private void moveFoot()
	{
		
		dy=centerY;
		if(step)
		{
			dx+=stepSize;
			step=false;
		}
		else
		{
			dx-=stepSize;
			step=true;
		}
	}
	
	private void stagger()
	{
		dy=centerY;
		dx=centerX;
		if(step)
		{
			dx+=stepSize;
			dy+=staggerSize;
			step=false;
		}
		else
		{
			dx-=stepSize;
			dy-=staggerSize;
			step=true;
		}
	}
	
	public void render(Graphics2D g, double posX, double posY, double fat)
	{
		fatLevel = fat;
		boyPosx = posX;
		boyPosy = posY;
		g.translate(boyPosx,boyPosy);
		g.drawImage(footImage, (int)(dx), (int)(dy), footSizeX , footSizeY , null);
		g.translate(-boyPosx,-boyPosy);
		
	}

}
