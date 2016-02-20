package sweetdreams;

import java.awt.Graphics;
import java.awt.Color;

public class MovingObject implements Drawable {
	public Vec d,v,a;
	public double m;
	
	public void draw(Graphics g, int winWidth, int winHeight) {
		g.setColor(Color.GREEN);
		g.fillRect((int)d.x, (int) d.y, 10, 10);
	}
}
