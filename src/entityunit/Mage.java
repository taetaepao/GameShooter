package entityunit;

import gui.GameSence;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Mage extends BasicEnemy{
	private int SpellTime;

	public Mage(int x, int y) {
		super(x, y);
		setPicture("image/Mage.png");
		this.SpellTime = 0;
	}


	@Override
	public void move() {
		super.move();
	}
	

	@Override
	public void render(GraphicsContext gc) {
		Image rotatedImage = picture(angle,Width);
		double realWidth = rotatedImage.getWidth();
		gc.drawImage(rotatedImage,this.getX()-realWidth/2-NOTE, this.getY()-realWidth/2-NOTE);
		move();
		this.SpellTime++;
		if(this.SpellTime == 150) {
			spell();
			this.SpellTime = 0;
		}
		gc.setFill(Color.PINK);
		gc.fillRect(this.getX()-15,this.getY()-25, 60 * (this.SpellTime / 150.0), 5);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(this.getX()-15, this.getY()-25, 60, 5);
	}
	
	public void spell() {
		GameSence.player.sleep();
		System.out.println("spell");
	}

}
