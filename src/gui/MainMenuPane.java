package gui;
import java.awt.Canvas;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
import javafx.stage.Window;

public class MainMenuPane extends VBox {
	//properties
	private Button playButton;
	private Button exitButton;
	
	private Font fontForText = new Font(120);
	
	
	
	//constructor
	public MainMenuPane() {
		setAlignment(Pos.BOTTOM_CENTER);
		setPrefHeight(800);
		setPrefWidth(1200);
		setSpacing(50);
		setPadding(new Insets(40));
		initializeExitButton();
		initializePlayButton();
		this.getChildren().addAll(playButton,exitButton);
	}
	
	//initialize Button
	private void initializePlayButton() {
		playButton = new Button("PLAY");
		playButton.setFont(fontForText.font("Verdana",FontWeight.BOLD,50));
		playButton.setPrefHeight(150);
		playButton.setPrefWidth(400);
		playButton.setTextFill(Color.DARKRED);
		
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
		});        
	}
//	Main.primaryStage.setScene(scene2);
//	Main.primaryStage.setTitle("MainMenu");
//	Main.primaryStage.show();
//	Main.setPrimaryStage(Main.primaryStage.setScene(scene2));
//	Main.start(Main.getPrimaryStage());
	
	private void initializeExitButton() {
		exitButton = new Button("EXIT");
		exitButton.setPrefHeight(150);
		exitButton.setPrefWidth(400);
		exitButton.setFont(fontForText.font("Verdana",FontWeight.BOLD,50));
		exitButton.setTextFill(Color.DARKRED);
		exitButton.setOnAction(event -> Platform.exit());
	}
}
