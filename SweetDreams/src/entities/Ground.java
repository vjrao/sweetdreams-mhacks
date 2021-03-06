package entities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import physics.*;

public class Ground extends Entity {
	private static final int DEFAULT_SEGMENTS = 40;
	private static final String IMAGE_PATH = "src/sweetdreams/Images/brick-tile-darker.jpg";
	private Image groundImage;
	private LinkedList<GroundSegment> groundList = new LinkedList<GroundSegment>();
	private int numSegments;
	private int screenWidth, screenHeight, blockWidth;
	private int renderX, renderY, playerX;
	private Color color;

	public Ground(double res, double inv, int width, int height, int newNumSegments, int topOrBot) {
//		super(1.0, 0.0);
		super(res, inv);
		numSegments = newNumSegments;
		screenWidth = width;
		if (topOrBot == 1) {	// bottom
			renderY = height;			
		}
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
		pos.y = renderY + screenHeight - blockWidth;
		
		initializeGroundList();
	}

	public void initializeGroundList() {
		int i;
		groundList.add(new GroundSegment(restitution, invmass, blockWidth, 2, 0, screenHeight));
		for (i = 1; i < numSegments + 1; i++) { // creating numSegments + 1
												// segments
			groundList.add(new GroundSegment(restitution, invmass, blockWidth, groundList.get(i - 1).getHeight(), i*blockWidth, screenHeight));
		}
	}

	/* delete first GroundSegment and generate/add new last */
	public void cycleGroundList() {
		groundList.removeFirst();
		groundList.add(new GroundSegment(restitution, invmass, blockWidth, groundList.getLast().getHeight(), screenWidth, screenHeight));
	}

	public void update(int deltaPlayerX) {
		playerX += deltaPlayerX;
		renderX -= deltaPlayerX;
		if (renderX >= blockWidth) {
			cycleGroundList();
			renderX += blockWidth;
		}
	}

	public void draw(Graphics2D g, int width, int height) {
		int i, j;
		/*
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON);
		 */

		/* fill in ground segments */
		for (i = 0; i < numSegments; i++) {
			for (j = 0; j < groundList.get(i).getHeight(); j++) {
				g.drawImage(groundImage, renderX + i * blockWidth, renderY + screenHeight - (j+1) * blockWidth,
						blockWidth, blockWidth, null);				
			}
		}
		
		/*
		 * g.drawImage(groundImage, renderX + i * blockWidth, renderY + screenHeight - (groundList.get(i).getHeight() * blockWidth),
						blockWidth, groundList.get(i).getHeight() * blockWidth, null);
		 */
		
		/* testing */
//		g.setColor(Color.GREEN);
//		g.fillRect(0, renderY + screenHeight-blockWidth, screenWidth, blockWidth);
		
		g.finalize();
	}
	
	public LinkedList<GroundSegment> getSegmentList() { return groundList; }

	public BB getBBox() {
//		return new AABB(0, renderY + screenHeight-blockWidth, screenWidth*2, renderY + screenHeight);
		return new AABB(0, 0, 0, 0);
	}

}
