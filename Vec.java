public class Vec{
	private double x,y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Vec(){
	}
	public Vec(Vec v){
		this.x=v.x;
		this.y=v.y;
	}
	public Vec(double x, double y){
		this.x=x;
		this.y=y;
	}
	public static Vec add(Vec v1,Vec v2){
		return new Vec(v1.x+v2.x,v1.y+v2.y);
	}
	public static Vec mult(double c, Vec v1){
		return new Vec(c*v1.x,c*v1.y);
	}
}
