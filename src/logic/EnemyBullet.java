package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyBullet extends Bullet {
	private final double SPEED = 3;
	public static final double WIDTH = 10;
	private double dx,dy;

	public EnemyBullet(double angle, double x, double y) {
		super(angle, x, y);
		this.dx = Math.cos(this.angle)*SPEED;
		this.dy = Math.sin(this.angle)*SPEED;
	}

	public void render(GraphicsContext gc){
		gc.setFill(Color.YELLOW);
		gc.fillOval(this.x, this.y, WIDTH, WIDTH);
		this.x += dx;
		this.y += dy;
	}
	
	

}
