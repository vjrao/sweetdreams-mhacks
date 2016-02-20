

public class collisions {
	public static double dt=.04;
	public static void iterate(MovingObject m){
		m.v=Vec.add(m.v,Vec.mult(dt,m.a));
		m.d=Vec.add(m.d,Vec.mult(dt,m.v));
	}
	public static void collide(MovingObject m1, MovingObject m2){
		Vec v1prime=Vec.add(m1.v,Vec.mult(-2*m2.m/(m1.m+m2.m),Vec.proj(Vec.add(m1.v, Vec.mult(-1, m2.v)),Vec.add(m1.d, Vec.mult(-1, m2.d)))));
		Vec v2prime=Vec.add(m2.v,Vec.mult(-2*m1.m/(m1.m+m2.m),Vec.proj(Vec.add(m2.v, Vec.mult(-1, m1.v)),Vec.add(m2.d, Vec.mult(-1, m1.d)))));
		m1.v=v1prime;
		m2.v=v2prime;
	}

}
