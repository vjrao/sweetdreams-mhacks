package entities;

import java.awt.Color;
import java.awt.Graphics;

import physics.*;

public class Virus extends Sprite {
	private static final double radius = 10;

	public Virus(double res, double invm) {
		super(res, invm);
	}

	public void draw(Graphics g, int winWidth, int winHeight) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int) (pos.x - radius), (int) (pos.y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	public BB getBBox() {
		return new Circle(radius, pos.x, pos.y);
	}

}
