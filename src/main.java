public class main {

	private static HighScoreMenu hmenu;
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
			hmenu = new HighScoreMenu(playerScore);
			hmenu.inputName(playerScore);	
		}
		else
			shutDown();
	}

	public static void shutDown() {
		System.exit(0);

	}

}
