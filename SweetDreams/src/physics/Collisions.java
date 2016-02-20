package physics;

import entities.Entity;

public class Collisions {

	private static final double SINK_CORRECTION_FACTOR = 0.2;
	private static final double SINK_CORRECTION_THRESHOLD = 0.01;

	public static void resolveCollision(Entity a, Entity b, Vec norm) {
		Vec rv = b.v.minus(a.v);
		double nvel = rv.dot(norm);
		if (nvel > 0)
			return;
		double minres = Math.min(a.restitution, b.restitution);
		double impScal = -(1 + minres) * nvel;
		impScal /= a.invmass + b.invmass;
		Vec impVec = norm.mult(impScal);
		a.v = a.v.minus(impVec.mult(a.invmass));
		b.v = b.v.add(impVec.mult(b.invmass));
	}

	// private void sinkCorrect(Entity a, Entity b) {
	// Vec pendepth = a.d.minus(b.d).mult(SINK_CORRECTION_FACTOR/ (a.invmass +
	// b.invmass));
	// a.d.minus(pendept)
	// }

	public static Vec intersects(Entity ea, Entity eb) {
		BB a = ea.getBBox(), b = eb.getBBox();
		if (a instanceof Circle)
			if (b instanceof Circle)
				return intersects((Circle) a, (Circle) b);
			else
				return intersects((AABB) b, (Circle) a);
		else if (b instanceof Circle)
			return intersects((AABB) a, (Circle) b);
		else
			return intersects((AABB) a, (AABB) b);
	}

	private static Vec intersects(Circle a, Circle b) {
		double r = a.radius + b.radius;
		Vec ret = b.pos.minus(a.pos);
		if (ret.magSquared() < r * r)
			return ret.unit();
		else
			return null;
	}

	private static Vec intersects(AABB a, AABB b) {
		Vec norm = b.min.minus(a.min);
		double xpen = (a.max.x - a.min.x) / 2 + (b.max.x - b.min.x) / 2 - Math.abs(norm.x);
		if (xpen > 0) {
			double ypen = (a.max.y - a.min.y) / 2 + (b.max.y - b.min.y) / 2 - Math.abs(norm.y);
			if (ypen > 0) {
				if (xpen < ypen)
					if (norm.x < 0)
						return new Vec(-1, 0);
					else
						return new Vec(1, 0);
				else if (norm.y < 0)
					return new Vec(0, -1);
				else
					return new Vec(0, 1);
			} else
				return null;
		} else
			return null;
	}

	private static Vec intersects(AABB a, Circle b) {
		Vec norm = b.pos.minus(a.min.avg(a.max));
		Vec closest = norm.copy();
		double half_width = (a.max.x - a.min.x) / 2;
		double half_height = (a.max.y - a.min.y) / 2;
		closest.x = clamp(-half_width, half_width, closest.x);
		closest.y = clamp(-half_height, half_height, closest.y);
		boolean inside = false;
		if (norm.equals(closest)) {
			inside = true;
			if (Math.abs(norm.x) > Math.abs(norm.y))
				if (closest.x > 0)
					closest.x = half_width;
				else
					closest.x = -half_width;
			else if (closest.y > 0)
				closest.y = half_height;
			else
				closest.y = -half_height;
		}

		Vec normal = norm.minus(closest);
		double d = normal.magSquared();
		double r = b.radius;

		if (d > r * r && !inside)
			return null;

		d = Math.sqrt(d);

		if (inside)
			return norm.mult(-1).unit();
		else
			return norm.unit();
	}

	private static double clamp(double low, double high, double val) {
		if (val < low)
			return low;
		else if (val > high)
			return high;
		else
			return val;
	}

}
