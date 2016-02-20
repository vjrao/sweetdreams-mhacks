package entities;

import java.awt.Graphics;

import physics.*;

public class Virus extends Sprite {
	private static final double radius = 20;

	public Virus(double res, double invm) {
		super(res, invm);
	}

	public void draw(Graphics g, int winWidth, int winHeight) {
	}

	public BB getBBox() {
		return new Circle(radius, d.x, d.y);
	}
	
}
