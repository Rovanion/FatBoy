import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class HighScoreMenu {
	private Image titleScreen;
	private boolean showTitleScreen;

	public HighScoreMenu(Image img) {
		titleScreen = img;
		showTitleScreen = true; // Ändrad för att slippa titleScreen under
								// testning. Ändra tbax!
	}

	public boolean isShowTitleScreen() {
		return showTitleScreen;
	}

	public void setShowTitleScreen(boolean showTitleScreen) {
		this.showTitleScreen = showTitleScreen;
	}

	public void update(Controller controller) {
		if (controller.keys[KeyEvent.VK_ENTER]) {
			showTitleScreen = false;
		}
	}

	public void render(Graphics2D g, int width, int height) {
		g.drawImage(titleScreen, 0, 0, width, height, null);
	}

}