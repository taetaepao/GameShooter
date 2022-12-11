package entityunit;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Mage extends BasicEnemy{

	public Mage(int x, int y) {
		super(x, y);
	}


	@Override
	public void move() {
		super.move();
	}
	

	@Override
	public void render(GraphicsContext gc) {
		Image image = new Image("image/Mage.png", 100, 100, false, false);
		gc.drawImage(image,this.getX(), this.getY());
		move();
		Main.shedule(2000, () -> spell());
	}
	
	public void spell() {
		Main.player.sleep();
		System.out.println("spell");
	}

}
