package entityunit;

import application.Main;
import javafx.animation.RotateTransition;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public abstract class Unit {
	private double x, y;
	private int hp;
	private int speed;
	protected int SIZE,Width=100;
	protected static int NOTE=-15;
	private String Picture;
	
	public Unit(double x, double y){
		this.x = x;
		this.y = y;
	}
	
//	public abstract void move(int x, int y);
	public abstract void render(GraphicsContext gc);
	
	public boolean checkCollision() {
		for (BasicEnemy e : Main.enemies) {
			if (e != this) {
				if (e.collided(this.getX(), this.getY(), SIZE, SIZE)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean collided(double x, double y, double w1, double w2) {
		return Math.sqrt(Math.pow(this.getX() + w1 / 2 - x - w2 / 2, 2)+ Math.pow(this.getY() + w1 / 2 - y - w2 / 2, 2)) <= w1 / 2 + w2 / 2;
	}
	
	public Image picture(double angle,int Width) {
		ImageView imageView = new ImageView(this.Picture);
		imageView.setFitHeight(Width);
		imageView.setFitWidth(Width);
//		imageView.getTransforms().add(new Rotate(angle*60, 10, 10));
		imageView.setRotate(angle*60);
		// clear transforms
//		imageView.getTransforms().clear();
	    //rotation
	    // add rotation to the imageview
//	    imageView.getTransforms().add(new Rotate(angle*60,1000.0,1000.0));

        //Add the Rotate to the ImageView's Transforms
//        Rotate rotation = new Rotate();
//        rotation.setPivotX(this.x);//Set the Pivot's X to be the same location as the Circle's X. This is only used to help you see the Pivot's point
//        rotation.setPivotY(this.y);//Set the Pivot's Y to be the same location as the Circle's Y. This is only used to help you see the Pivot's point
//        rotation.setAngle(angle*60);
//        imageView.getTransforms().add(rotation);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		return imageView.snapshot(params, null);
	}

	
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int defend) {
		this.hp = defend;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int movespeed) {
		this.speed = movespeed;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public int getSIZE() {
		return SIZE;
	}

	public void setSIZE(int sIZE) {
		SIZE = sIZE;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}
	
	
	
	

	
	
}
