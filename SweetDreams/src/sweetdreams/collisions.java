

public class collisions {
	
	public static void collide(MovingObject m1, MovingObject m2){
		Vec v1prime=new Vec(Vec.mult(1/(m1.m+m2.m),Vec.add(Vec.mult((m1.m-m2.m),m1.v),Vec.mult(2*m2.m, m2.v))));
		Vec v2prime=new Vec(Vec.mult(1/(m1.m+m2.m),Vec.add(Vec.mult((m2.m-m1.m),m2.v),Vec.mult(2*m1.m, m1.v))));
		m1.v=v1prime;
		m2.v=v2prime;
	}

}
