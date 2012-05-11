import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TitleScreen {
	private Image titleScreen;
	private Image buttonTut;
	private Image insertCoin;
	private boolean showTitleScreen;
	private int flash=0;
	private int maxFlash=150;
	private boolean showTut;
	
	public TitleScreen(Image img)
	{
		titleScreen = img;
		showTitleScreen=true; //öndrad för att slippa titleScreen under testning. öndra tbax!
		try {
			buttonTut = ImageIO.read(getClass().getResource("ButtonTutorial.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			insertCoin = ImageIO.read(getClass().getResource("InsertCoinPixelated.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		showTut=false;
	}
	
	public boolean isShowTitleScreen() {
		return showTitleScreen;
	}

	public void update(Controller controller) {
		if (controller.keys[KeyEvent.VK_ENTER]) {
			Settings.showTitleScreen = false;
		}
	}

	public void render(Graphics2D g, int width, int height) {
		g.drawImage(titleScreen, 0, 0, width, height, null);
		if(flash==maxFlash || showTut)
		{
		g.drawImage(buttonTut, (int)(Settings.width()*0.1), (int)(Settings.height()*0.8), buttonTut.getWidth(null), buttonTut.getHeight(null), null);
		}
		if(flash>70)
		{
		g.drawImage(insertCoin, (int)(Settings.width()*0.35), (int)(Settings.height()*0.5), insertCoin.getWidth(null), insertCoin.getHeight(null), null);
		}
		flash++;
		if(flash==maxFlash)
		{
			flash=0;
			showTut=true;
		}
	}

}
