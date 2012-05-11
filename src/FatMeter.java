import java.awt.Color;
import java.awt.Graphics2D;


public class FatMeter {
	
	public void render(Graphics2D g, double fatPoints){
			
			g.translate(Settings.width() * 0.1,Settings.height() * 0.95);
			g.rotate(Math.PI);
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, (int)(Settings.width() * 0.03), 
					(int)(Settings.height() * 0.5));
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, (int)(Settings.width() * 0.03), 
					(int)(Settings.height() * fatPoints * 0.12));
			
			g.rotate(-Math.PI);
			g.translate(-Settings.width() * 0.1, -Settings.height() * 0.95);
	}
}
