import javax.swing.JFrame;


public class CreateFrame 
{
	//Constructor
	/**
	 * Creating the JFrame with the Canvas created in Main.
	 * @param canvas
	 */
	public CreateFrame(GameCanvas canvas)
	{
		JFrame gameFrame = new JFrame("FatBoy");
		gameFrame.add(canvas);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		canvas.start();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
