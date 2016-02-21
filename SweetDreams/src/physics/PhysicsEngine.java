package physics;

import entities.Entity;
import entities.Ground;

import java.util.ArrayList;
import sweetdreams.Environment;

public class PhysicsEngine {

	public static double SURFACE_FRICTION = 10;
	public static double AIRDRAG = .001;
	public static double GRAVITY = 200;
	private static String forces = "gf";

	public static void update(Environment env, long tdelta) {
		double dt = tdelta / (double) 1000000000;
		env.update(tdelta);
		ArrayList<Entity> entities = env.elements();
		int num_entities = entities.size();
		for (Entity e : entities) {
			if (!(e instanceof Ground)) {
				e.a = Fnet(forces, env, e).mult(e.invmass);
				move(e, dt);
			}
		}
		for (int i = 0; i < num_entities; i++)
			for (int j = i + 1; j < num_entities; j++) {
				Entity ei = entities.get(i), ej = entities.get(j);
				Vec norm = Collisions.intersects(ei, ej);
				if (norm != null)
					Collisions.resolveCollision(ei, ej, norm);
			}
	}

	public static void move(Entity e, double dt) {
		e.v = e.v.add(e.a.mult(dt));
		e.pos = e.pos.add(e.v.mult(dt));
	}

	public static Vec Fnet(String s, Environment env, Entity e) {
		Vec fnet = new Vec();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-')
				fnet = fnet.minus(force(s.charAt(++i), env, e));
			else
				fnet = fnet.add(force(s.charAt(i), env, e));
		}
		return fnet;
	}

	public static final double MOVEMENT_EPSILON = 0.5;

	private static Vec force(char name, Environment env, Entity e) {
		if (name == 'g')
			return new Vec(0.0, GRAVITY);
		else if (name == 'd')
			return e.v.mult(-1 * e.v.mag() * AIRDRAG);
		else if (name == 'f')
			// if (Math.abs(e.v.x) < MOVEMENT_EPSILON) {
			// e.v.x = 0;
			// return new Vec();
			// } else
			return new Vec(-e.v.x * SURFACE_FRICTION, 0.0);
		else
			throw new IllegalArgumentException();
	}
}
