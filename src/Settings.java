import java.awt.Dimension;
import java.awt.Toolkit;

public class Settings {
	//Permanent settings that are not changed during runtime
	public static final double groundLevel = 0.9;
	public static final boolean musicPlaying = true;
	public static final boolean musicPaused = false;
	public static final boolean titleMusic = false;
	public static final boolean showHiScore = true;
	public static final boolean liveDebugging = false;
	public static Dimension dim = new Dimension(1280, 720);
//	public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int height() {
		return dim.height;
	}

	public static int width() {
		return dim.width;
	}

	//Settings that may change during runtime
	public static boolean showTitleScreen = true;

}
