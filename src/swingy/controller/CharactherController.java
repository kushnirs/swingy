package swingy.controller;

import swingy.model.characthers.*;
import swingy.util.CharactherFactory;

/**
 * Created by skushnir on 12.09.2018.
 */
public class CharactherController {
    public static Hero hero;

    static void fight(Hero hero, Characther enemy) {

    }

    static void run(Hero hero, Characther enemy) {
        double probability = Math.random();
        if (probability > 0.5)
            fight(hero, enemy);
    }

    static Characther newEnemy() {
        String[] enemy = {"Elf", "Orc", "Villain", "BlackMage"};
        int range[] = {0, 3};
        int i = range[0] + (int) (Math.random() * range[1]);
        return CharactherFactory.Factory(enemy[i]);
    }
}
