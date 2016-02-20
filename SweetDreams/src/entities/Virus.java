package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import physics.*;

public class Virus extends Sprite {
	private static final double radius = 40;

	public Virus(double res, double invm) {
		super(res, invm);
	}

	public Virus(double res, double invm, double x, double y, double xvel, double yvel) {
		super(res, invm);
		pos.x = x;
		pos.y = y;
		v.x = xvel;
		v.y = yvel;
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int) (pos.x - radius), (int) (pos.y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	public BB getBBox() {
		return new Circle(radius, pos.x, pos.y);
	}

}
