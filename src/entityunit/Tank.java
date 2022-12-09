package entityunit;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tank extends BasicEnemy{

	public Tank(int x, int y) {
		super(x, y);
		setHp(10);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillOval(this.getX(), this.getY(), 60, 60);
		move();
	}
	
	public boolean checkCollision() {
		for (BasicEnemy e : Main.enemies) {
			if (e != this) {
				if (e.collided(this.getX(), this.getY(), 60, 60)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean collided(double x, double y, double w1, double w2) {
		return Math.sqrt(Math.pow(this.getX() + w1 / 2 - x - w2 / 2, 2)
				+ Math.pow(this.getY() + w1 / 2 - y - w2 / 2, 2)) <= w1 / 2 + w2 / 2;
	}

}
