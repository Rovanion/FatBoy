import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Disk {
	private Image diskImage;
	private double x = -0.1;
	private double y = 0.75;

	public Disk() {
		try {
			diskImage = ImageIO.read(getClass().getResource("testbild.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		diskImage = GameCanvas.makeColorTransparent((BufferedImage) diskImage);
	}

	public void render(Graphics2D g) {

		// g.translate(x, y);
		
		int absoluteX = (int) (x * GameCanvas.height());
		int absoluteY = (int) (y * GameCanvas.height());

		g.drawImage(diskImage, absoluteX, absoluteY, diskImage.getWidth(null),
				diskImage.getHeight(null), null);
	}

}
