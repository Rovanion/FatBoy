import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class FlyingObjectUtility
{
	private FlyingObject fo;
	private Image oImage;
	private Random rand;
	
	public FlyingObjectUtility()
	{
		rand = new Random();
	}
	
	private Image fetchImage(String fileName)
	{
		try {
			oImage = ImageIO.read(getClass().getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oImage;
	}
	
	public FlyingObject createHamburger()
	{
		return fo = new FlyingObject(fetchImage("Hamburger.png"), 0.2, 5, 5);
	}
	
	public FlyingObject createFries()
	{
		return fo = new FlyingObject(fetchImage("pommes.png"), 0.4, 10, 10);
	}
	
	public FlyingObject createIceCream()
	{
		return fo = new FlyingObject(fetchImage("IceCream.png"), 0.7, 15, 15);
	}
	
	public FlyingObject createCarrot()
	{
		return fo = new FlyingObject(fetchImage("carrot.png"), 0.5, -10, 0);
	}
	
	public FlyingObject createApple()
	{
		return fo = new FlyingObject(fetchImage("Apple.png"), 0.8, -5, 0);
	}
	
	public double getRandomNumber()
	{
		return rand.nextDouble();
	}
}
