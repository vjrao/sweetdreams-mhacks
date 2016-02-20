package entities;

import java.awt.Graphics;

import sweetdreams.Drawable;
import sweetdreams.Vec;

import java.awt.Color;

public abstract class MovingObject implements Drawable {
	public Vec d, v, a;
	public double m;
	
	public MovingObject() {
		d = new Vec();
		v = new Vec();
		a = new Vec();
	}
	
	public void draw(Graphics g, int winWidth, int winHeight) {
		g.setColor(Color.GREEN);
		g.fillRect((int)d.x, (int) d.y, 10, 10);
	}
}
