import java.io.Serializable;


public class Score implements Serializable{
	private int score;
	private String name;
	
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	public Score(){
		
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}

}
