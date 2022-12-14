package logic;

import entityunit.BasicEnemy;
import entityunit.Mage;
import entityunit.Player;
import entityunit.Ranger;
import entityunit.Tank;
import entityunit.Turret;
import gui.GameSence;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;

public class GameLogic {
	private static AudioClip damageSound = new AudioClip(ClassLoader.getSystemResource("sound/damage.mp3").toString());
	
	public static void CheckBulletPlayer() {
		for (BasicEnemy e : GameSence.enemies) {
			for (int j = 0; j < Player.bullets.size(); j++) {
				if (e.collided(Player.bullets.get(j).getX(), Player.bullets.get(j).getY(), 30, 30)) {
					Player.bullets.remove(j);
					e.setHp(e.getHp() - 1);
					damageSound.play();
					if (e.getHp() == 0) {
						GameSence.enemies.remove(e);
						GameSence.player.setScore(GameSence.player.getScore() + 1);
					}
					break;
				}
				if (Player.bullets.get(j).getX() < 0 || Player.bullets.get(j).getX() > 1200)
					Player.bullets.remove(j);
				if (Player.bullets.get(j).getY() < 0 || Player.bullets.get(j).getY() > 800)
					Player.bullets.remove(j);
			}
		}
	}
	
	public static void CheckBulletEnemy() {
		for (int j =  BasicEnemy.bullets.size(); j < BasicEnemy.bullets.size(); j++) {
			if (GameSence.player.collided(BasicEnemy.bullets.get(j).getX(), BasicEnemy.bullets.get(j).getY(), 30, 30)) {
				if (!GameSence.player.isFlashing()) {
					GameSence.player.hitByEnemy();
					BasicEnemy.bullets.remove(j);
					GameSence.player.takeDamage(5);
				}
			}
			if (BasicEnemy.bullets.get(j).getX() < -40 || BasicEnemy.bullets.get(j).getX() > 1200)
				BasicEnemy.bullets.remove(j);
			if (BasicEnemy.bullets.get(j).getY() < -40 || BasicEnemy.bullets.get(j).getY() > 800)
				BasicEnemy.bullets.remove(j);
		}
	}
	
	public static void spawnEnemies(GraphicsContext gc) {
		Thread spawner = new Thread(() -> {
			try {
				while (true) {
					int x = (int) (Math.random() * 2);
					int y = (int) (Math.random() * 400);
					int z = (int) (Math.random() * 6);
					if (x==0)
						x = -30;
					if(x==1)
						x = 1230;
					if (GameSence.enemies.size() < 4) {
						if (z == 0)
							GameSence.enemies.add(new Mage(x, y+320));
						if (z == 1)
							GameSence.enemies.add(new BasicEnemy(x, y+320));
						if (z == 2)
							GameSence.enemies.add(new Ranger(x, y+320));
						if (z == 3)
							GameSence.enemies.add(new BasicEnemy(x, y+320));
						if (z == 4)
							GameSence.enemies.add(new Tank(x, y+320));
						if (z == 5)
							GameSence.enemies.add(new BasicEnemy(x, y+320));
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
}
