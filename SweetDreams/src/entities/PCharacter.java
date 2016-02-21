package entities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import physics.*;

public class PCharacter extends Sprite {
	private int dir=-1;
	private static final double width = 60, height = 100;

	private static Image baseChar;
	private static Image walkChar1;
	private static Image walkChar2;
	private static Image jumpChar;
	private static Image images[] = { baseChar, walkChar1, walkChar2, jumpChar };
	private static int curr = 0;

	public PCharacter(double res, double invm) {
		super(res, invm);
		try {
			images[0] = ImageIO.read(new File("src/sweetdreams/Images/CharRest.png"));
			images[1] = ImageIO.read(new File("src/sweetdreams/Images/CharWalking1.png"));
			images[2] = ImageIO.read(new File("src/sweetdreams/Images/CharWalking2-2.png"));
			images[3] = ImageIO.read(new File("src/sweetdreams/Images/CharJumping.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pos.x = 200; // center
		pos.y = 0; // center
		v.y=30;
	}

	public BB getBBox() {
		return new AABB(pos.x - width / 4, pos.y - height / 2, pos.x + width / 4, pos.y + height / 2);
	}

	public void update(long tdelta) {

		long millis = System.currentTimeMillis() % 1000;

		// cause of standing in midair bug?
		if (!onsurface)
			curr = 3;
		else if (Math.abs(v.x) <= PhysicsEngine.MOVEMENT_EPSILON)
			curr = 0;
		else if ((millis / 170) % 2 == 0)
			curr = 1;
		else
			curr = 2;
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		if (v.x < 0) {
			g.drawImage(images[curr], (int) (pos.x - width / 2 + width), (int) (pos.y - height / 2), (int) (-1 * width),
					(int) height, null);
			dir=1;
		} else if (v.x>0){
			g.drawImage(images[curr], (int) (pos.x - width / 2), (int) (pos.y - height / 2), (int) width, (int) height,
					null);
			dir=-1;
		}
		else{
			if (dir==-1) {
				g.drawImage(images[curr], (int) (pos.x - width / 2), (int) (pos.y - height / 2), (int) width, (int) height,
						null);
			}
			else{
				g.drawImage(images[curr], (int) (pos.x - width / 2 + width), (int) (pos.y - height / 2), (int) (-1 * width),
						(int) height, null);
			}
		}
		g.setColor(Color.red);
		//g.drawRect((int)(pos.x - width / 2),(int) (pos.y - height / 2),(int) (width), (int)(height));

		// g.setColor(Color.RED);
		// g.drawRect((int) (pos.x - width / 2), (int) (pos.y - height / 2),
		// (int) width, (int) height);
	}

}