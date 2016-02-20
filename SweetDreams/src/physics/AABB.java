package physics;

public class AABB extends BB {

	public Vec min, max;

	public AABB(double x, double y, double x2, double y2) {
		min = new Vec(x, y);
		max = new Vec(x2, y2);
	}
}
