package application;
import gui.GameOverPane;
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
	public static Stage stage;
	public static Scene scene1, scene2, scene3;
	
	
	public void start(Stage stage) throws Exception {
		Canvas canvas = initializeBackground("image/Main.png");
		
		StackPane root = new StackPane();
		root.setPrefHeight(800);
		root.setPrefWidth(1200);
		root.setPadding(new Insets(8));
		
		
		MainMenuPane mainMenuPane = new MainMenuPane();
		root.getChildren().addAll(canvas,mainMenuPane);
		Scene scene1 = new Scene(root);
		
		StackPane root2 = new StackPane();
	    root2.setPrefHeight(800);
	    root2.setPrefWidth(1200);
	    root2.setPadding(new Insets(8));
	    
	    GameSence gameSence = new GameSence();
	    root2.getChildren().add(gameSence);
	    Scene scene2 = new Scene(root2);
	    
	    
	    Canvas canvas2 = initializeBackground("image/GameOver.png");
		StackPane root3 = new StackPane();
	    root3.setPrefHeight(800);
	    root3.setPrefWidth(1200);
	    root3.setPadding(new Insets(8));
	    
	    GameOverPane GameOver = new GameOverPane();
	    root3.getChildren().addAll(canvas2,GameOver);
	    Scene scene3 = new Scene(root3);
	    stage.setScene(scene1);
		stage.setTitle("MainMenu");
		stage.show();
		Main.stage = stage;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

//	public static Stage getPrimaryStage() {
//		return Stage;
//	}
//
//	public static void setPrimaryStage(Stage scene) {
//		stage = scene;
//	}
	
	public static void Game() {
		scene1.;
		stage.setScene(scene2);
		
	}
	
	public static void MainMenu() {
		stage.setScene(scene1);
		stage.show();
	}
	
	public static void GameOver() {
		stage.setScene(scene3);
		stage.show();
	}
	
	private static  Canvas initializeBackground(String image) {
		Image bgImage = new Image(image);
		Canvas canvas = new Canvas(1200,800);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(bgImage, 0, 0);
		return canvas;
	}
}
