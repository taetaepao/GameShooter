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
	public static Stage primaryStage;
	
	
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = initializeBackground("image/Main.png");
		
		StackPane root = new StackPane();
		root.setPrefHeight(800);
		root.setPrefWidth(1200);
		root.setPadding(new Insets(8));
		
		GameSence GameSence = new GameSence();
		MainMenuPane mainMenuPane = new MainMenuPane();
		root.getChildren().addAll(canvas,mainMenuPane);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MainMenu");
		primaryStage.show();
		this.primaryStage = primaryStage;
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
	
	public static void Game() {
		StackPane root = new StackPane();
	    root.setPrefHeight(800);
	    root.setPrefWidth(1200);
	    root.setPadding(new Insets(8));
	    
	    GameSence gameSence = new GameSence();
	    root.getChildren().add(gameSence);
	    Scene scene = new Scene(root);
	    Stage stage = Main.primaryStage;
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void MainMenu() {
		Canvas canvas = initializeBackground("image/Main.png");
		StackPane root = new StackPane();
	    root.setPrefHeight(800);
	    root.setPrefWidth(1200);
	    root.setPadding(new Insets(8));
	    
	    MainMenuPane mainMenuPane = new MainMenuPane();
	    root.getChildren().addAll(canvas,mainMenuPane);
	    Scene scene = new Scene(root);
	    Stage stage = Main.primaryStage;
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void GameOver() {
		Canvas canvas = initializeBackground("image/GameOver.png");
		StackPane root = new StackPane();
	    root.setPrefHeight(800);
	    root.setPrefWidth(1200);
	    root.setPadding(new Insets(8));
	    
	    GameOverPane gameOverPane = new GameOverPane();
	    root.getChildren().addAll(canvas,gameOverPane);
	    Scene scene = new Scene(root);
	    Stage stage = Main.primaryStage;
	    stage.setScene(scene);
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
