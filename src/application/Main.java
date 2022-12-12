package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
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
	private static final int SPEED = 3;
	public static Player player;
	Text scoreText, livesText;
	private Map<KeyCode, Boolean> keys = new HashMap<>();
	public static List<BasicEnemy> enemies = new ArrayList<>();
	private int wavetime = 100, wave = 0, cooldown = 0;
	private boolean use = true;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Simple shooter game");
		Main.player = new Player(200, 200);
		StackPane pane = new StackPane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		pane.getChildren().add(canvas);
		Date time = new Date();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				long z = time.getTime();
				update(gc);
				long q = time.getTime();
//				System.out.println(q - z);
			}
		};
		timer.start();

		AnimationTimer timer2 = new AnimationTimer() {
			@Override
			public void handle(long now) {
				BasicEnemy.renderBullet(gc);
				for (BasicEnemy e : Main.enemies) {
					e.render(gc);
				}
				GameLogic.CheckBulletEnemy();
			}
		};
		timer2.start();

		spawnEnemies(gc);

		canvas.setOnKeyPressed(e -> this.keys.put(e.getCode(), true));
		canvas.setOnKeyReleased(e -> this.keys.put(e.getCode(), false));
		canvas.setOnMousePressed(e -> Main.player.shoot(e.getX(), e.getY()));
		canvas.setOnMouseDragged(e -> Main.player.shoot(e.getX(), e.getY()));

		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	private void update(GraphicsContext gc) {
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(Color.LIGHTPINK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);

		Main.player.render(gc);

		if (this.keys.getOrDefault(KeyCode.W, false)) {
			Main.player.move(0, -SPEED);
		}
		if (this.keys.getOrDefault(KeyCode.A, false)) {
			Main.player.move(-SPEED, 0);
		}
		if (this.keys.getOrDefault(KeyCode.S, false)) {
			Main.player.move(0, SPEED);
		}
		if (this.keys.getOrDefault(KeyCode.D, false)) {
			Main.player.move(SPEED, 0);
		}

		GameLogic.CheckBulletPlayer();
		gc.setFill(Color.GREEN);
		gc.fillRect(50, HEIGHT - 80, 100 * (Main.player.getHp() / 100.0), 30);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(50, HEIGHT - 80, 100, 30);
		Font front = Font.font("Verdana", FontWeight.BOLD, 15);
		gc.setFont(front);
		gc.setFill(Color.RED);
		gc.fillText("HP", 60, HEIGHT - 60);
		gc.fillText("Score: " + player.getScore(), 50, HEIGHT - 90);
		gc.fillText("WAVE: " + wave, 50, HEIGHT - 110);
		if (Player.bullet != 0)
			gc.fillText("Bullet: " + Player.bullet + " /30", 50, HEIGHT - 130);
		else
			gc.fillText("Bullet: Reloading...", 50, HEIGHT - 130);

		if (player.getScore() % 10 == 0) {
			wave = (player.getScore() / 10) + 1;
			this.wavetime = 100;
			waveshow(gc);
			use = true;
		}
		
		player.setSleepTime(player.getSleepTime()-1);

	}

	public void waveshow(GraphicsContext gc) {
		if (wavetime > 0) {
			use = true;
			Font front2 = Font.font("Verdana", FontWeight.BOLD, 100);
			gc.setFont(front2);
			gc.fillText("WAVE " + wave, 350, HEIGHT / 2);
			wavetime--;
		}
	}

	public void spawnEnemies(GraphicsContext gc) {
		Thread spawner = new Thread(() -> {
			try {
				while (true) {
						int x = (int) (Math.random() * 1150);
						int y = (int) (Math.random() * 800);
						int z = (int) (Math.random() * 4);
						if (Main.enemies.size() < 5) {
							if (z == 0)
								Main.enemies.add(new Mage(x, y));
							if (z == 1)
								Main.enemies.add(new Turret(x, y));
							if (z == 2)
								Main.enemies.add(new Ranger(x, y));
							if (z == 3)
								Main.enemies.add(new Tank(x, y));
						}
						Thread.sleep(1500);
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

	public static void main(String[] args) {
		launch(args);
	}

}