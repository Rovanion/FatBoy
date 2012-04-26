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
	private double fatLevel = 1.6; // Max:1.7 , Min:0.82 (Med dessa slipper
									// �nden av repet synas vid n�got av
									// tillf�llen fet-smal.
	private double counterLimit = 0.05;
	private double groundLevel = 0.8;
	private int jumpKeyPresses = 0;
	private boolean jumping = false;

	// CONSTRUCTOR
	public FatBoyHero(Image image) {
		this.image = image;
	}

	// METHODS
	/**
	 * Update keeps track on FatBoy's movement corresponding to the controller.
	 * 
	 * @param controller
	 */
	public void update(Controller controller) {
		// Movement X-axis
		dx = dx * (1 - 0.1 * fatLevel);

		dx += 0.00005 * (1.5 - x) * (0.8 * fatLevel); // Elastic rope:
														// ForcePull=k*xChange*percentageOfMass
		if (fatLevel < 0.82) {
			dx += 0.0003; // If too thin you get dragged out.
		} 
		else if (fatLevel > 1.5) {
			dx = dx / 4;
		

		dx += 0.00005*(1.5-x)*(0.8*fatLevel); //Elastic rope: ForcePull=k*xChange*percentageOfMass
		if(fatLevel<0.82)
		{
			dx+=0.0003; //If too thin you get dragged out.
		}
		else if(fatLevel>1.5)
		{
			dx=dx/4;
		}
		// dx += 0.004 - (0.004 * fatLevel);

		if (controller.keys[KeyEvent.VK_RIGHT] || controller.keys[KeyEvent.VK_D]){
			dx += 0.0003 * fatLevel;
		}
		if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A]){
			dx -= 0.00015 * fatLevel;
			//Moving against the rope's force burns fat
			fatLevel -= 0.0003;
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
				fatLevel -= 0.02;
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
		} else if (x >= 0.9) {
			main.endGame();
		}
	}

	/**
	 * JUMP Changes the y-ccordinates.
	 */
	private void jump() {
		jumpKeyPresses++;
		if (jumpKeyPresses < 12) {
			if (jumpKeyPresses == 1)
				dy = -0.02 * (1 - 0.35 * fatLevel);
			dy -= 0.003 * (1 - 0.35 * fatLevel);
		}
	}


	public void render(Graphics2D g) {

		int absoluteX = (int) (x * GameCanvas.width());
		int absoluteY = (int) (y * GameCanvas.height());
		
		//Sets the FatMeter
		g.setColor(Color.YELLOW);
		g.fillRect((int) (0.05 * GameCanvas.width()),
				(int) (0.98 * GameCanvas.height()), 40, (int)(-200*(fatLevel-0.8))); //-200 = max , 0 = min

		g.translate(absoluteX, absoluteY);

		//Set the rope not to show when size differs.
		int ropeXcoord;
		if(fatLevel>1.0)
		{
			ropeXcoord = (int) (0.15 * GameCanvas.width());
		}
		else
		{
			ropeXcoord = (int) (0.1 * GameCanvas.width());
		}
		g.setColor(Color.orange);
		g.fillRect(ropeXcoord,
				(int) (0.20 * GameCanvas.height()), 2000, 6);

		g.drawImage(image, 0, 0, (int) (GameCanvas.width() * 0.1 * fatLevel), 
						(int) (GameCanvas.height() * 0.15), null);

		// Reset the coordinate system to the top left of the screen
		g.translate(-absoluteX, -absoluteY);
	}
}
