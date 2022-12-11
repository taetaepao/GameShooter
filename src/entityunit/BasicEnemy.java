package entityunit;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.EnemyBullet;

public class BasicEnemy extends Unit {
	public static List<EnemyBullet> bullets = new ArrayList<>();
//	private double angle = Math.atan2(Main.player.getY() - this.getY(), Main.player.getX() - this.getX());
	protected double angle;

	public BasicEnemy(int x, int y) {
		super(x, y);
		setAttack(y);
		setHp(1);
		setSpeed(1);
		setSIZE(40);
	}

	public void move() {
		double angle = Math.atan2(Main.player.getY() - this.getY(), Main.player.getX() - this.getX());
		this.angle = angle;
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
		ImageView imageView = new ImageView("image/BasicEnemy.png");
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.setRotate(angle * 60);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage = imageView.snapshot(params, null);
		gc.drawImage(rotatedImage, this.getX() + NOTE, this.getY() + NOTE);
		move();
	}

	public static void renderBullet(GraphicsContext gc) {
		for (EnemyBullet j : BasicEnemy.bullets) {
			j.render(gc);
		}
	}

	public static void CheckBulletEnemy() {
//		for (EnemyBullet j : BasicEnemy.bullets) {
//			if (j.getX() < 0 || j.getX() > 1160) {
//				BasicEnemy.bullets.remove(j);
//				break;
//			}
//			if (j.getY() < 0 || j.getY() > 759) {
//				BasicEnemy.bullets.remove(j);
//				break;
//			}
//			if (Main.player.collided(j.getX(), j.getY(), SIZE, SIZE)) {
//				System.out.println("take damage");
//				Main.player.takeDamage(5);
//				BasicEnemy.bullets.remove(j);
//			}
//				if (!Main.player.isFlashing()) {
//					Main.player.takeDamage(5);
//					Main.player.hitByEnemy();
//				}
//		}
		for (int j = 0; j < BasicEnemy.bullets.size(); j++) {
			if (Main.player.collided(BasicEnemy.bullets.get(j).getX(), BasicEnemy.bullets.get(j).getY(), 30, 30)) {
				if (!Main.player.isFlashing()) {
					Main.player.hitByEnemy();
					BasicEnemy.bullets.remove(j);
					Main.player.takeDamage(5);
				}
			}
			if (BasicEnemy.bullets.get(j).getX() < 0 || BasicEnemy.bullets.get(j).getX() > 1200)
				BasicEnemy.bullets.remove(j);
			if (BasicEnemy.bullets.get(j).getY() < 0 || BasicEnemy.bullets.get(j).getY() > 800)
				BasicEnemy.bullets.remove(j);
		}
	}

}
