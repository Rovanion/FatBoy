import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero {
	private Image image;
	private double x = 0.5;
	private double y = 0.8;
	private double tjockhet = 0.5;

	public FatBoyHero(Image image) {
		this.image = image;
	}

	public void update(Controller controller) {
		if (controller.keys[KeyEvent.VK_D])
			x += 0.005;
		if (controller.keys[KeyEvent.VK_A])
			x -= 0.005;

		x += 0.005 - (0.005 * tjockhet);

		
		if(x < 0.1)
			x = 0.1;
		else if (x >= 1.7)
			main.endGame();
		
	}

	public void render(Graphics2D g) {
		// g.translate(x, y);

		int absoluteX = (int) (x * GameCanvas.height());
		int absoluteY = (int) (y * GameCanvas.height());

		g.drawImage(image, absoluteX, absoluteY, (int) (400 * tjockhet),
				(int) (400 * tjockhet), null);
		// g.translate(-x,-y);
	}
}
