package logic;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public interface Renderble {
	String Picture;
	
	public default Image picture(double angle,int Width) {
		ImageView imageView = new ImageView(this.Picture);
		imageView.setFitHeight(Width);
		imageView.setFitWidth(Width);
		imageView.setRotate(angle*60);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		return imageView.snapshot(params, null);
	}

}
