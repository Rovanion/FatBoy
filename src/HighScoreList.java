import java.util.Vector;
import java.io.*;

public class HighScoreList extends Vector {

	void put(Object o) {
		addElement(o);
	}

	Object get() {
		if (isEmpty())
			return null;
		Object o = firstElement();
		removeElement(o);
		return o;
	}

	Object peek() {
		if (isEmpty())
			return null;
		return firstElement();
	}
}
