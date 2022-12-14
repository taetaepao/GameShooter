package entityunit;

import gui.GameSence;
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
		GameSence.shedule(1000, () -> this.shooting = false);
		double angle = Math.atan2(GameSence.player.getY()-this.getY(), GameSence.player.getX()-this.getX());
		EnemyBullet b = new EnemyBullet(angle, this.getX(), this.getY());
		BasicEnemy.bullets.add(b);
	}

}
