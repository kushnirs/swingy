package swingy.controller;

import swingy.model.characthers.*;
import swingy.Main;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GamePlayController {
    public static int map_size;
    public static int[] map = initMap();

    static int[] initMap() {
        map_size = (CharactherController.hero.getLevel() - 1) * 5 + 10 - (CharactherController.hero.getLevel() % 2);
        return new int[map_size * map_size];
    }

    static void addEnemytoMap(int level) {
        int enemyAmount = (int)(map.length * (level * 0.1 + 0.1));
        for (int enemy : map)
            enemy = 1;
    }

    static void move(int x, int y) {
        int pos = CharactherController.hero.getX() + x + (CharactherController.hero.getY() + y) * map_size;
        if (pos > map_size * map_size)
            return;

        map[CharactherController.hero.getX()+ CharactherController.hero.getY() * map_size] = 2;

        CharactherController.hero.setX(CharactherController.hero.getX() + x);
        CharactherController.hero.setY(CharactherController.hero.getY() + y);
    }

    static int checkCollision() {
        if (map[CharactherController.hero.getX() + CharactherController.hero.getY() * map_size] == 1)
            return 1;
        return 0;
    }
}
