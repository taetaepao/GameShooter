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
		setHp(1);
		setSpeed(1);
	}

	public void move() {
		double angle = Math.atan2(Main.player.getY() - this.getY(), Main.player.getX() - this.getX());
		double x = Math.cos(angle) * this.getSpeed();
		double y = Math.sin(angle) * this.getSpeed();
		double distance = Math
				.sqrt(Math.pow(this.getX() - Main.player.getX(), 2) + Math.pow(this.getY() - Main.player.getY(), 2));
		if (distance <= 50) {
			Main.player.takeDamage(5);
			if (!Main.player.isFlashing())
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
		move();
	}
	
	public void renderBullet(GraphicsContext gc) {
		for (int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).render(gc);
		}
	}

	public void CheckBulletEnemy() {
		for (int j = 0; j < BasicEnemy.bullets.size(); j++) {
			if (Main.player.collided(BasicEnemy.bullets.get(j).getX(), BasicEnemy.bullets.get(j).getY(), 30, 30)) {
				BasicEnemy.bullets.remove(j);
				if (!Main.player.isFlashing()) {
					Main.player.takeDamage(5);
					Main.player.hitByEnemy();
				}
			}
			if (BasicEnemy.bullets.get(j).getX() < 0 || BasicEnemy.bullets.get(j).getX() > 1160)
				BasicEnemy.bullets.remove(j);
			if (BasicEnemy.bullets.get(j).getY() < 0 || BasicEnemy.bullets.get(j).getY() > 759)
				BasicEnemy.bullets.remove(j);
		}
	}

}
