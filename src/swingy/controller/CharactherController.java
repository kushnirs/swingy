package swingy.controller;

import swingy.Main;
import swingy.model.characthers.*;
import swingy.util.CharactherFactory;

/**
 * Created by skushnir on 12.09.2018.
 */
public class CharactherController {

    public static int fight(Hero hero, Characther enemy) {
        return 1;
    }

    public static int run(Hero hero, Characther enemy) {
        double probability = Math.random();
        if (probability > 0.5)
            return fight(hero, enemy);
        else
            return 2;
    }

    public static Characther newEnemy() {
        String[] enemy = {"Elf", "Orc", "Villain", "BlackMage"};
        int range[] = {0, 3};
        int i = range[0] + (int) (Math.random() * range[1]);
        return CharactherFactory.Factory(enemy[i]);
    }

    public static void initHeroPosition(Hero hero) {
        hero.setX(Main.map_size / 2);
        hero.setY(Main.map_size / 2);
    }

}
