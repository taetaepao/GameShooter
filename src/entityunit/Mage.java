package entityunit;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Mage extends BasicEnemy{

	public Mage(int x, int y) {
		super(x, y);
		setPicture("image/Mage.png");
		Main.shedule(5000, () -> spell());
	}


	@Override
	public void move() {
		super.move();
	}
	

	@Override
	public void render(GraphicsContext gc) {
		Image rotatedImage = picture(angle,Width);
		gc.drawImage(rotatedImage,this.getX()+NOTE, this.getY()+NOTE);
		move();
	}
	
	public void spell() {
		Main.player.sleep();
		System.out.println("spell");
	}

}
