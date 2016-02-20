package sweetdreams;

import java.awt.Color;
import java.util.Random;

public class GroundSegment {

	private int blockWidth, blockHeight, height;
	private Color color;
	
	public GroundSegment(int newWidth, int prevHeight) {
		blockWidth = newWidth;
		blockHeight = newWidth;
		height = assignHeight(prevHeight);
	}
	
	public int assignHeight(int prevHeight) {
		Random rn = new Random(); 
		int rNum = rn.nextInt(3) - 1;
		return prevHeight + rNum;
	}
	
	public int getHeight() { return height; }
	public Color getColor() { return color; }
	public void setColor(Color newCol) { color = newCol; }
	
}
