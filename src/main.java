public class main {

	private static HighScoreMenu hmenu;
	/**
	 * Main jumpstarts the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		start();
	}
	public static void endGame(int playerScore) {
		if(Settings.showHiScore){
			hmenu = new HighScoreMenu(playerScore);
			hmenu.inputName(playerScore);	
		}
		else
			shutDown();
	}
	
	public static void start()
	{
		GameCanvas newCanvas = new GameCanvas();
		CreateFrame frame = new CreateFrame(newCanvas);
	}

	public static void shutDown() {
		//Kill all threads, restart application.
		System.exit(0);
	}

}
