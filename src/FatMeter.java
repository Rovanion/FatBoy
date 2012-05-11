import java.awt.Color;
import java.awt.Graphics2D;


public class FatMeter {
	
	public void render(Graphics2D g, double fatPoints){
	int fatMeterLevel = 0;
			if (fatPoints < 1.8) {
				fatMeterLevel = (int) (-0.15 * Settings.height() * fatPoints);
			} else if (fatPoints < 0.6) {
				fatMeterLevel = 0;
			} else {
				fatMeterLevel = (int) (-0.3 * Settings.height() * 1.3);
			}
			g.translate(500,500);
			g.rotate(Math.PI);
			g.setColor(Color.BLACK);
			g.fillRect(0,0, 50, 500);
			g.setColor(Color.YELLOW);
			g.fillRect(0,0, 50, -fatMeterLevel);
			g.rotate(-Math.PI);
			g.translate(-500,-500);
	}
}
