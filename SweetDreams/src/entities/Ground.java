package entities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import physics.*;
public class Ground extends Entity {
	private static final int DEFAULT_SEGMENTS = 40;
	private static final String IMAGE_PATH = "src/sweetdreams/Images/walltexture.png";
	private Image groundImage;
	private GroundSegment ground[];
	private LinkedList<GroundSegment> groundList = new LinkedList<GroundSegment>();
	private int numSegments;
	private int screenWidth, screenHeight, blockWidth;
	private int renderX, playerX;
	private Color color;

	public Ground(double res, double inv, int width, int height, int numSegments) {
		// super(1.0, 0.0);
		super(res, inv);
		this.numSegments=numSegments;
		ground = new GroundSegment[numSegments];
		screenWidth = width * 2;
		screenHeight = height;
		blockWidth = screenWidth / numSegments;
		renderX = 0; // where in the canvas the ground will start rendering
		playerX = screenWidth / 3;
		try {
			groundImage = ImageIO.read(new File(IMAGE_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pos.x = 0;
		pos.y = screenHeight - blockWidth;

		initializeGroundList();
	}

	public void initializeGroundList() {
		int i;
		groundList.add(new GroundSegment(restitution, invmass, blockWidth, 2));
		for (i = 1; i < numSegments + 1; i++) { // creating numSegments + 1
												// segments
			groundList.add(new GroundSegment(restitution, invmass, blockWidth, groundList.get(i - 1).getHeight()));
		}
	}

	/* delete first GroundSegment and generate/add new last */
	public void cycleGroundList() {
		groundList.removeFirst();
		groundList.add(new GroundSegment(restitution, invmass, blockWidth, groundList.getLast().getHeight()));
	}

	public void update(int deltaPlayerX) {
//		playerX += deltaPlayerX;
//		renderX -= deltaPlayerX;
//		if (renderX >= blockWidth) {
//			cycleGroundList();
//			renderX += blockWidth;
//		}
	}

	public void draw(Graphics2D g, int width, int height) {
		int i;
		/*
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON);
		 */

		/* fill in ground segments */
		for (i = 0; i < numSegments; i++) {
			g.drawImage(groundImage, (int)(pos.x + i * blockWidth),(int)( pos.y),
					blockWidth, blockWidth, null);
		}

		/* testing */
//		g.setColor(Color.GREEN);
//		g.fillRect(0, screenHeight - blockWidth, screenWidth, blockWidth);

	}

	public BB getBBox() {
		// System.out.println(pos + "\t" + v + "\t" + a);
		return new AABB(0, screenHeight - blockWidth, screenWidth, screenHeight);
	}

}
