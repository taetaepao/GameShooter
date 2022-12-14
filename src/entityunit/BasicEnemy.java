package entityunit;

import java.util.ArrayList;
import java.util.List;

import gui.GameSence;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.EnemyBullet;

public class BasicEnemy extends Unit {
	public static List<EnemyBullet> bullets = new ArrayList<>();
	protected double angle;

	public BasicEnemy(int x, int y) {
		super(x, y);
		setHp(1);
		setSpeed(4);
		setSIZE(40);
		setPicture("image/BasicEnemy.png");
	}

	public void move() {
		double angle = Math.atan2(GameSence.player.getY() - this.getY(), GameSence.player.getX() - this.getX());
		this.angle = angle;
		double x = Math.cos(angle) * this.getSpeed();
		double y = Math.sin(angle) * this.getSpeed();
		double distance = Math
				.sqrt(Math.pow(this.getX() - GameSence.player.getX(), 2) + Math.pow(this.getY() - GameSence.player.getY(), 2));
		if (distance <= 50) {
			GameSence.player.takeDamage(5);
			if (!GameSence.player.isFlashing())
				GameSence.player.hitByEnemy();
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
		double realWidth = rotatedImage.getWidth();
		gc.drawImage(rotatedImage,this.getX()-realWidth/2, this.getY()-realWidth/2);
		move();
	}

	public static void renderBullet(GraphicsContext gc) {
		for (EnemyBullet j : BasicEnemy.bullets) {
			j.render(gc);
		}

	}

}
