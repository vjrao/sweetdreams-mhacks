package entities;

import physics.BB;
import physics.Vec;

public abstract class Entity implements Drawable {
	public boolean onsurface=false;
	public final double restitution, invmass;
	public Vec pos, v, a;

	public Entity(double res, double invm) {
		restitution = res;
		invmass = invm;
		pos = new Vec();
		v = new Vec();
		a = new Vec();
		onsurface=false;
	}

	public abstract BB getBBox();
}
