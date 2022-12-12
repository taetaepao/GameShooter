package logic;

import application.Main;
import entityunit.BasicEnemy;
import entityunit.Player;

public class GameLogic {
	
	public static void CheckBulletPlayer() {
		for (BasicEnemy e : Main.enemies) {
			for (int j = 0; j < Player.bullets.size(); j++) {
				if (e.collided(Player.bullets.get(j).getX(), Player.bullets.get(j).getY(), 30, 30)) {
					Player.bullets.remove(j);
					e.setHp(e.getHp() - 1);
					if (e.getHp() == 0) {
						Main.enemies.remove(e);
						Main.player.setScore(Main.player.getScore() + 1);
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
		for (int j = 0; j < BasicEnemy.bullets.size(); j++) {
			if (Main.player.collided(BasicEnemy.bullets.get(j).getX(), BasicEnemy.bullets.get(j).getY(), 30, 30)) {
				if (!Main.player.isFlashing()) {
					Main.player.hitByEnemy();
					BasicEnemy.bullets.remove(j);
					Main.player.takeDamage(5);
				}
			}
			if (BasicEnemy.bullets.get(j).getX() < 0 || BasicEnemy.bullets.get(j).getX() > 1200)
				BasicEnemy.bullets.remove(j);
			if (BasicEnemy.bullets.get(j).getY() < 0 || BasicEnemy.bullets.get(j).getY() > 800)
				BasicEnemy.bullets.remove(j);
		}
	}
}
