import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FlyingObject {

	private Image image;
	private long mass;
	private long fatpoints;
	private long highscore;
	private double x;
	private double y;

	/**
	 * 
	 * @param image
	 * @param mass
	 * @param fatpoints
	 * @param highscore
	 */
	public FlyingObject(Image image, long mass, long fatpoints, long highscore) {
		this.image = image;
		this.mass = mass;
		this.fatpoints = fatpoints;
		this.highscore = highscore;
		this.x = 0;
		this.y = 0;
	}

	public void update(double velocity, double angle) {
		double g = 9.82;

		y = x
				* Math.tan(angle)
				- ((g * x * x) / (2 * velocity * velocity * Math.cos(angle) * Math
						.cos(angle))) + 0.5;

		this.x = x + ((velocity * Math.cos(angle)) / (1 / 60));

	}

	public void render(Graphics2D g) {
		// g.translate(x, y);
		g.drawImage(image, (int) x, (int) y, image.getWidth(null),
				image.getHeight(null), null);
		// g.translate(-x,-y);
	}
}
