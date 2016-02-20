package entities;

import java.awt.Graphics;
import java.awt.Image;
import physics.*;

public class Character extends Sprite {
	private static final double width = 10, height = 40;

	public Character(double res, double invm) {
		super(res, invm);
	}

	private Image baseChar;
	private Image moveChar1;
	private Image moveChar2;
	private Image skidChar;

	public BB getBBox() {
		return new AABB(pos.x - width / 2, pos.y - height / 2, pos.x + width / 2, pos.y + height / 2);
	}

	public void draw(Graphics g, int winWidth, int winHeight) {
	}

}
