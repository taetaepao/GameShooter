package entityunit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gui.GameSence;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Bullet;

public class Player extends Unit {
	public static List<Bullet> bullets = new ArrayList<>();
	private int score = 0;
	private int hp = 100;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;
	private int sleepTime = 0;
	public static int bullet = 30;
	private double angle;
	private boolean flashing = false;
	private boolean shooting = false;
	private boolean damage = false;
	private AudioClip gunSound = new AudioClip(ClassLoader.getSystemResource("sound/gun.mp3").toString());
	private AudioClip reloadSound = new AudioClip(ClassLoader.getSystemResource("sound/reload.mp3").toString());
	

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
		if (GameSence.keys.getOrDefault(KeyCode.W, false)) {
			GameSence.player.move(0, -this.getSpeed());
		}
		if (GameSence.keys.getOrDefault(KeyCode.A, false)) {
			GameSence.player.move(-this.getSpeed(), 0);
		}
		if (GameSence.keys.getOrDefault(KeyCode.S, false)) {
			GameSence.player.move(0, this.getSpeed());
		}
		if (GameSence.keys.getOrDefault(KeyCode.D, false)) {
			GameSence.player.move(this.getSpeed(), 0);
		}
	}

	public void render(GraphicsContext gc) {
		Checkmove();
		Image rotatedImage = picture(angle,Width);
		double realWidth = rotatedImage.getWidth();
		
		for (int i = 0; i < Player.bullets.size(); i++) {
			Player.bullets.get(i).render(gc);
		}

		if (flashing) {
			if (flashCounter == 0) {
				gc.drawImage(rotatedImage,this.getX()-realWidth/2-NOTE, this.getY()-realWidth/2-NOTE);
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					if (flashCounter <= 5) {
						gc.drawImage(rotatedImage,this.getX()-realWidth/2-NOTE, this.getY()-realWidth/2-NOTE);
					}
					flashDurationCounter--;
				} else {
					gc.drawImage(rotatedImage,this.getX()-realWidth/2-NOTE, this.getY()-realWidth/2-NOTE);
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		} else {
			gc.drawImage(rotatedImage,this.getX()-realWidth/2-NOTE, this.getY()-realWidth/2-NOTE);
		}
		if(sleepTime>0) {
			Font front = Font.font("Verdana", FontWeight.BOLD, 15);
			gc.setFont(front);
			gc.setFill(Color.RED);
			gc.fillText("FREZZE" , this.getX()-10, this.getY()-20);
		}
		sleepTime--;
	}

	public void move(int x, int y) {
		if (this.getX() + x >= 60 && this.getX() + x <= 1130 && this.getY() + y >= 60 && this.getY() + y <= 740&&sleepTime<0) {
			this.setX(this.getX() + x);
			this.setY(this.getY() + y);
		}
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
			GameSence.shedule(2000, () -> this.shooting = false);
			GameSence.shedule(2000, () -> bullet = 30);
			reloadSound.play();
		}else {
			GameSence.shedule(150, () -> this.shooting = false);
		}
		gunSound.play();
	}

	public void takeDamage(int dmg) {
		if (damage)return;
		this.hp -= dmg;
		damage = true;
		GameSence.shedule(400, () -> damage = false);
	}

	public void sleep() {
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


	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	
}
