package entityunit;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Bullet;
import logic.EnemyBullet;


public class BasicEnemy extends Unit {
	public static List<EnemyBullet> bullets = new ArrayList<>();

	public BasicEnemy(int x, int y) {
		super(x, y);
		setAttack(y);
		setDefend(y);
		setSpeed(1);
	}

	public void move() {
		double angle = Math.atan2(Main.player.getY() - this.getY(), Main.player.getX() - this.getX());
		double x = Math.cos(angle) * this.getSpeed();
		double y = Math.sin(angle) * this.getSpeed();
		double distance = Math.sqrt(Math.pow(this.getX() - Main.player.getX(), 2) + Math.pow(this.getY() - Main.player.getY(), 2));
		if (distance <= 50) {
			Main.player.takeDamage(5);
			if(!Main.player.isFlashing())
				Main.player.hitByEnemy();
		}
		this.setX(this.getX() + x);
		if (checkCollision()) {
			this.setX(this.getX() - x);
		}
		this.setY(this.getY() + y);
		if (checkCollision()) {
			this.setY(this.getY() - y);
		}
	}

	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillOval(this.getX(), this.getY(), 30, 30);
		for (int i = 0; i < this.bullets.size(); i++){
			this.bullets.get(i).render(gc);
		}
	}

}
