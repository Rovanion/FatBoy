import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rope {
	private Image ropeTexture;
	private int momX;
	private int momY;
	private double ropeLength;
	private double angle;
	private double[] positionMatrix = new double[10];
	private int boyPositionX;
	private int boyPositionY;

	public Rope() {
		momX = (int) (Settings.width() + 0.08 * Settings.width());
		momY = (int) (Settings.height() * 0.65);

		try {
			ropeTexture = ImageIO.read(getClass().getResource("Rope.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void render(Graphics2D g, double fatLevel) {
		g.translate((int) (0.06 * Settings.width() * fatLevel),
				(int) (0.06 * Settings.height()));

		AffineTransform currentTransform = g.getTransform();
		currentTransform.getMatrix(positionMatrix);

		boyPositionX = (int) positionMatrix[4];
		boyPositionY = (int) positionMatrix[5];

		ropeLength = Math.sqrt(Math.pow(momX - boyPositionX, 2)
				+ Math.pow(momY - boyPositionY, 2));
		angle = Math.asin((boyPositionY - momY) / ropeLength);

		g.rotate(-angle);
		g.drawImage(ropeTexture, 0, 0, (int) ropeLength, 24, null);
		g.rotate(angle);
		System.out.println(angle);

		g.translate(-(int) (0.065 * Settings.width() * fatLevel),
				-(int) (0.06 * Settings.height()));
	}
}
