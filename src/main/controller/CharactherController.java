package main.controller;

import main.Main;
import main.model.characthers.*;
import main.util.CharactherFactory;

/**
 * Created by skushnir on 12.09.2018.
 */
public class CharactherController {

    public static int fight(Hero hero, Characther enemy) {
        StringBuffer log = new StringBuffer("");

        int enemyHp = enemy.getHp(), heroHp = hero.getHp();
        int enemyA = enemy.getAttack(), heroA = hero.getAttack();
        int enemyD = enemy.getDefense(), heroD = hero.getDefense();
        String weaponN = "", helmN = "", armorN = "";

        int art;
        if (hero.getWeapon() != null) {
            weaponN = " using " + hero.getWeapon().getName();
            art = hero.getWeapon().getQuality() * hero.getWeapon().getValue() / 100;
            log.append("Artifact " + hero.getWeapon().getName() + "increases the attack +" + art + "\n");
            heroA += art;
        }
        if (hero.getArmor() != null) {
            armorN = " using " + hero.getArmor().getName();
            art = hero.getArmor().getQuality() * hero.getArmor().getValue() / 100;
            log.append("Artifact " + hero.getArmor().getName() + "increases defense +" + art + "\n");
            heroD += art;
        }
        if (hero.getHelm() != null) {
            helmN = " in " + hero.getHelm().getName();
            art = hero.getHelm().getQuality() * hero.getHelm().getValue() / 100;
            log.append("Artifact " + hero.getHelm().getName() + "increases defense +" + art + "\n");
            heroHp += art;
        }


        while (heroHp > 0 && enemyHp > 0)
        {
            log.append("\u001B[36m" + hero.getName() + "HP: " + heroHp + "\n" + enemy.getName() + "HP: " + enemyHp + "\n" + "\u001B[0m");
            enemyHp -= heroA - enemyD;
            log.append("\u001B[31m" + hero.getName() + " attack " + enemy.getName() + weaponN + " and decrease " + (heroA - enemyD) + "hp\n" + "\u001B[0m");
            if (enemyHp <= 0)
            {
                int exp = 500 * (enemy.getLevel() / 10 + 1);
                log.append(enemy.getName() + " is DIE\n" + hero.getName() + " is WIN and get " + exp + " experience\n");
                updateArtifactInfo(hero, log, enemy.getLevel());
                return 1;
            }

            heroHp -= enemyA - heroD;
            log.append("\u001B[32m" + enemy.getName() + " attack " + hero.getName() + " and decrease " + (enemyA - heroD) + "hp\n" + "\u001B[0m");
            if (heroHp <= 0)
            {
                log.append(hero.getName() + " is DIE\n");
                return 0;
            }
        }
        return 1;
    }

    static private void updateArtifactInfo (Hero  hero, StringBuffer log, int enemylevel) {
        int art = hero.getWeapon().getQuality() - (enemylevel * 5);
        if (art > 0) {
            hero.getWeapon().setQuality(art);
            log.append(hero.getWeapon().getName() + "quality = " + art + "\n");
        }
        else {
            hero.setWeapon(null);
            log.append(hero.getName() + "droped his " + hero.getWeapon().getName() + "\n");
        }

        art = hero.getHelm().getQuality() - (enemylevel * 7);
        if (art > 0) {
            hero.getHelm().setQuality(art);
            log.append(hero.getHelm().getName() + "quality = " + art + "\n");
        }
        else {
            hero.setHelm(null);
            log.append(hero.getName() + "droped his " + hero.getHelm().getName() + "\n");
        }

        art = hero.getArmor().getQuality() - (enemylevel * 9);
        if (art > 0) {
            hero.getArmor().setQuality(art);
            log.append(hero.getArmor().getName() + "quality = " + art + "\n");
        }
        else {
            hero.setArmor(null);
            log.append(hero.getArmor() + "droped his " + hero.getArmor().getName() + "\n");
        }
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
