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
		setWidth(200);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}

	@Override
	public void render(GraphicsContext gc) {
		Image rotatedImage = picture(angle,Width);
		gc.drawImage(rotatedImage,this.getX()+NOTE*3/2, this.getY()+NOTE*3/2);
		move();
	}
	
	public boolean checkCollision() {
		for (BasicEnemy e : Main.enemies) {
			if (e != this) {
				if (e.collided(this.getX(), this.getY(), 60, 60)) {
					return true;
				}
			}
		}
		return false;
	}


}
