import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;


public class TitleScreen 
{
	private Image titleScreen;
	
	public TitleScreen(Image img)
	{
		titleScreen = img;
	}

	public void update(Controller controller)
	{
		if (controller.keys[KeyEvent.VK_ENTER]) 
		{
			Settings.showTitleScreen = false;
		}
	}
	
	public void render(Graphics2D g, int width, int height)
	{
		g.drawImage(titleScreen, 0, 0, width, height, null);
	}

}
