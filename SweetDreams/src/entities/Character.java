package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import physics.*;

public class Character extends Sprite {
	private static final double width = 10, height = 40;

	private static Image baseChar;
	private static Image walkChar1;
	private static Image walkChar2;
	private static Image jumpChar;
	private static Image images[] = { baseChar, walkChar1, walkChar2, jumpChar };
	private static int curr = 0;

	public Character(double res, double invm) {
		super(res, invm);
		baseChar = Toolkit.getDefaultToolkit().getImage("CharRest.png");
		walkChar1 = Toolkit.getDefaultToolkit().getImage("CharWalking1.png");
		walkChar2 = Toolkit.getDefaultToolkit().getImage("CharWalking2.png");
		jumpChar = Toolkit.getDefaultToolkit().getImage("CharJumping.png");
	}
	
	public BB getBBox() {
		return new AABB(pos.x - width / 2, pos.y - height / 2, pos.x + width / 2, pos.y + height / 2);
	}
	
	public void draw(Graphics g, int winWidth, int winHeight) {
		Graphics2D g2d = (Graphics2D) g;
		
		if (v.y > 0) { curr = 3; }
		else if (v.x == 0) { curr = 0; }
		else { // placeholder
			Random rn = new Random(); 
			int rNum = rn.nextInt(2) + 1;
			curr = rNum;
		}
		
		g2d.drawImage(images[curr], (int)(pos.x - width / 2), (int)(pos.y - height / 2), (int)width, (int)height, null);
	}

}
