package sweetdreams;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Character extends Sprite {
	
	private Image baseChar;
	private Image moveChar1;
	private Image moveChar2;
	private Image skidChar;
	
	public Character(int startX, int startY) {
		super(startX, startY);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
	}
	
}
