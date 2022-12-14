package entityunit;

import application.Main;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Tank extends BasicEnemy{

	public Tank(int x, int y) {
		super(x, y);
		setHp(10);
		setSIZE(60);
		setPicture("image/Tank.png");
		setWidth(180);
		setSpeed(2);
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void render(GraphicsContext gc) {
		Image rotatedImage = picture(angle,Width);
//		gc.drawImage(rotatedImage,this.getX()+NOTE*3/2, this.getY()+NOTE*3/2);
		double realWidth = rotatedImage.getWidth();
		gc.drawImage(rotatedImage,this.getX()-realWidth/2-NOTE*2, this.getY()-realWidth/2-NOTE*2);
		gc.setFill(Color.RED);
		gc.fillRect(this.getX()+20,this.getY(), 60 * (this.getHp() / 10.0), 10);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(this.getX()+20, this.getY(), 60, 10);
		move();
	}
	
	public boolean checkCollision() {
		for (BasicEnemy e : Main.enemies) {
			if (e != this) {
				if (e.collided(this.getX(), this.getY(), 80, 80)) {
					return true;
				}
			}
		}
		return false;
	}


}
