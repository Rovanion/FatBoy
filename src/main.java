public class main {

	private static HighScoreMenu hmenu = new HighScoreMenu();
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
			
			hmenu.inputName(playerScore);	
		}
		else
			shutDown();
	}

	public static void shutDown() {
		HighscoreManager hm = new HighscoreManager();
		// Hör sötter vi in vöra variabler för poöng och namn 
		hm.addScore( hmenu.getName(),hmenu.getScore());
		System.exit(0);

	}

}
