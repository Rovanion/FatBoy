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
		// Här sätter vi in våra variabler för poäng och namn 
		hm.addScore("Bart",240);
		System.exit(0);

	}

}
