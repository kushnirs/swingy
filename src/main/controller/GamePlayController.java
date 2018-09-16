package main.controller;

import main.Main;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GamePlayController {

    public static void initMap() {
        Main.map_size = (Main.hero.getLevel() - 1) * 5 + 10 - (Main.hero.getLevel() % 2);
        Main.map = new int[Main.map_size * Main.map_size];
    }

    public static void addEnemytoMap(int level) {
        int enemyAmount = (int)(Main.map.length * (level * 0.1 + 0.1));
        for (int i = 0; i < enemyAmount; i++)
            Main.map[(int)(Math.random() * enemyAmount)] = 1;
    }

    public static void move(int x, int y) {
        int pos = Main.hero.getX() + x + (Main.hero.getY() + y) * Main.map_size;
        if (pos > Main.map_size * Main.map_size)
            return;

        Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 2;

        Main.hero.setX(Main.hero.getX() + x);
        Main.hero.setY(Main.hero.getY() + y);
    }

    public static int checkCollision() {
        if (Main.map[Main.hero.getX() + Main.hero.getY() * Main.map_size] == 1)
            return 1;
        return 0;
    }
}
