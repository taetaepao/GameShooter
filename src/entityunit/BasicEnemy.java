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
		setHp(1);
		setSpeed(4);
		setSIZE(40);
		setPicture("image/BasicEnemy.png");
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
		Image rotatedImage = picture(angle, Width);
		gc.drawImage(rotatedImage, this.getX() + NOTE, this.getY() + NOTE);
		move();
	}

	public static void renderBullet(GraphicsContext gc) {
		for (EnemyBullet j : BasicEnemy.bullets) {
			j.render(gc);
		}

	}

}
