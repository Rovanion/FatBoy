import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;


public class FlyingObject {
	
	
		private Image image;
		private long mass;
		private long fatpoints;
		private long highscore;
		private double x;
		private double y;
		
		/**
		 * 
		 * @param image 
		 * @param mass
		 * @param fatpoints
		 * @param highscore
		 */
		public FlyingObject(Image image, long mass, long fatpoints, long highscore)
		{
			this.image = image;
			this.mass = mass;
			this.fatpoints = fatpoints;
			this.highscore = highscore;
		}
		
		
		
		public void update()
		{
			//här skrivs en formel för x och en för y för kastparabel. koordinaterna.
			
		}
		
		public void render(Graphics2D g)
		{
			//g.translate(x, y);
			g.drawImage(image, (int)x, (int)y, image.getWidth(null), image.getHeight(null), null);
			//g.translate(-x,-y);
		}
	}
	 


