public class main {

	/**
	 * Main jump starts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameCanvas newCanvas = new GameCanvas();
		CreateFrame frame = new CreateFrame(newCanvas);
	}
	public static void endGame(){
		//Load the hiscore from file and show it
		
		System.exit(0);
	}

}
