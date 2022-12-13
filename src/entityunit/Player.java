package entityunit;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Bullet;

public class Player extends Unit {
	public static List<Bullet> bullets = new ArrayList<>();
	private boolean shooting = false, damage = false;
	private int score = 0;
	private int hp = 100;
	public static int bullet = 30;
	private boolean flashing = false, sleep = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;
	private int sleepTime = 0;
	private double angle;
	

	public Player(int x, int y) {
		super(x, y);
		setSpeed(10);
		setPicture("image/Player2.png");

	}

	public void hitByEnemy() {
		flashing = true;
		flashCounter = 10;
		flashDurationCounter = 10;
	}
	
	public void Checkmove(){
		if (Main.keys.getOrDefault(KeyCode.W, false)) {
			Main.player.move(0, -this.getSpeed());
		}
		if (Main.keys.getOrDefault(KeyCode.A, false)) {
			Main.player.move(-this.getSpeed(), 0);
		}
		if (Main.keys.getOrDefault(KeyCode.S, false)) {
			Main.player.move(0, this.getSpeed());
		}
		if (Main.keys.getOrDefault(KeyCode.D, false)) {
			Main.player.move(this.getSpeed(), 0);
		}
	}

	public void render(GraphicsContext gc) {
		Checkmove();
		Image rotatedImage = picture(angle,Width);
		
		for (int i = 0; i < Player.bullets.size(); i++) {
			Player.bullets.get(i).render(gc);
		}

		if (flashing) {
			if (flashCounter == 0) {
				gc.drawImage(rotatedImage,this.getX()+NOTE, this.getY()+NOTE);
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					if (flashCounter <= 5) {
						gc.drawImage(rotatedImage,this.getX()+NOTE, this.getY()+NOTE);
					}
					flashDurationCounter--;
				} else {
					gc.drawImage(rotatedImage,this.getX()+NOTE, this.getY()+NOTE);
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		} else {
			gc.drawImage(rotatedImage,this.getX()+NOTE, this.getY()+NOTE);
		}
		if(sleepTime>0) {
			Font front = Font.font("Verdana", FontWeight.BOLD, 15);
			gc.setFont(front);
			gc.setFill(Color.RED);
			gc.fillText("FREZZE" , this.getX()-10, this.getY()-20);
		}
	}

	public void move(int x, int y) {
		if (this.getX() + x >= 60 && this.getX() + x <= 1130 && this.getY() + y >= 60 && this.getY() + y <= 740&&sleepTime<0) {
			this.setX(this.getX() + x);
			this.setY(this.getY() + y);
		}
		sleepTime--;
		System.out.println(sleepTime);
	}

	public void shoot(double x, double y) {
		double angle = Math.atan2(y - 35 - this.getY(), x - 35 - this.getX());
		this.angle = angle;
		if (shooting)
			return;
		shooting = true;
		Bullet b = new Bullet(angle, this.getX() + SIZE / 2, this.getY() + SIZE / 2);
		Player.bullets.add(b);
		bullet--;
		if (bullet == 0) {
			Main.shedule(2000, () -> this.shooting = false);
			Main.shedule(2000, () -> bullet = 30);
		}else {
			Main.shedule(150, () -> this.shooting = false);
		}
	}

	public void takeDamage(int dmg) {
		if (damage)return;
		this.hp -= dmg;
		damage = true;
		Main.shedule(400, () -> damage = false);
	}

	public void sleep() {
//		this.sleep = true;
		sleepTime = 100;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isFlashing() {
		return flashing;
	}

	public void setFlashing(boolean flashing) {
		this.flashing = flashing;
	}

	public boolean isSleep() {
		return sleep;
	}

	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	
}
