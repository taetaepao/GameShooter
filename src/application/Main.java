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
import entityunit.Turret;

public class Main extends Application {
	private static final int HEIGHT = 800;
	private static final int WIDTH = 1200;
	private static final int SPEED = 3;
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
		Date time = new Date();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				long z = time.getTime();
				update(gc);
				long q = time.getTime();
			System.out.println(q-z);
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
		for (BasicEnemy e : Main.enemies) {
			for (int j = 0; j < Player.bullets.size(); j++) {
				if (e.collided(Player.bullets.get(j).getX(), Player.bullets.get(j).getY(), 30, 30)) {
					Player.bullets.remove(j);
					enemies.remove(e);
					this.player.setScore(player.getScore() + 1);
					break;
				}
				if (Player.bullets.get(j).getX() < 0 || Player.bullets.get(j).getX() > 1000)
					Player.bullets.remove(j);
				if (Player.bullets.get(j).getY() < 0 || Player.bullets.get(j).getY() > 2000)
					Player.bullets.remove(j);

			}
			e.move();
			e.render(gc);
		}
			for (int j = 0; j < BasicEnemy.bullets.size(); j++) {
				if (Main.player.collided(BasicEnemy.bullets.get(j).getX(), BasicEnemy.bullets.get(j).getY(), 30, 30)) {
					BasicEnemy.bullets.remove(j);
					Main.player.takeDamage(5);
					if (!Main.player.isFlashing())
						Main.player.hitByEnemy();
				}
				if (BasicEnemy.bullets.get(j).getX() < 0 || BasicEnemy.bullets.get(j).getX() > 1000)
					BasicEnemy.bullets.remove(j);
				if (BasicEnemy.bullets.get(j).getY() < 0 || BasicEnemy.bullets.get(j).getY() > 2000)
					BasicEnemy.bullets.remove(j);
			}
		gc.setFill(Color.GREEN);
		gc.fillRect(50, HEIGHT - 80, 100 * (this.player.getHp() / 100.0), 30);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(50, HEIGHT - 80, 100, 30);
		Font front = Font.font("Verdana", FontWeight.BOLD, 15);
		gc.setFont(front);
		gc.setFill(Color.RED);
		gc.fillText("HP", 60, HEIGHT - 60);
		gc.fillText("Score: " + player.getScore(), 50, HEIGHT - 90);
	}

	public void spawnEnemies(GraphicsContext gc) {
		Thread spawner = new Thread(() -> {
			try {
				while (true) {
					int x = (int) (Math.random() * 760);
					int y = (int) (Math.random() * 559);
					int z = (int) (Math.random() * 2);
					if (z == 0)
						Main.enemies.add(new BasicEnemy(x, y));
					if (z == 1)
						Main.enemies.add(new Turret(x, y));
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