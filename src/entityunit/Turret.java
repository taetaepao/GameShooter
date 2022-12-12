package entityunit;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.EnemyBullet;

public class Turret extends BasicEnemy{
	private boolean shooting = false;

	public Turret(int x, int y) {
		super(x, y);
		setSpeed(1);
	}

	public void render(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillOval(this.getX(), this.getY(), SIZE, SIZE);
		shoot();
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
