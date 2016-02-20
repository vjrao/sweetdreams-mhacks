package sweetdreams;

import java.util.ArrayList;

import entities.Entity;

import java.awt.Graphics;

public class Environment {

	private final ArrayList<Entity> entities;

	public Environment() {
		entities = new ArrayList<Entity>();
	}

	public void addElement(Entity e) {
		entities.add(e);
	}

	public ArrayList<Entity> elements() {
		return entities;
	}

	public void draw(Graphics g, int winWidth, int winHeight) {
		for (Entity e : entities)
			e.draw(g, winWidth, winHeight);
	}
}
