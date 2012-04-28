import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Main {

	/**
	 * Main jump starts the application.
	 * 
	 * @param args
	 */
	public static void Main(String[] args) {
		GameCanvas newCanvas = new GameCanvas();
		CreateFrame frame = new CreateFrame(newCanvas);
	}

	public static void endGame(){
		HighScoreList list = null;
		
		try {
			// deserialize from filen
			ObjectInputStream in =new ObjectInputStream(new FileInputStream("list.ser"));
			in.close();
			
		}catch(IOException e){
			HighScoreList HighScoreList = new HighScoreList();
			
		}
		
		
		
		//Visar listan samt sparar namn.
		
		try {	
			//Seralize to fil
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("list.ser"));
	    out.writeObject(list);
	    out.close();
	    
		}
		catch(IOException e){
		}
		
		System.exit(0);
	}
}
