package logic;

import entityunit.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet{
	protected double angle, x, y;
	private static double SPEED = 5;
	public static double WIDTH = 20;
	
	public Bullet(double angle, double x, double y){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public void render(GraphicsContext gc){
		gc.setFill(Color.GRAY);
		gc.fillOval(this.x, this.y, WIDTH, WIDTH);
		
		this.x += Math.cos(this.angle)*SPEED;
		this.y += Math.sin(this.angle)*SPEED;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public static double getSpeed() {
		return SPEED;
	}

	public static double getWidth() {
		return WIDTH;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}