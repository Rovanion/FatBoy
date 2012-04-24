import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameCanvas extends Canvas implements Runnable
{
	//FIELDS
	private boolean running;
	private Image backgroundImage;
	private Image fatBoyImage;
	private Disk disk;
	private FatBoyHero hero;
	private Controller controller = new Controller();

	//CONSTRUCTOR
	public GameCanvas()
	{
		//Fullscreen Settings
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width, screenSize.height);
		
		//Choosable dimension settings
	    /*Dimension dim = new Dimension(800,600);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);*/
		
		addKeyListener(controller);
		
		try {
			backgroundImage = ImageIO.read(getClass().getResource("BackgroundFit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fatBoyImage = ImageIO.read(getClass().getResource("FatBoy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fatBoyImage = makeColorTransparent((BufferedImage) fatBoyImage);
		hero = new FatBoyHero(fatBoyImage, 600, 590);
		
		disk = new Disk();
	}
	
	//METHODS
	/**
	 * Run will handle the rendering and updating of the JFrame and make sure it does not crash due to heavy thread.
	 */
	public void run()
	{
		while(running)
		{
			update();
			render();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running=false;
			}
		}
	}
	
	/**
	 * Start creates a thread and makes the frame run.
	 */
	public void start()
	{
		if(!running)
		{
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
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.ORANGE);
		g.fillRect(550, 520, 2000, 6);		
		
		hero.render(g);
	
		strategy.show();
	}
	
	/**
	 * Update
	 */
	private void update() 
	{
		if(controller.keys[KeyEvent.VK_ESCAPE])
		{
			//Close here.
		}
		hero.update(controller);
	}
	
	/**
	 * MakeColorTransparent removes the frame which follows imported images.
	 * @return
	 */
	private BufferedImage makeColorTransparent(BufferedImage image) 
	{
		BufferedImage dimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g2 = dimg.createGraphics();  
		g2.setComposite(AlphaComposite.Src);  
		g2.drawImage(image, null, 0, 0);  
		g2.dispose();  
		for(int i = 0; i < dimg.getHeight(); i++) 
		{  
			for(int j = 0; j < dimg.getWidth(); j++) 
			{  
				if(dimg.getRGB(j, i) == Color.WHITE.getRGB()) 
				{  
					dimg.setRGB(j, i, 0x8F1C1C);  
				}
            }  
        }  
		return dimg;  
	}
}
