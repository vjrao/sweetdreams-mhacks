package physics;

import entities.Entity;
import java.util.ArrayList;
import sweetdreams.Environment;

public class PhysicsEngine {
	public static double AIRDRAG=.001;
	public static void update(Environment env, long tdelta) {
		double dt = tdelta / (double) 1000000000;
		env.update();
		ArrayList<Entity> entities = env.elements();
		int num_entities = entities.size();
		for (Entity e : entities){
			e.a=Fnet("gd",env,e).mult(e.invmass);
			move(e, dt);
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
	public static Vec Fnet(String s, Environment env, Entity e){
		Vec fnet=new Vec();
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)=='-'){
				i++;
				fnet=fnet.minus(force(s.charAt(i),env,e));
			}
			else{
				fnet=fnet.add(force(s.charAt(i),env,e));
			}
		}
		return fnet;
	}

	private static Vec force(char name, Environment env, Entity e) {
		if (name=='g') {
			return new Vec(0.0,100.0);
		}
		if (name=='d'){
			return e.v.mult(-1*e.v.mag()*AIRDRAG);
		}
		return null;
	}
}
