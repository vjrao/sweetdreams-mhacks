package entities;

import java.awt.Graphics;

import physics.*;

public class Crate extends Entity {
	private static final double width = 40, height = 40;

	private static final double res = 0.5, invm = 10;

	public Crate() {
		super(res, invm);
	}

	public void draw(Graphics g, int winWidth, int winHeight) {
	}

	public BB getBBox() {
		return new AABB(pos.x - width / 2, pos.y - height / 2, pos.x + width / 2, pos.y + height / 2);
	}

}