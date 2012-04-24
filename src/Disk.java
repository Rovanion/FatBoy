import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Disk
{
	private Image diskImage;
	private double x=500;
	private double y=500;
	
	
	public Disk()
	{	
		try {
			diskImage = ImageIO.read(getClass().getResource("testbild.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void render (Graphics2D g) {
		
		//g.translate(x, y);
		g.drawImage(diskImage, (int)x, (int)y, diskImage.getWidth(null), diskImage.getHeight(null), null);
		//g.translate(-x,-y);
	}
	
}
