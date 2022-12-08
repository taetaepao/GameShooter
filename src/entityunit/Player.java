package entityunit;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Bullet;

public class Player extends Unit {
	public static List<Bullet> bullets = new ArrayList<>();
	private boolean shooting = false, damage = false;
	private int score = 0, lives = 3;
	private int hp = 100;
	private boolean flashing = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;
	
	public Player(int x, int y) {
		super(x, y);
		setAttack(y);
		setDefend(y);
		setSpeed(5);
		
	}
	
	public void hitByEnemy() {
		flashing = true;
		flashCounter = 10;
		flashDurationCounter = 10;
	}

	
	public void render(GraphicsContext gc){
		if (flashing) {
			if (flashCounter == 0) {
				gc.setFill(Color.RED);
				gc.fillOval(this.getX(), this.getY(), WIDTH, WIDTH);
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					if(flashCounter <= 5) {
						gc.setFill(Color.RED);
						gc.fillOval(this.getX(), this.getY(), WIDTH, WIDTH);
					}
					flashDurationCounter--;
				} else {
					gc.setFill(Color.RED);
					gc.fillOval(this.getX(), this.getY(), WIDTH, WIDTH);
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		}else {
		gc.setFill(Color.RED);
		gc.fillOval(this.getX(), this.getY(), WIDTH, WIDTH);
		}
		for (int i = 0; i < Player.bullets.size(); i++){
			Player.bullets.get(i).render(gc);
		}
	}
	
	public void move(int x, int y){
		if (this.getX()+x  >= 1 && this.getX()+x  <= 1160 && this.getY()+y  >= 1 && this.getY()+y <= 759) {
			this.setX(this.getX()+x);
			this.setY(this.getY()+y);
		}
	}
	
	public void shoot(double x, double y){
		if (shooting) return;
		shooting = true;
		Main.shedule(150, () -> this.shooting = false);
		double angle = Math.atan2(y-35-this.getY(), x-35-this.getX());
		Bullet b = new Bullet(angle, this.getX()+WIDTH/2, this.getY()+WIDTH/2);
		Player.bullets.add(b);
		
	}
	
	public void takeDamage(int dmg){
		if (damage) return;
		this.hp -= dmg;
		damage = true;
		Main.shedule(400, () -> damage = false);
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getLives() {
		return lives;
	}


	public void setLives(int lives) {
		this.lives = lives;
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
	
	
	
}
