package application;
import gui.GameSence;
import gui.MainMenuPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage primaryStage;
	
	
	public void start(Stage primaryStage) {
		Image bgImage = new Image("image/Main.png");
		Canvas canvas = new Canvas(1200,800);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(bgImage, 0, 0);
		
		StackPane root = new StackPane();
		root.setPrefHeight(800);
		root.setPrefWidth(1200);
		root.setPadding(new Insets(10));
		
		
		GameSence GameSence = new GameSence();
		root.getChildren().addAll(GameSence);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MainMenu");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage scene) {
		Main.primaryStage = scene;
	}
	
	public void run() {
		primaryStage.show();
	}
}
