package entityunit;


public abstract class Unit {
	private double x, y;
	private int defend;
	private int speed;
	private int attack;
	protected static int WIDTH=40;
	
	public Unit(double x, double y){
		this.x = x;
		this.y = y;
	}
	
//	public abstract void move(int x, int y);
	
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getDefend() {
		return defend;
	}
	public void setDefend(int defend) {
		this.defend = defend;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int movespeed) {
		this.speed = movespeed;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
}
