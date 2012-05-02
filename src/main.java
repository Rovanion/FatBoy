
public class main {

	/**
	 * Main jumpstarts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameCanvas newCanvas = new GameCanvas();
		CreateFrame frame = new CreateFrame(newCanvas);
	}
	
	public static void endGame(){
		
		System.exit(0);
	}
}
