import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero {
	private Image image;
	private double x = 0.5;
	private double y = 0.7;
	private double dx = 0;
	private double dy = 0;
	private double tjockhet = 1;
	private int hoppat = 0;

	public FatBoyHero(Image image) {
		this.image = image;
	}

	public void update(Controller controller) {

		// OBS! Endast för testning!
		tjockhet -= 0.0005;
		// Ska ersättas med spelmekanik senare!

		// Förflyttning i X-led
		dx = dx * (1 - 0.1 * tjockhet);

		if (controller.keys[KeyEvent.VK_RIGHT]
				|| controller.keys[KeyEvent.VK_D])
			dx += 0.005 * tjockhet;
		if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A])
			dx -= 0.005 * tjockhet;
		dx += 0.004 - (0.004 * tjockhet);

		x += dx;

		/*
		 * Makes the player stop if they reach the counter at the left side of
		 * the map and terminates the game if they go outside of the screen to
		 * the right.
		 */
		if (x < 0.1) {
			x = 0.1;
			dx = 0;
		} else if (x >= 1)
			main.endGame();

		// Förflyttning i Y-led
		if (controller.keys[KeyEvent.VK_UP] || controller.keys[KeyEvent.VK_W]) {
			hoppat++;
			if (hoppat < 12) {
				if (hoppat == 1)
					dy = -0.06 * (1 - 0.65 * tjockhet);
				dy -= 0.01 * (1 - 0.65 * tjockhet);
			}
		}

		y += dy;

		/*
		 * If the player has jumped; Once he reaches the ground level again
		 * reset his speed and y-position.
		 */
		if (hoppat > 0) {
			dy += 0.004;
			if (y > 0.7) {
				dy = 0;
				y = 0.7;
				hoppat = 0;
			}
		}

	}

	public void render(Graphics2D g) {

		int absoluteX = (int) (x * GameCanvas.width());
		int absoluteY = (int) (y * GameCanvas.height());

		// Set the coordinate system relative to the fat boy
		g.translate(absoluteX, absoluteY);

		g.drawImage(image, 0, 0, (int) (300 * tjockhet),
				(int) (300 * (1 - 0.2 * tjockhet)), null);

		g.setColor(Color.ORANGE);
		g.fillRect((int) (0.25 * GameCanvas.height()),
				(int) (0.09 * GameCanvas.width()), 2000, 6);

		// Reset the coordinate system to the top left of the screen
		g.translate(-absoluteX, -absoluteY);
	}
}
