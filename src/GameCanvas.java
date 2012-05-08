import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class GameCanvas extends Canvas implements Runnable {
	// FIELDS
	private boolean running;
	private Image backgroundImage;
	private Image fatBoyImage;
	private Image titleScreen;
	private Image carrotImage;
	private Image hamburgerImage;
	private Image iceCreamImage;
	private Image appleImage;
	private Image friesImage;
	private Disk disk;
	private FatBoyHero hero;
	private TitleScreen title;
	private PlayWave music;
	private boolean musicPlaying, titleMusic, musicPaused;
	private Controller controller = new Controller();
	private static List<FlyingObject> flyingObects = new LinkedList<FlyingObject>();
	private static List<FlyingObject> FOtoBeRemoved = new LinkedList<FlyingObject>();
	private int timeSinceLastFlyingObject = 0;
	private int flyingObjectsSent = 0;
	private int timeBetweenFlyingObjects = 70;
	private Random rand = new Random();
	private int chanceOfGettingABurger = 750;

	// CONSTRUCTOR
	public GameCanvas() {
		// Fullscreen Settings
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(0,0,screenSize.width, screenSize.height);

		// Choosable dimension settings
		setPreferredSize(Settings.dim);

		addKeyListener(controller);

		initializeImages();
		music = new PlayWave("src/GameTrack02.wav");
		title = new TitleScreen(titleScreen);

		musicPlaying = Settings.musicPlaying;
		musicPaused = Settings.musicPaused;
		titleMusic = Settings.titleMusic;

		hero = new FatBoyHero(fatBoyImage);
		disk = new Disk();
	}

	/**
	 * InitializeImages fetches the images at start up.
	 */
	private void initializeImages() {
		try {
			backgroundImage = ImageIO.read(getClass().getResource(
					"BackgroundFit.jpg"));
			titleScreen = ImageIO.read(getClass().getResource(
					"FatBoyTitlePixelated.png"));
			fatBoyImage = ImageIO.read(getClass().getResource("FatBoy.png"));
			carrotImage = ImageIO.read(getClass().getResource("carrot.png"));
			hamburgerImage = ImageIO.read(getClass().getResource(
					"Hamburger.png"));
			appleImage = ImageIO.read(getClass().getResource("Apple.png"));
			friesImage = ImageIO.read(getClass().getResource("pommes.png"));
			iceCreamImage = ImageIO
					.read(getClass().getResource("IceCream.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void playMusic() {
		if (!titleMusic) {
			music.start();
			titleMusic = true;
		}
		if (!title.isShowTitleScreen()) {
			music.stop();
			music = new PlayWave("src/GameTrack04.wav");
			music.start();
			musicPlaying = true;
		}

	}

	// METHODS
	/**
	 * Run will handle the rendering and updating of the JFrame and make sure it
	 * does not crash due to heavy thread.
	 */
	public void run() {
		while (running) {
			timeSinceLastFlyingObject++;
			if (!musicPlaying)
				playMusic();

			if (timeSinceLastFlyingObject == timeBetweenFlyingObjects) {
				FlyingObject derp = null;
				if (rand.nextInt(1000) < chanceOfGettingABurger) {
					switch (rand.nextInt(3)) {
					case 0:	derp = new FlyingObject(friesImage, 0.3, 0.25, 50);
							break;
					case 1: derp = new FlyingObject(hamburgerImage, 0.6, 0.25, 50);
							break;
					case 2: derp = new FlyingObject(iceCreamImage, 0.1, 0.1, 50);
					}
				} else{
					switch (rand.nextInt(2)){
					case 0: derp = new FlyingObject(appleImage, 0.9, -0.1, 80);
							break;
					case 1: derp = new FlyingObject(carrotImage, 0.75, -0.2, 80);
							break;
					}
				}
					
				flyingObects.add(derp);

				chanceOfGettingABurger--;
				flyingObjectsSent++;
				timeSinceLastFlyingObject = 0;
				if (flyingObjectsSent % 5 == 0 && timeBetweenFlyingObjects > 20)
					timeBetweenFlyingObjects -= 5;
			}
			update();
			render();

			for (FlyingObject fo : FOtoBeRemoved)
				flyingObects.remove(fo);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}

			if (controller.keys[KeyEvent.VK_M]) {
				if (musicPaused) {
					music.unmute();
					musicPaused = false;
				} else {
					music.mute();
					musicPaused = true;
				}
			}

		}
	}

	/**
	 * Start creates a thread and makes the frame run.
	 */
	public void start() {
		if (!running) {
			Thread t = new Thread(this);
			createBufferStrategy(3);
			running = true;
			t.start();
		}
	}

	/**
	 * Render handles graphic rendering.
	 */
	private void render() {
		BufferStrategy strategy = getBufferStrategy();
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		if (title.isShowTitleScreen()) {
			title.render(g, getWidth(), getHeight());
		}

		else {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

			// Sets the FatMeter according to FatBoy's FatPoints.
			int fatMeterLevel = 0;
			if (hero.getFatLevel() < 1.8) {
				fatMeterLevel = (int) (-0.15 * Settings.height() * (hero
						.getFatLevel()));
			} else if (hero.getFatLevel() < 0.6) {
				fatMeterLevel = 0;
			} else {
				fatMeterLevel = (int) (-0.3 * Settings.height() * 1.3);
			}
			g.setColor(Color.YELLOW);
			g.fillRect((int) (0.05 * Settings.width()),
					(int) (0.98 * Settings.height()), 40, fatMeterLevel);

			//Render objects
			disk.render(g);
			hero.render(g);

			for (FlyingObject fo : flyingObects)
				fo.render(g);
		}
		strategy.show();
	}

	/**
	 * Update
	 */
	private void update() {
		if (controller.keys[KeyEvent.VK_ESCAPE]) {
			main.endGame();
		}
		if (!title.isShowTitleScreen()) {
			// Update all movable objects positions
			hero.update(controller);
			for (FlyingObject fo : flyingObects)
				fo.update();

			// Check for collisions between FatBoy and food
			double x = hero.getX();
			double y = hero.getY();
			for (FlyingObject fo : flyingObects)
				if (fo.checkForCollision(x, y)) {
					hero.setFatLevel(fo.fatpoints());
					hero.setFinalScore(fo.highscore());
					removeFlyingObject(fo);
				}
		} else {
			title.update(controller);
		}

	}

	public static void removeFlyingObject(FlyingObject fo) {

		FOtoBeRemoved.add(fo);
	}
}
