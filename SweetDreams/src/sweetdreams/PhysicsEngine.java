package sweetdreams;

import entities.MovingObject;

public class PhysicsEngine {

	public static void update(Environment env, long tdelta) {
		double dt = tdelta / (double) 1000000000;
		for (MovingObject mo : env.elements()) {
			mo.v = mo.v.add(mo.a.mult(dt));
			mo.d = mo.d.add(mo.v.mult(dt));
		}
	}

}
