import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero 
{
	//FIELDS
	private Image image;
	private double x = 0.5;
	private double y = 0.7;
	private double dx = 0;
	private double dy = 0;
	private double fatLevel = 1; //Max:1.7 , Min:0.82 (Med dessa slipper änden av repet synas vid något av tillfällen fet-smal.
	private double counterLimit = 0.05;
	private double groundLevel = 0.7;
	private int jump = 0;

	//CONSTRUCTOR
	public FatBoyHero(Image image) {
		this.image = image;
	}

	//METHODS
	/**
	 * Update keeps track on FatBoy's movement corresponding to the controller.
	 * @param controller
	 */
	public void update(Controller controller) 
	{
		// Movement X-axis
		dx = dx * (1 - 0.1 * fatLevel);

		if (controller.keys[KeyEvent.VK_RIGHT]|| controller.keys[KeyEvent.VK_D])
		{
			dx += 0.0003 * fatLevel;
		}
		if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A])
		{
			dx -= 0.00015 * fatLevel;
			fatLevel -= 0.0003;
		}
		
		//dx += 0.004 - (0.004 * fatLevel);
		x += dx;
		
		limitScreenMovement();

		// Movement Y-axis
		if (controller.keys[KeyEvent.VK_UP] || controller.keys[KeyEvent.VK_W]) 
		{
			jump();
		}
		
		y += dy;

		resetGroundLevel();	
	}
	
	/**SCREEN LIMITATION
	 * Makes the player stop if they reach the counter at the left side of
	 * the map and terminates the game if they go outside of the screen to
	 * the right.
	 */
	private void limitScreenMovement()
	{
		if (x < counterLimit)
		{
			x = counterLimit;
			dx = 0;
		} 
		else if (x >= 0.9)
		{
			main.endGame();
		}
	}
	
	private void jump()
	{
		jump++;
		if (jump < 12) {
			if (jump == 1)
				dy = -0.004 * (1 - 0.65 * fatLevel);
			dy -= 0.007 * (1 - 0.65 * fatLevel);
		}
	}
	
	/**RESET GROUND LEVEL
	 * If the player has jumped; Once he reaches the ground level again
	 * reset his speed and y-position.
	 */
	private void resetGroundLevel()
	{
		if (jump > 0) 
		{
			dy += 0.0009;
			if (y > groundLevel) 
			{
				dy = 0;
				y = groundLevel;
				jump = 0;
			}
		}
	}

	public void render(Graphics2D g) {

		int absoluteX = (int) (x * GameCanvas.width());
		int absoluteY = (int) (y * GameCanvas.height());

		// Set the coordinate system relative to the fat boy
		g.translate(absoluteX, absoluteY);
		
		g.setColor(Color.ORANGE);
		g.fillRect((int) (0.14 * GameCanvas.width()),
				(int) (0.20 * GameCanvas.height()), 2000, 6);

		g.drawImage(image, 0, 0, (int) (300 * fatLevel),
				(int) (300 /*(1 - 0.2 * fatLevel)*/), null);

		

		// Reset the coordinate system to the top left of the screen
		g.translate(-absoluteX, -absoluteY);
	}
}
