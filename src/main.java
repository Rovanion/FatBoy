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
		if(Settings.showHiScore){
			HighScoreMenu hsm = new HighScoreMenu();
			hsm.inputName();
		}
	}

	public static void shutDown() {
		HighscoreManager hm = new HighscoreManager();
		// Hör sötter vi in vöra variabler för poöng och namn 
		hm.addScore( HighScoreMenu.getName(),240);
		System.exit(0);

	}

}
