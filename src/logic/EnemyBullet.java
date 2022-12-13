package logic;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class EnemyBullet extends Bullet {
	private final double SPEED =10;
	public static final double WIDTH = 10;
	private double dx,dy;
	private int NOTE = -24;
	private String Picture = "image/EnemyBullet.png";

	public EnemyBullet(double angle, double x, double y) {
		super(angle, x, y);
		this.dx = Math.cos(this.angle)*SPEED;
		this.dy = Math.sin(this.angle)*SPEED;
		super.picture(Picture);
	}

	
	public void render(GraphicsContext gc){
		gc.drawImage(rotatedImage, this.getX() + NOTE, this.getY() + NOTE);
		this.x += dx;
		this.y += dy;
	}
	
	

}
