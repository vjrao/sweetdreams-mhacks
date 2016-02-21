package entities;

import java.awt.Graphics2D;

import physics.BB;

public class CommandBlock extends Entity {
	
	// 4 bits, 1111, clockwise from top
	public byte attackers = 0;

	public CommandBlock(double res, double invm) {
		super(res, invm);
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		
	}

	public BB getBBox() {
		return null;
	}
	
}
