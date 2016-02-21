package physics;

import entities.Entity;
import entities.Ground;
import entities.PCharacter;

import java.util.ArrayList;
import sweetdreams.Environment;

public class PhysicsEngine {

	public static double SURFACE_FRICTION = 10;
	public static double AIRDRAG = .001;
	public static double GRAVITY = 10.0;
	private static String forces = "";

	public static String update(Environment env, long tdelta, String rules) {
		forces=rules;
		double dt = tdelta / (double) 1000000000;
		rules=env.update(tdelta,rules);
		ArrayList<Entity> entities = env.elements();
		int num_entities = entities.size();
		for (Entity e : entities) {
			if (!(e instanceof Ground)) {
				e.a = Fnet(forces, env, e).mult(e.invmass);
//				System.out.println(e.a+" "+e.onsurface);
				move(e, dt);
				//System.out.println(""+e+e.v+e.a);
			}
		}
		for (int i = 0; i < num_entities; i++){
			for (int j = i + 1; j < num_entities; j++) {
				Entity ei = entities.get(i), ej = entities.get(j);
				Vec norm = Collisions.intersects(ei, ej);
				if (norm != null){
//					if(ei.onsurface || ej.onsurface)continue;
					if ((ei instanceof PCharacter)&&norm.x==0) {
						ei.onsurface=true;
//						ei.a.y=0;
//						ej.v.y=0;
//						continue;
					}
					if ((ej instanceof PCharacter)&&norm.x==0) {
						ej.onsurface=true;
//						ej.a.y=0;
//						ej.v.y=0;
//						continue;
					}
					Collisions.resolveCollision(ei, ej, norm);
				}
			}
		}
		return rules;
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

	public static final double MOVEMENT_EPSILON = 2.0;

	private static Vec force(char name, Environment env, Entity e) {
		if (name == 'g'){
			//System.out.println(GRAVITY/e.invmass);
//			if (e.onsurface) return new Vec();
			return new Vec(0.0, GRAVITY/e.invmass);
		}else if (name == 'd')
			return e.v.mult(-1 * e.v.mag() * AIRDRAG);
		else if (name == 'f')
			if (Math.abs(e.v.x) < MOVEMENT_EPSILON) {
				e.v.x = 0;
				return new Vec();
			} else{
				if (!e.onsurface) {
					return new Vec();
				}
				return new Vec(-e.v.x * SURFACE_FRICTION, 0.0);}
		else
			throw new IllegalArgumentException();
	}
}
