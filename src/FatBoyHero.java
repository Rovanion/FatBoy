import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class FatBoyHero 
{
	private Image image;
	private float x;
	private float y;
	
	public FatBoyHero(Image image, float x, float y)
	{
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void update(Controller controller)
	{
		if(controller.keys[KeyEvent.VK_D]) x += 1;
		if(controller.keys[KeyEvent.VK_A]) x -= 1;
	}
	
	public void render(Graphics2D g)
	{
		//g.translate(x, y);
		g.drawImage(image, (int)x, (int)y, image.getWidth(null), image.getHeight(null), null);
		//g.translate(-x,-y);
	}
}
 