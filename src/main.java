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
	public static void endGame(Score playerScore) {
		if(Settings.showHiScore){
			HighScoreMenu hm = new HighScoreMenu();
			hm.inputName();	
		}
		else
			shutDown();
	}

	public static void shutDown() {
		HighscoreManager hm = new HighscoreManager();
		// Hör sötter vi in vöra variabler för poöng och namn 
		hm.addScore( HighScoreMenu.getName(),240);
		System.exit(0);

	}

}
