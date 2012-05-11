import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Disk {
	private Image diskImage;
	private double x = -0.067;
	private double y = 0.51;


	public Disk() {
		try {
			diskImage = ImageIO.read(getClass().getResource("Counter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics2D g) {		
		int absoluteX = (int) (x * Settings.width());
		int absoluteY = (int) (y * Settings.height());

		//g.drawImage(diskImage, absoluteX, absoluteY, (int)(Settings.width() * 0.23),
			//	(int) (Settings.height() * 0.6), null);
	}

}
