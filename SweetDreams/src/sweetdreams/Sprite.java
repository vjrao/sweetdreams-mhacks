package sweetdreams;

public class Sprite {
	private int x, y;
	
	public Sprite(int startX, int startY) {
		x = startX;
		y = startY;
	}
	
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
}
