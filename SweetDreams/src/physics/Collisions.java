package physics;

import entities.Entity;

public class Collisions {

	private static final double SINK_CORRECTION_FACTOR = 0.2;
	private static final double SINK_CORRECTION_THRESHOLD = 0.01;

	public void resolveCollision(Entity a, Entity b) {
		Vec rv = a.v.minus(b.v), norm = a.d.minus(b.d).unit();
		double nvel = rv.dot(norm);
		if (nvel > 0)
			return;
		double minres = Math.min(a.restitution, b.restitution);
		double impScal = -(1 + minres) * nvel;
		impScal /= a.invmass + b.invmass;
		Vec impVec = norm.mult(impScal);
		a.v = a.v.minus(impVec.mult(a.invmass));
		b.v = b.v.minus(impVec.mult(b.invmass));
	}

	// private void sinkCorrect(Entity a, Entity b) {
	// Vec pendepth = a.d.minus(b.d).mult(SINK_CORRECTION_FACTOR/ (a.invmass +
	// b.invmass));
	// a.d.minus(pendept)
	// }

	public boolean intersects(Entity ea, Entity eb) {
		BB a = ea.getBBox(), b = eb.getBBox();
		if (a instanceof Circle)
			if (b instanceof Circle)
				return intersects((Circle) a, (Circle) b);
			else
				return intersects((Circle) a, (AABB) b);
		else if (b instanceof Circle)
			return intersects((Circle) b, (AABB) a);
		else
			return intersects((AABB) a, (AABB) b);
	}

	public boolean intersects(Circle a, Circle b) {
		double r = a.radius + b.radius;
		return b.pos.minus(a.pos).magSquared() > r * r;
	}

	public boolean intersects(AABB a, AABB b) {
		return a.min.x < b.max.x && a.max.x > b.min.x && a.min.y < b.max.y && a.max.y > b.min.y;
	}

	public boolean intersects(Circle a, AABB b) {
		if (a.pos.x > b.min.x && a.pos.x < b.max.x || a.pos.y > b.min.y && a.pos.y < b.max.y)
			return true;
		double rsquare = a.radius * a.radius;
		return b.min.minus(a.pos).magSquared() < rsquare || b.max.minus(a.pos).magSquared() < rsquare
				|| new Vec(b.min.x, b.max.y).minus(a.pos).magSquared() < rsquare
				|| new Vec(b.max.x, b.min.y).minus(a.pos).magSquared() < rsquare;
	}

	// private static class Manifold {
	// Entity a, b;
	// double pen;
	// Vec norm;
	//
	// public Manifold(){}
	//
	// public Manifold(Entity a, Entity b, double penetration, Vec normal) {
	// this.a = a;
	// this.b = b;
	// pen = penetration;
	// norm = normal;
	// }
	// }
	//

}
