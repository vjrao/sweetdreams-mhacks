package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import physics.AABB;
import physics.BB;

public class GroundSegment extends Entity {

	private int blockWidth, blockHeight, height;
	private Color color;
	
	public GroundSegment(double res, double inv, int newWidth, int prevHeight) {
		super(res, inv);
		blockWidth = newWidth;
		blockHeight = newWidth;
		height = assignHeight(prevHeight);
	}
	
	public int assignHeight(int prevHeight) {
		Random rn = new Random(); 
		int rNum = rn.nextInt(4) - 1;
		if (rNum > 0) { rNum--; }	// overweights for 0 change
		if (prevHeight + rNum > 3) {
			return 3;	// prevent it from getting too tall
		}
		return Math.max((prevHeight + rNum), 1);
	}
	
	public int getHeight() { return height; }
	public Color getColor() { return color; }
	public void setColor(Color newCol) { color = newCol; }

	@Override
	public void draw(Graphics2D g, int winWidth, int winHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BB getBBox() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
