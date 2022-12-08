package entityunit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import entityunit.Player;

public class BasicEnemy extends Unit {

	public BasicEnemy(int x, int y) {
		super(x, y);
		setAttack(y);
		setDefend(y);
		setSpeed(2);
	}

	public boolean checkCollision() {
		for (BasicEnemy e : Main.enemies) {
			if (e != this) {
				if (e.collided(this.getX(), this.getY(), 30, 30)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean collided(double x, double y, double w1, double w2) {
		// Check if the distance between the center of the the 2 enemies is less than
		// the total width (diameter)
		return Math.sqrt(Math.pow(this.getX() + w1 / 2 - x - w2 / 2, 2)
				+ Math.pow(this.getY() + w1 / 2 - y - w2 / 2, 2)) <= w1 / 2 + w2 / 2;
	}

	public void move() {
		double angle = Math.atan2(Main.player.getY() - this.getY(), Main.player.getX() - this.getX());
		double x = Math.cos(angle) * this.getSpeed();
		double y = Math.sin(angle) * this.getSpeed();
		double distance = Math
				.sqrt(Math.pow(this.getX() - Main.player.getX(), 2) + Math.pow(this.getY() - Main.player.getY(), 2));
		if (distance <= 60)
			Main.player.takeDamage(5);
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
	}

}
