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
	public static void endGame() {
    
	HighScoreMenu hsm = new HighScoreMenu();
	
	hsm.inputName();

	}

	public static void shutDown() {
		HighscoreManager hm = new HighscoreManager();
		// H�r s�tter vi in v�ra variabler f�r po�ng och namn 
		hm.addScore("Bart",240);
		System.exit(0);

	}

}
