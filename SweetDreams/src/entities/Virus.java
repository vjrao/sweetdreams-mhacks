package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import physics.*;

public class Virus extends Sprite {
	private static final double jump = -50;
	private static final double speed = 10;
	private static final double radius = 40;

	private static Image baseChar;
	private static Image walkChar1;
	private static Image walkChar2;
	private static Image jumpChar;
	private static Image images[] = { baseChar, walkChar1, walkChar2, jumpChar };
	
	public CommandBlock target = null;
	public int targetSide = -1;

	public Virus(double res, double invm) {
		super(res, invm);
		try {
			images[0] = ImageIO.read(new File("src/sweetdreams/Images/VirusRest-02.png"));
			images[1] = ImageIO.read(new File("src/sweetdreams/Images/VirusWalking1-02.png"));
			images[2] = ImageIO.read(new File("src/sweetdreams/Images/VirusWalking2-02.png"));
			images[3] = ImageIO.read(new File("src/sweetdreams/Images/VirusJumping-02.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Virus(double res, double invm, double x, double y, double xvel, double yvel) {
		super(res, invm);
		pos.x = x;
		pos.y = y;
		v.x = xvel;
		v.y = yvel;
	}

	public void accelerate(int direction) {
		v.x += speed * direction;
	}
	
	public void jump() {
		if (v.y == 0)
			v.y = jump;
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		g.setColor(Color.MAGENTA);
		g.drawImage(images[0], (int) (pos.x - radius), (int) (pos.y - radius), (int) (2 * radius), (int) (2 * radius),
				null);
//		g.fillOval((int) (pos.x - radius), (int) (pos.y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	public BB getBBox() {
		return new Circle(radius, pos.x, pos.y);
	}

}
