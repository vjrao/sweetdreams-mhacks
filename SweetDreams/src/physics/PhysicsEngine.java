package physics;

import entities.Entity;
import java.util.ArrayList;
import sweetdreams.Environment;

public class PhysicsEngine {
	private static final double FRICTION = 0.01;
	private static final double FRICTION_COEFF = 1.0 - FRICTION;

	public static void update(Environment env, long tdelta) {
		double dt = tdelta / (double) 1000000000;
		ArrayList<Entity> entities = env.elements();
		int num_entities = entities.size();
		for (Entity e : entities)
			move(e, dt);

		for (int i = 0; i < num_entities; i++)
			for (int j = i + 1; j < num_entities; j++) {
				Entity ei = entities.get(i), ej = entities.get(j);
				Vec norm = Collisions.intersects(ei, ej);
				if (norm != null)
					Collisions.resolveCollision(ei, ej, norm);
			}
	}

	public static void move(Entity e, double dt) {
		e.v = e.v.add(e.a.mult(dt)).mult(FRICTION_COEFF);
		e.pos = e.pos.add(e.v.mult(dt));
	}
}
