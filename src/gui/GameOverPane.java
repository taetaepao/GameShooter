package gui;
import application.Main;
import entityunit.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverPane extends VBox {
	//properties
	private Text gameOverText;
	private Text scoreText;
	private Button playAgainButton;
	private Button mainMenuButton;
	private Font fontForText = new Font(120);
	
	//constructor
	public GameOverPane() {
		setAlignment(Pos.CENTER);
		setPrefHeight(800);
		setPrefWidth(1200);
		setSpacing(50);
		setPadding(new Insets(40));
		initializeGameOverText();
		initializeScoreText();
		initializePlayAgainButton();
		initializeMainMenuButton();
		this.getChildren().addAll(gameOverText,scoreText,playAgainButton,mainMenuButton);
	}
	
	//initialize Text
	private void initializeGameOverText() {
		gameOverText = new Text("GAME OVER!");
		gameOverText.setFont(fontForText.font("Verdana", FontWeight.BOLD, 120));
		gameOverText.setFill(Color.DARKRED);
	}
	
	private void initializeScoreText() {
		scoreText = new Text("score = " +Player.getScore());
		scoreText.setFont(fontForText.font("Verdana",FontWeight.BOLD,100));
		scoreText.setFill(Color.DARKRED);
	}
	
	private void initializePlayAgainButton() {
		playAgainButton = new Button("PLAY AGAIN");
		playAgainButton.setFont(fontForText.font("Verdana",FontWeight.BOLD,40));
		playAgainButton.setPrefHeight(150);
		playAgainButton.setPrefWidth(400);
		playAgainButton.setTextFill(Color.DARKRED);
		playAgainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			    Main.Game();
			}
		}); 
	}
	
	private void initializeMainMenuButton() {
		mainMenuButton = new Button("MAIN MENU");
		mainMenuButton.setPrefHeight(150);
		mainMenuButton.setPrefWidth(400);
		mainMenuButton.setFont(fontForText.font("Verdana",FontWeight.BOLD,40));
		mainMenuButton.setTextFill(Color.DARKRED);
		mainMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			    Main.MainMenu();
			}
		}); 
	}
	
	//initialize Button
	
}
