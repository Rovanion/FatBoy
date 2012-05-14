import java.io.Serializable;


public class Score  implements Serializable {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String naam, int score) {
        this.score = score;
        this.name = naam;
    }
}
