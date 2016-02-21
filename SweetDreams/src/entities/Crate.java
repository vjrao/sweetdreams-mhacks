package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import physics.*;

public class Crate extends Entity {
	private static final double width = 40, height = 40;
	private static Image baseChar;
	private static final double res = 1.0, invm = .005;

	public Crate() {
		super(res, invm);
	}

	public Crate(double x, double y, double xvel, double yvel) {
		super(res, invm);
		try{
			baseChar=ImageIO.read(new File("src/sweetdreams/Images/crate.jpg"));
		}catch(Exception ex){
			
		}
		pos.x = x;
		pos.y = y;
		v.x = xvel;
		v.y = yvel;
	}

	public void draw(Graphics2D g, int winWidth, int winHeight) {
		g.setColor(Color.GREEN);
//		g.fillRect((int) (pos.x - width / 2), (int) (pos.y - height / 2), (int) width, (int) height);
		g.drawImage(baseChar,(int) (pos.x - width / 2), (int) (pos.y - height / 2), (int) width, (int) height,null);
	}

	public BB getBBox() {
		return new AABB(pos.x - width / 2, pos.y - height / 2, pos.x + width / 2, pos.y + height / 2);
	}

}