import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Disk {
	private Image diskImage;
	private double x = -0.2;
	private double y = 0.49;

	public Disk() {
		try {
			diskImage = ImageIO.read(getClass().getResource("Counter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics2D g) {

		// g.translate(x, y);
		
		int absoluteX = (int) (x * Settings.height());
		int absoluteY = (int) (y * Settings.height());

		g.drawImage(diskImage, absoluteX, absoluteY, diskImage.getWidth(null),
				diskImage.getHeight(null), null);
	}

}
