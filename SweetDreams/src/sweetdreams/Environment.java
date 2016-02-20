package sweetdreams;

import java.util.ArrayList;
import java.awt.Graphics;

public class Environment {
	
	private final ArrayList<MovingObject> movingObjs;
	
	public Environment() {
		movingObjs = new ArrayList<MovingObject>();
	}
	
	public void draw(Graphics g, int winWidth, int winHeight) {
		for (MovingObject mo : movingObjs)
			mo.draw(g, winWidth, winHeight);
	}
}
