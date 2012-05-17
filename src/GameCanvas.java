import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameCanvas extends Canvas implements Runnable {
	// FIELDS
	private boolean running;
	private Image backgroundImage;
	private Image gameOverImage;
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
	private int time = 0;
	private int timeSinceLastFlyingObject = 0;
	private int flyingObjectsSent = 0;
	private int timeBetweenFlyingObjects = 60;
	private Random rand = new Random();
	private int chanceOfGettingABurger = 750;
	private FatMeter fm = new FatMeter();

	// CONSTRUCTOR
	public GameCanvas() {
		// Fullscreen Settings
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(0,0,screenSize.width, screenSize.height);
		setBounds(0,0,Settings.dim.width, Settings.dim.height);

		// Choosable dimension settings
		setPreferredSize(Settings.dim);

		addKeyListener(controller);

		initializeImages();
		music = new PlayWave("src/GameTrack02.wav");
		title = new TitleScreen(titleScreen);

		musicPlaying = Settings.musicPlaying;//??? Lokal variabel tänkt för att styra vilken låt som spelas.
		musicPaused = Settings.musicPaused;
		titleMusic = Settings.titleMusic;

		hero = new FatBoyHero(fatBoyImage);
		disk = new Disk();
		musicPlaying=false;
	}

	/**
	 * InitializeImages fetches the images at start up.
	 */
	private void initializeImages() {
		try {
			backgroundImage = ImageIO.read(getClass().getResource(
					"BackgroundFit.jpg"));
			gameOverImage = ImageIO.read(getClass().getResource(
					"GameOverScreen.png"));
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

	private void playMusic()
	{
		/*if (titleMusic) {
			music.start();
			titleMusic = false;
		}*/
		//if (!Settings.showTitleScreen) {
			music.mute();
			music = new PlayWave("src/GameTrack04.wav");
			music.start();
			musicPlaying = true;
		//}

	}

	// METHODS
	/**
	 * Run will handle the rendering and updating of the JFrame and make sure it
	 * does not crash due to heavy thread.
	 */
	public void run() {
		while (running) {
			if(!Settings.showTitleScreen){
				time++;
				timeSinceLastFlyingObject++;
				if (time % 150 == 0 && timeBetweenFlyingObjects > 15)
					timeBetweenFlyingObjects -= 2;
				
				if (timeSinceLastFlyingObject >= timeBetweenFlyingObjects)
					addFlyingObject();
			}
				
			update();
			render();
			
			for (FlyingObject fo : FOtoBeRemoved)
				flyingObects.remove(fo);

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}

			if (controller.keys[KeyEvent.VK_ENTER]) {
				if(!musicPlaying)
				{
					playMusic();
					musicPlaying=true;
				}
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
			if(Settings.liveDebugging)
				System.out.println("FO-timing:" +timeSinceLastFlyingObject + " " + 
						timeBetweenFlyingObjects + " " + chanceOfGettingABurger
						+ " FatLvl:" + hero.getFatLevel() + " Points:" + hero.getFinalScore());
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
			music.run();
		}
	}

	/**
	 * Render handles graphic rendering.
	 */
	private void render() {
		BufferStrategy strategy = getBufferStrategy();
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		if (Settings.showTitleScreen) {
			title.render(g, getWidth(), getHeight());
		}

		else if(!hero.isGameEnded()) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

			// Sets the FatMeter according to FatBoy's FatPoints.
			fm.render(g, hero.getFatLevel());
			//Render objects
			disk.render(g);
			hero.render(g);

			for (FlyingObject fo : flyingObects)
				fo.render(g);
		}
		else
		{
			g.drawImage(gameOverImage, 0, 0, getWidth(), getHeight(), null);
		}
		strategy.show();
	}

	/**
	 * Update
	 */
	private void update() {
		if (controller.keys[KeyEvent.VK_ESCAPE]) {
				System.exit(0);
		}
		if (!Settings.showTitleScreen) {
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
		public void addFlyingObject(){
			FlyingObject derp = null;
					if (rand.nextInt(1000) < chanceOfGettingABurger) {
						switch (rand.nextInt(3)) {
						case 0:	derp = new FlyingObject(friesImage, 0.3, 0.2, 50);
								break;
						case 1: derp = new FlyingObject(hamburgerImage, 0.6, 0.2, 50);
								break;
						case 2: derp = new FlyingObject(iceCreamImage, 0.1, 0.1, 50);
						}
					} else{
						switch (rand.nextInt(2)){
						case 0: derp = new FlyingObject(appleImage, 0.9, -0.15, 80);
								break;
						case 1: derp = new FlyingObject(carrotImage, 0.75, -0.2, 80);
								break;
						}
					}
					timeSinceLastFlyingObject = 0;
					
					if(chanceOfGettingABurger>75)//10% chance to get more fat points-->impossibleness
						chanceOfGettingABurger--;
					
					flyingObjectsSent++;
					flyingObects.add(derp);
	}
}
