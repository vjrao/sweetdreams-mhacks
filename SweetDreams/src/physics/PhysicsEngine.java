package physics;

import entities.Entity;
import sweetdreams.Environment;

public class PhysicsEngine {

	public static void update(Environment env, long tdelta) {
		double dt = tdelta / (double) 1000000000;
		for (Entity e : env.elements()) {
			e.v = e.v.add(e.a.mult(dt));
			e.d = e.d.add(e.v.mult(dt));
		}
	}

}
