package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.*;
import javafx.util.Duration;
import logic.GameLogic;
import javafx.scene.input.KeyCode;

import java.util.*;

import entityunit.BasicEnemy;
import entityunit.Mage;
import entityunit.Player;
import entityunit.Ranger;
import entityunit.Tank;
import entityunit.Turret;

public class Main extends Application {
	private static final int HEIGHT = 800;
	private static final int WIDTH = 1200;
	private static final int SPEED = 7;
	public static Player player;
	Text scoreText, livesText;
	public static  Map<KeyCode, Boolean> keys = new HashMap<>();
	public static List<BasicEnemy> enemies = new ArrayList<>();
	private int wavetime = 100, wave = 0;
	public static boolean Boss;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Simple shooter game");
		Main.player = new Player(200, 200);
		StackPane pane = new StackPane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		pane.getChildren().add(canvas);
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				update(gc);
			}
		};
		timer.start();

		AnimationTimer timer2 = new AnimationTimer() {
			public void handle(long now) {
				GameLogic.CheckBulletEnemy();
				GameLogic.CheckBulletPlayer();
			}
		};
		timer2.start();
//		spawner.join();

		GameLogic.spawnEnemies(gc);

		canvas.setOnKeyPressed(e -> Main.keys.put(e.getCode(), true));
		canvas.setOnKeyReleased(e -> Main.keys.put(e.getCode(), false));
		canvas.setOnMousePressed(e -> Main.player.shoot(e.getX(), e.getY()));
		canvas.setOnMouseDragged(e -> Main.player.shoot(e.getX(), e.getY()));

		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	private void update(GraphicsContext gc) {
		Image iv = new Image("image/Background2.png");
		gc.drawImage(iv, 0, 0);

		if (Player.bullet != 0)
			gc.fillText("Bullet: " + Player.bullet + " /30", 50, HEIGHT - 130);
		else
			gc.fillText("Bullet: Reloading...", 50, HEIGHT - 130);
		
		Main.player.render(gc);
		BasicEnemy.renderBullet(gc);
		for (BasicEnemy e : Main.enemies) {
			e.render(gc);
		}
		player.setSleepTime(player.getSleepTime() - 1);
		Image tree = new Image("image/Tree.png");
		gc.drawImage(tree, 0, 0);
		
		if (player.getScore() % 10 == 0) {
			wave = (player.getScore() / 10) + 1;
			this.wavetime = 100;
			waveshow(gc);
		}
		gc.setFill(Color.GREEN);
		gc.fillRect(50, HEIGHT - 80, 100 * (Main.player.getHp() / 100.0), 30);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(50, HEIGHT - 80, 100, 30);
		Font front = Font.font("Unispace", FontWeight.BOLD, 15);
		gc.setFont(front);
		gc.setFill(Color.RED);
		gc.fillText("HP", 60, HEIGHT - 60);
		gc.fillText("Score: " + player.getScore(), 50, HEIGHT - 90);
		gc.fillText("WAVE: " + wave, 50, HEIGHT - 110);
//		gc.fillRect(0, 320, 200, 430);

	}

	public void waveshow(GraphicsContext gc) {
		if (wavetime > 0) {
			Font front2 = Font.font("Unispace", FontWeight.BOLD, 100);
			gc.setFont(front2);
			gc.setFill(Color.WHITE);
			gc.fillText("WAVE " + wave, 350, HEIGHT / 2);
			wavetime--;
			Boss=false;
		}
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

	public static void main(String[] args) {
		launch(args);
	}

}