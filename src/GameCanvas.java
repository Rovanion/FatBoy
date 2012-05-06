import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameCanvas extends Canvas implements Runnable {
	// FIELDS
	private boolean running;
	private Image backgroundImage;
	private Image fatBoyImage;
	private Image titleScreen;
	private Disk disk;
	private FatBoyHero hero;
	private Controller controller = new Controller();
	//private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static Dimension dim = new Dimension(1280, 720);
	private TitleScreen title;
	private FlyingObject hamburger;
	private Image testbild;

	public static int height() {
		return dim.height;
	}

	public static int width() {
		return dim.width;
	}

	// CONSTRUCTOR
	public GameCanvas() {
		// Fullscreen Settings
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(0,0,screenSize.width, screenSize.height);

		// Choosable dimension settings
		setPreferredSize(dim);

		addKeyListener(controller);

		initializeImages();
		
		title = new TitleScreen(titleScreen);

		hero = new FatBoyHero(fatBoyImage);
		disk = new Disk();
		
		hamburger = new FlyingObject(testbild, 50, 50, 50);
		
	}
	
	/**
	 * InitializeImages fetches the images at start up.
	 */
	private void initializeImages()
	{
		try {
			backgroundImage = ImageIO.read(getClass().getResource(
					"BackgroundFit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fatBoyImage = ImageIO.read(getClass().getResource("FatBoy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			titleScreen = ImageIO.read(getClass().getResource("FatBoyTitlePixelated.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			testbild = ImageIO.read(getClass().getResource("testbild.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// METHODS
	/**
	 * Run will handle the rendering and updating of the JFrame and make sure it
	 * does not crash due to heavy thread.
	 */
	public void run() {
		while (running) {
			update();
			render();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
			
			if(controller.keys[KeyEvent.VK_ESCAPE])
				System.exit(0);
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
	private void render() 
	{
		BufferStrategy strategy = getBufferStrategy();
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
		if(title.isShowTitleScreen())
		{
			title.render(g, getWidth(), getHeight());
		}

		else
		{
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

		//Sets the FatMeter according to FatBoy's FatPoints.
		int fatMeterLevel=0;
		if(hero.getFatLevel()>0.8 && hero.getFatLevel()<1.8)
		{
			fatMeterLevel=(int)(-200*(hero.getFatLevel()-0.8));
		}
		g.setColor(Color.YELLOW);
		g.fillRect((int) (0.05 * GameCanvas.width()),
				(int) (0.98 * GameCanvas.height()), 40, fatMeterLevel); //-200 = max , 0 = min
		
		
		disk.render(g);
		hero.render(g);
		hamburger.render(g);
		}
		
		strategy.show();
	}

	/**
	 * Update
	 */
	private void update()
	{
		if (controller.keys[KeyEvent.VK_ESCAPE]) {
			Main.endGame();
		}
		if(!title.isShowTitleScreen())
		{
			hero.update(controller);
		}
		else
		{
			title.update(controller);
		}
		hamburger.update();
	;
		
	}

	/**
	 * MakeColorTransparent removes the frame which follows imported images.
	 * 
	 * @return
	 */
	public static BufferedImage makeColorTransparent(BufferedImage image) {
		BufferedImage dimg = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dimg.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.drawImage(image, null, 0, 0);
		g2.dispose();
		for (int i = 0; i < dimg.getHeight(); i++) {
			for (int j = 0; j < dimg.getWidth(); j++) {
				if (dimg.getRGB(j, i) == Color.WHITE.getRGB()) {
					dimg.setRGB(j, i, 0x8F1C1C);
				}
			}
		}
		return dimg;
	}

}
