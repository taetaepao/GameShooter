package entityunit;

import application.Main;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.EnemyBullet;

public class Ranger extends BasicEnemy{
	private boolean shooting = false;

	public Ranger(int x, int y) {
		super(x, y);
		setSpeed(2);
		setPicture("image/Mage.png");
	}

	public void render(GraphicsContext gc) {
		Image rotatedImage = picture(angle,Width);
		gc.drawImage(rotatedImage,this.getX()+NOTE, this.getY()+NOTE);
		move();
		shoot();
	}
	
	@Override
	public void move() {
		double distance = Math.sqrt(Math.pow(this.getX() - Main.player.getX(), 2) + Math.pow(this.getY() - Main.player.getY(), 2));
		if (distance > 350) 
			super.move();
	}
	
	public void shoot(){
		if (shooting) return;
		shooting = true;
		Main.shedule(1000, () -> this.shooting = false);
		double angle = Math.atan2(Main.player.getY()-this.getY(), Main.player.getX()-this.getX());
		EnemyBullet b = new EnemyBullet(angle, this.getX(), this.getY());
		BasicEnemy.bullets.add(b);
	}

}
