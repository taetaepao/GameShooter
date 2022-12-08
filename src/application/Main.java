package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

import java.util.*;

import entityunit.BasicEnemy;
import entityunit.Player;

public class Main extends Application {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private static final int SPEED = 6;
	public static Player player;
	Text scoreText, livesText;
	private Map<KeyCode, Boolean> keys = new HashMap<>();
	public static List<BasicEnemy> enemies = new ArrayList<>();

	@Override
	public void start(Stage stage) {
		stage.setTitle("Simple shooter game");

		StackPane pane = new StackPane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		pane.getChildren().add(canvas);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update(gc);
			}
		};
		timer.start();
		
		spawnEnemies(gc);

		canvas.setOnKeyPressed(e -> this.keys.put(e.getCode(), true));
		canvas.setOnKeyReleased(e -> this.keys.put(e.getCode(), false));
		canvas.setOnMousePressed(e -> this.player.shoot(e.getX(), e.getY()));
		canvas.setOnMouseDragged(e -> this.player.shoot(e.getX(), e.getY()));

		this.player = new Player(200, 200);
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	private void update(GraphicsContext gc) {
			gc.clearRect(0, 0, WIDTH, HEIGHT);
			gc.setFill(Color.LIGHTPINK);
			gc.fillRect(0, 0, WIDTH, HEIGHT);

			this.player.render(gc);

			if (this.keys.getOrDefault(KeyCode.W, false)) {
				this.player.move(0, -SPEED);
			}
			if (this.keys.getOrDefault(KeyCode.A, false)) {
				this.player.move(-SPEED, 0);
			}
			if (this.keys.getOrDefault(KeyCode.S, false)) {
				this.player.move(0, SPEED);
			}
			if (this.keys.getOrDefault(KeyCode.D, false)) {
				this.player.move(SPEED, 0);
			}
			for (BasicEnemy e : Main.enemies){
				for (int j = 0; j < Player.bullets.size(); j++){
					if (e.collided(Player.bullets.get(j).getX(), Player.bullets.get(j).getY(), 30 ,30)){
						Player.bullets.remove(j);
						enemies.remove(e);
						this.player.setScore(player.getScore()+1);
						break;
					}
				}
				e.move();
				e.render(gc);
			}
			gc.setFill(Color.GREEN);
			gc.fillRect(50, HEIGHT-80, 100*(this.player.getHp()/100.0), 30);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(50, HEIGHT-80, 100, 30);
			Font front = Font.font("Verdana",FontWeight.BOLD,15);
			gc.setFont(front);
			gc.setFill(Color.RED);
			gc.fillText( "HP",60, HEIGHT-60);
			gc.fillText( "Score: " + player.getScore(),50, HEIGHT-90);
	}

	public void spawnEnemies(GraphicsContext gc) {
		Thread spawner = new Thread(() -> {
			try {
				while (true) {
					int x = (int) (Math.random() * 760);
					int y = (int) (Math.random() * 559);
					Main.enemies.add(new BasicEnemy( x, y));
					Thread.sleep(1000);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		spawner.setDaemon(true);
		spawner.start();
	}

	public static void shedule(long time, Runnable r) {
		new Thread(() -> {
			try {
				Thread.sleep(time);
				r.run();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
	
}