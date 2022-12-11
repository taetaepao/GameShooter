package entityunit;

import application.Main;

public abstract class Unit {
	private double x, y;
	private int hp;
	private int speed;
	private int attack;
	protected static int SIZE=30;
	protected static int NOTE=-35;
	
	public Unit(double x, double y){
		this.x = x;
		this.y = y;
	}
	
//	public abstract void move(int x, int y);
	
	public boolean checkCollision() {
		for (BasicEnemy e : Main.enemies) {
			if (e != this) {
				if (e.collided(this.getX(), this.getY(), SIZE, SIZE)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean collided(double x, double y, double w1, double w2) {
		return Math.sqrt(Math.pow(this.getX() + w1 / 2 - x - w2 / 2, 2)+ Math.pow(this.getY() + w1 / 2 - y - w2 / 2, 2)) <= w1 / 2 + w2 / 2;
	}

	
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
	public int getHp() {
		return hp;
	}
	public void setHp(int defend) {
		this.hp = defend;
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

	public static int getSIZE() {
		return SIZE;
	}

	public static void setSIZE(int sIZE) {
		SIZE = sIZE;
	}
	
}
