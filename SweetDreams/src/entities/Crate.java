package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import physics.*;

public class Crate extends Entity {
	private static final double width = 40, height = 40;

	private static final double res = 0.5, invm = .25;

	public Crate() {
		super(res, invm);
	}

	public Crate(double x, double y, double xvel, double yvel) {
		super(res, invm);
		pos.x = x;
		pos.y = y;
		v.x = xvel;
		v.y = yvel;
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		g.setColor(Color.GREEN);
		g.fillRect((int) (pos.x - width / 2), (int) (pos.y - height / 2), (int) width, (int) height);
	}

	public BB getBBox() {
		return new AABB(pos.x - width / 2, pos.y - height / 2, pos.x + width / 2, pos.y + height / 2);
	}

}