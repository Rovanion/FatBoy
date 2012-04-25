import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero {
	private Image image;
	private double x = 0.5;
	private double y = 0.8;
	private double dy = 0;
	private double tjockhet = 0.7;
	private int hoppat  = 0;

	public FatBoyHero(Image image) {
		this.image = image;
	}

	public void update(Controller controller) {
		//Förflyttning i X-led
		if (controller.keys[KeyEvent.VK_RIGHT]||controller.keys[KeyEvent.VK_D])
			x += 0.005*tjockhet;
		if (controller.keys[KeyEvent.VK_LEFT]||controller.keys[KeyEvent.VK_A])
			x -= 0.005*tjockhet;
		x += 0.004 - (0.004 * tjockhet);

		if(x < 0.1)
			x = 0.1;
		else if (x >= 1)
			main.endGame();
		
		
		//Förflyttning i Y-led
		if(controller.keys[KeyEvent.VK_UP]||controller.keys[KeyEvent.VK_W]){
			hoppat++;
			if(hoppat < 10){
				if(hoppat == 1)
					dy = -0.05;
				dy -= 0.01;
			}
		}
		
		y += dy;
		
		if(hoppat > 0){
			dy += 0.005;
			if(y > 0.8){
				dy = 0;
				y = 0.8;
				hoppat = 0;
			}
		}
		
		
	}

	public void render(Graphics2D g) {


		int absoluteX = (int) (x * GameCanvas.width());
		int absoluteY = (int) (y * GameCanvas.height());
		
		//Set the coordinate system relative to the fat boy
		g.translate(absoluteX, absoluteY);
		
		g.drawImage(image, 0, 0, (int) (400 * tjockhet),
				(int) (400 * tjockhet), null);
		
		g.setColor(Color.ORANGE);
		g.fillRect((int)(0.09*GameCanvas.height()), (int)(0.08*GameCanvas.width()), 2000, 6);
		
		//Reset the coordinate system to the top left of the screen
		g.translate(-absoluteX, -absoluteY);
	}
}
