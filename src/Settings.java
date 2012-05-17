import java.awt.Dimension;

public class Settings {
	//Permanent settings that are not changed during runtime
	public static final double groundLevel = 0.9;
	public static final boolean musicPlaying = true;
	public static final boolean musicPaused = true;
	public static final boolean titleMusic = true;
	public static final boolean showHiScore = false;
	public static final boolean liveDebugging = false;
	public static Dimension dim = new Dimension(1280, 720);
	public static int height() {
		return dim.height;
	}

	public static int width() {
		return dim.width;
	}

	//Settings that may change during runtime
	public static boolean showTitleScreen = true;

}
