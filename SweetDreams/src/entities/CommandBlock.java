package entities;

import java.awt.Graphics2D;

import physics.*;

public class CommandBlock extends Entity {
	
	private static final double width = 80, height = 80;
	
	// 4 bits, 1111, clockwise from top
	public byte attackers = 0;

	public CommandBlock(double res, double invm) {
		super(res, invm);
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		g.fillRect((int) (pos.x - width / 2), (int) (pos.y - height / 2), (int) width, (int) height);
	}

	public BB getBBox() {
		return new AABB(pos.x - width / 2, pos.y - height / 2, pos.x + width / 2, pos.y + height / 2);
	}
	
}
