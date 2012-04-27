import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero {
	// FIELDS
	private Image image;
	private double x = 0.3;
	private double y = 0.8;
	private double dx = 0;
	private double dy = 0;
	private double fatLevel = 1.6;
	


	private double counterLimit = 0.09;
	private double groundLevel = 0.8;
	private int jumpKeyPresses = 0;
	private boolean jumping = false;

	// CONSTRUCTOR
	public FatBoyHero(Image image) {
		this.image = image;
	}


	//METHODS
	public double getFatLevel()
	{
		return fatLevel;
	}
	
	/**
	 * Update keeps track on FatBoy's movement corresponding to the controller.
	 * 
	 * @param controller
	 */
	public void update(Controller controller) {
		// Friction removing speed depending on weight.
		dx = dx - dx * 0.04;

		// Force = k * Distance From Equilibrium * Mass
		dx += 0.00016 * (1.75 - x) * (1 / (fatLevel)); // Elastic rope:
											  

		if (controller.keys[KeyEvent.VK_RIGHT] || controller.keys[KeyEvent.VK_D]){
			dx += 0.0004;
		}
		if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A]){
			dx -= 0.0004;
			//Moving against the rope's force burns fat
			fatLevel -= 0.003;
		}

		x += dx;

		limitScreenMovement();
		

		// Movement Y-axis
		if (controller.keys[KeyEvent.VK_UP] || controller.keys[KeyEvent.VK_W]) {
			jump();
		}
		/*
		 * This prevents double-jumping.
		 * By setting the number of jump-key presses over the allowed value 
		 * for adding more speed, no more force will be added if the key is 
		 * pressed again.
		 */
		else if(jumping){
			jumpKeyPresses = 100;
			jumping = false;
		}

		y += dy;
		
		//If the player is jumping, add the gravitational acceleration to dy.
		if (jumpKeyPresses > 0) {
			dy += 0.0009;
			
			//If the player just hit the ground, reset speed and position.
			if (y > groundLevel) {
				dy = 0;
				y = groundLevel;
				jumpKeyPresses = 0;
				//Jumping burns fat.
				fatLevel -= 0.05;	
			}
		}
	}

	/**
	 * SCREEN LIMITATION Makes the player stop if they reach the counter at the
	 * left side of the map and terminates the game if they go outside of the
	 * screen to the right.
	 */
	private void limitScreenMovement() {
		if (x < counterLimit) {
			x = counterLimit;
			dx = 0;
		} else if (x >= 1) {
			main.endGame();
		}
	}

	/**
	 * JUMP Changes the y-coordinates.
	 */
	private void jump() {
		jumping = true;
		jumpKeyPresses++;
		if (jumpKeyPresses < 12) {
			if (jumpKeyPresses == 1)
				dy = -0.02 * (1 / (0.3 + fatLevel));
			dy -= 0.001 * (1 / (0.3 + fatLevel));
		}
	}


	public void render(Graphics2D g) {

		int absoluteX = (int) (x * GameCanvas.width());
		int absoluteY = (int) (y * GameCanvas.height());

		g.translate(absoluteX, absoluteY);


		g.setColor(Color.orange);
		g.fillRect((int) (0.03 * GameCanvas.width()),
				(int) (0.08 * GameCanvas.height()), 2000, 6);

		g.drawImage(image, 0, 0, (int) (GameCanvas.width() * 0.1 * fatLevel), 
						(int) (GameCanvas.height() * 0.15), null);

		// Reset the coordinate system to the top left of the screen
		g.translate(-absoluteX, -absoluteY);
	}
}
