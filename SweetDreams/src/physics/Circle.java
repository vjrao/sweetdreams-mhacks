package physics;

public class Circle extends BB {

	public double radius;
	public Vec pos;

	/**
	 * 
	 * @param radius
	 * @param x
	 * @param y
	 */
	public Circle(double radius, double x, double y) {
		this.radius = radius;
		pos = new Vec(x, y);
	}

	public double distSqr(Circle o) {
		double dx = pos.x - o.pos.x, dy = pos.y - o.pos.y;
		return dx * dx + dy * dy;
	}
}
