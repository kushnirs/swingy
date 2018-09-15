package swingy.controller;

import swingy.model.characthers.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GamePlayController {

    static int[] initMap(Hero hero) {
        int size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
        return new int[size * size];
    }

    static void addEnemytoMap(int[] map, int level) {
        int enemyAmount = (int)(map.length * (level * 0.1 + 0.1));
        for (int enemy : map)
            enemy = 1;
    }

    static void move(int xsize, Hero hero, int x, int y) {
        int pos = hero.getX() + x + (hero.getY() + y) * xsize;
        if (pos > xsize)
            return;
       hero.setX(hero.getX() + x);
       hero.setY(hero.getY() + y);
    }

    static int checkCollision(int[] map, int xsize, Hero hero) {
        if (map[hero.getX() + hero.getY() * xsize] == 1)
            return 1;
        return 0;
    }
}
