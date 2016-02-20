package sweetdreams;

public class Vec {
	public double x, y;

	public Vec() {
	}

	public Vec(Vec v) {
		this.x = v.x;
		this.y = v.y;
	}

	public Vec(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec add(Vec o) {
		return new Vec(x + o.x, y + o.y);
	}
	
	public Vec mult(double scalar) {
		return new Vec(x * scalar, y * scalar);
	}
	
	public Vec dot(Vec o) {
		return new Vec(x * o.x, y * o.y);
	}
	
	public double magSquared() {
		return x * x + y * y;
	}
	
	public double mag() {
		return Math.sqrt(magSquared());
	}

	public static Vec add(Vec v1, Vec v2) {
		return new Vec(v1.x + v2.x, v1.y + v2.y);
	}

	public static Vec mult(double c, Vec v1) {
		return new Vec(c * v1.x, c * v1.y);
	}
	public static double mag(Vec v1){
		return Math.sqrt(Math.pow(v1.x, 2)+Math.pow(v1.y, 2));
	}
	public static double dot(Vec v1, Vec v2){
		return v1.x*v2.x+v1.y+v2.y;
	}
	public static Vec proj(Vec v1, Vec v2){
		return Vec.mult(Vec.dot(v1, v2)/Vec.dot(v2,v2),v2);
	}
}
