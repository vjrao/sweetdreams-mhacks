package sweetdreams;

import java.util.ArrayList;

import entities.MovingObject;

import java.awt.Graphics;

public class Environment {
	
	private final ArrayList<MovingObject> movingObjs;
	
	public Environment() {
		movingObjs = new ArrayList<MovingObject>();
	}
	
	public void addElement(MovingObject mo) {
		movingObjs.add(mo);
	}
	
	public ArrayList<MovingObject> elements() {
		return movingObjs;
	}
	
	public void draw(Graphics g, int winWidth, int winHeight) {
		for (MovingObject mo : movingObjs)
			mo.draw(g, winWidth, winHeight);
	}
}
