import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameCanvas extends Canvas implements Runnable {
	// FIELDS
	private boolean running;
	private Image backgroundImage;
	private Image fatBoyImage;
	private Image titleScreen;
	private Image carrotImage;
	private Disk disk;
	FlyingObject fo;
	private FatBoyHero hero;
	private Controller controller = new Controller();
	private TitleScreen title;
	private PlayWave music;
	private boolean musicPlaying, titleMusic, musicPaused;



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
		fo = new FlyingObject(carrotImage, 0.8, 50, 50);
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
			if (!musicPlaying) {
				playMusic();
			}
			update();
			render();

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
			if (hero.getFatLevel() > 0.8 && hero.getFatLevel() < 1.8) {
				fatMeterLevel = (int) (-0.3 * Settings.height() * (hero.getFatLevel() - 0.7));
			}
			g.setColor(Color.YELLOW);
			g.fillRect((int) (0.05 * Settings.width()),
					(int) (0.98 * Settings.height()), 40, fatMeterLevel);

			disk.render(g);
			hero.render(g);
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
			hero.update(controller);
			fo.update();
		} else {
			title.update(controller);
		}
		
	}
}
