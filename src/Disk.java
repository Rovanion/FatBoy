import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Disk
{
	private Image diskImage;
	private double x=0.1;
	private double y=800;
	
	
	public Disk(GameCanvas parent)
	{	
		try {
			diskImage = ImageIO.read(getClass().getResource("testbild.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		diskImage = parent.makeColorTransparent((BufferedImage) diskImage);
	}
	
	
	
	public void render (Graphics2D g) {
		
		//g.translate(x, y);
		g.drawImage(diskImage, (int)x, (int)y, diskImage.getWidth(null), diskImage.getHeight(null), null);
		//g.translate(-x,-y);
	}
	
}
