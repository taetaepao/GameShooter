package entityunit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bomber extends BasicEnemy{

	public Bomber(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BROWN);
		gc.fillOval(this.getX(), this.getY(), 30, 30);
		move();
	}

}
