package logic;

import entityunit.Player;
import entityunit.Unit;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bullet{
	protected double angle, x, y;
	private static double SPEED = 10;
	public static double WIDTH = 20;
	private int NOTE = -18;
	protected Image rotatedImage;
	private String Picture = "image/Bullet.png";
	
	public Bullet(double angle, double x, double y){
		this.x = x;
		this.y = y;
		this.angle = angle;
		picture(Picture);
	}
	
	public void picture(String Picture) {
		ImageView imageView = new ImageView(Picture);
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		imageView.setRotate(angle*60);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		this.rotatedImage =  imageView.snapshot(params, null);
	}
	
	public void render(GraphicsContext gc){
		gc.drawImage(rotatedImage, this.getX() + NOTE, this.getY() + NOTE);
		this.x += Math.cos(this.angle)*SPEED;
		this.y += Math.sin(this.angle)*SPEED;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public static double getSpeed() {
		return SPEED;
	}

	public static double getWidth() {
		return WIDTH;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}