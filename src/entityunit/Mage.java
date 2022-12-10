package entityunit;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Mage extends BasicEnemy{

	public Mage(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}
	

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BROWN);
		gc.fillOval(this.getX(), this.getY(), 30, 30);
		move();
		Main.shedule(2000, () -> spell());
	}
	
	public void spell() {
		Main.player.sleep();
		System.out.println("spell");
	}

}
