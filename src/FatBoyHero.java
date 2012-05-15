import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero {
	// FIELDS
	private Image image;
	private double x = 0.3;
	private double y = Settings.groundLevel;
	private double dx = 0;
	private double dy = 0;
	private double fatLevel = 1.6;
	private double maxFat = 2.0;
	private double counterLimit = 0.14;
	private int jumpKeyPresses = 0;
	private boolean jumping = false;
	private Rope theRope = new Rope();
	private LeftFoot leftFoot = new LeftFoot();
	private RightFoot rightFoot = new RightFoot();
	private boolean gameEnded;
	private int finalScore;

	public boolean isGameEnded() {
		return gameEnded;
	}

	// CONSTRUCTOR
	public FatBoyHero(Image image) {
		this.image = image;
		gameEnded = false;
	}

	// METHODS
	public double getFatLevel() {
		return fatLevel;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int newScore) {
		int oldScore = finalScore;
		finalScore = oldScore + newScore;
	}

	public void setFatLevel(double newFat) {
		if (fatLevel < maxFat)
			fatLevel += newFat;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
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
		dx += 0.0003 * (1.75 - x) * (1 / (fatLevel)); // Elastic rope:

		leftFoot.update(controller);
		rightFoot.update(controller);
		
		if (controller.keys[KeyEvent.VK_RIGHT]
				|| controller.keys[KeyEvent.VK_D]) {
			dx += 0.0008;
		}
		if (controller.keys[KeyEvent.VK_LEFT] || controller.keys[KeyEvent.VK_A]) {
			dx -= 0.0008;
			// Moving against the rope's force burns fat
			fatLevel -= 0.001 * (1.75 - x);
		}

		x += dx;

		limitScreenMovement();

		// Movement Y-axis
		if (controller.keys[KeyEvent.VK_UP] || controller.keys[KeyEvent.VK_W]) {
			jump();
		}
		/*
		 * This prevents double-jumping. By setting the number of jump-key
		 * presses over the allowed value for adding more speed, no more force
		 * will be added if the key is pressed again.
		 */
		else if (jumping) {
			jumpKeyPresses = 100;
			jumping = false;
		}

		y += dy;

		// If the player is jumping, add the gravitational acceleration to dy.
		if (jumpKeyPresses > 0) {
			dy += 0.0009;

			// If the player just hit the ground, reset speed and position.
			if (y > Settings.groundLevel) {
				dy = 0;
				y = Settings.groundLevel;
				jumpKeyPresses = 0;
				
				leftFoot.setJump(false);
				rightFoot.setJump(false);
				
				// Jumping burns fat.
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
		} else if (x >= 1.06) {
			if (!gameEnded) {
				gameEnded = true;
				main.endGame(finalScore);
			}

		}
	}

	/**
	 * JUMP Changes the y-coordinates.
	 */
	private void jump() {
		jumping = true;
		jumpKeyPresses++;
		if (jumpKeyPresses < 13) {
			if (jumpKeyPresses == 1)
				dy = -0.02 * (1.75 - fatLevel * 0.75);
			dy -= 0.001 * (1.75 - fatLevel * 0.75);
		}
	}

	public void render(Graphics2D g) {
		int absoluteX = (int) (x * Settings.width());
		int absoluteY = (int) (y * Settings.height());
		g.translate(absoluteX, absoluteY);
		rightFoot.render(g, x, y, fatLevel);
		g.translate(-(int) (Settings.width() * 0.075 * fatLevel) / 2,
				-(int) (Settings.height() * 0.125) / 2);
		theRope.render(g, fatLevel);

		g.drawImage(image, 0, 0, (int) (Settings.width() * 0.075 * fatLevel),
				(int) (Settings.height() * 0.125), null);

		// Reset the coordinate system to the top left of the screen
		g.translate((int) (Settings.width() * 0.075 * fatLevel) / 2,
				(int) (Settings.height() * 0.125) / 2);
		leftFoot.render(g, x, y, fatLevel);
		g.translate(-absoluteX, -absoluteY);
	}
}
