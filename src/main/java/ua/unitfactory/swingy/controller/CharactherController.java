package ua.unitfactory.swingy.controller;

import ua.unitfactory.swingy.Main;
import ua.unitfactory.swingy.model.characthers.Characther;
import ua.unitfactory.swingy.model.characthers.Hero;
import ua.unitfactory.swingy.util.CharactherFactory;

/**
 * Created by skushnir on 12.09.2018.
 */
public class CharactherController {

    public static int fight(Hero hero, Characther enemy, StringBuffer log, int mode) throws NullPointerException {
        if (hero == null || enemy == null || log == null)
            throw new NullPointerException("ERROR: Null object in CharactherController.fight");
        int enemyHp = enemy.getHp(), heroHp = hero.getHp();
        int enemyD = enemy.getDefense(), heroD = hero.getDefense();
        int enemyA = enemy.getAttack(), heroA = hero.getAttack();
        String weaponN = "", helmN = "", armorN = "";

        int art;
        if (hero.getWeapon() != null) {
            weaponN = " using " + hero.getWeapon().getName();
            art = hero.getWeapon().getQuality() * hero.getWeapon().getValue() / 100;
            log.append("Artifact " + hero.getWeapon().getName() + " increases the attack +" + art + "\n");
            heroA += art;
        }
        if (hero.getArmor() != null) {
            armorN = " which defending himself with " + hero.getArmor().getName();
            art = hero.getArmor().getQuality() * hero.getArmor().getValue() / 100;
            log.append("Artifact " + hero.getArmor().getName() + " increases defense +" + art + "\n");
            heroD += art;
        }
        if (hero.getHelm() != null) {
            helmN = " dressed in " + hero.getHelm().getName();
            art = hero.getHelm().getQuality() * hero.getHelm().getValue() / 100;
            log.append("Artifact " + hero.getHelm().getName() + " increases defense +" + art + "\n");
            heroHp += art;
        }

        heroA = (heroA - enemyD) <= 0 ? 0 : heroA - enemyD;
        enemyA = (enemyA - heroD) <= 0 ? 0 : enemyA - heroD;
        while (heroHp > 0 && enemyHp > 0)
        {
            log.append("***************************FIGHT***********************\n");
            if (mode == 0)
                log.append("\u001B[36m" + hero.getName() + " HP: " + heroHp + "\n" + enemy.getName() + " HP: " + enemyHp + "\n" + "\u001B[0m");
            else
                log.append(hero.getName() + " HP: " + heroHp + "\n" + enemy.getName() + " HP: " + enemyHp + "\n");
            enemyHp -= heroA;
            if (mode == 0)
                log.append("\u001B[31m" + hero.getName() + " attack " + enemy.getName() + weaponN + " and decrease " + heroA + "hp\n" + "\u001B[0m");
            else
                log.append(hero.getName() + " attack " + enemy.getName() + weaponN + " and decrease " + heroA + "hp\n");

            if (enemyHp <= 0)
            {
                int exp = 500 * (enemy.getLevel() / 10 + 1);
                log.append("\n##################WIN###################\n");
                log.append(enemy.getName() + " is DEAD\n" + hero.getName() + " WINS and get " + exp + " experience\n");
                log.append("##################WIN###################\n");
                hero.setExperience(exp);
                hero.checkExperience();
                hero.setHp(heroHp);
                updateArtifactInfo(hero, log, enemy.getLevel());
                log.append("***************************FIGHT***********************\n\n\n");
                return 1;
            }

            heroHp -= enemyA;
            if (mode == 0)
                log.append("\u001B[32m" + enemy.getName() + " attack " + hero.getName() + armorN + helmN + " and decrease " + enemyA + "hp\n" + "\u001B[0m");
            else
                log.append(enemy.getName() + " attack " + hero.getName() + armorN + helmN + " and decrease " + enemyA + "hp\n");
            if (heroHp <= 0)
            {
                log.append("\n##################DEAD##################\n");
                log.append(hero.getName() + " is DEAD\n");
                log.append("##################DEAD##################\n");
                log.append("***************************FIGHT************************\n\n\n");
                return 0;
            }
            log.append("***************************FIGHT***********************\n\n\n");
        }
        return 1;
    }

    static private void updateArtifactInfo (Hero  hero, StringBuffer log, int enemylevel) throws NullPointerException {
        if (hero == null || log == null)
            throw new NullPointerException("ERROR: Null object in CharactherController.updateArtifactInfo");
        int art;
        if (hero.getWeapon() != null) {
            art = hero.getWeapon().getQuality() - (enemylevel * 5);
            if (art > 0) {
                hero.getWeapon().setQuality(art);
                log.append(hero.getWeapon().getName() + " quality = " + art + "\n");
            } else {
                hero.setWeapon(null);
                log.append(hero.getName() + " droped his " + hero.getWeapon().getName() + "\n");
            }
        }

        if (hero.getHelm() != null) {
            art = hero.getHelm().getQuality() - (enemylevel * 7);
            if (art > 0) {
                hero.getHelm().setQuality(art);
                log.append(hero.getHelm().getName() + " quality = " + art + "\n");
            } else {
                hero.setHelm(null);
                log.append(hero.getName() + " droped his " + hero.getHelm().getName() + "\n");
            }
        }

        if (hero.getArmor() != null) {
            art = hero.getArmor().getQuality() - (enemylevel * 9);
            if (art > 0) {
                hero.getArmor().setQuality(art);
                log.append(hero.getArmor().getName() + " quality = " + art + "\n");
            } else {
                hero.setArmor(null);
                log.append(hero.getArmor() + " droped his " + hero.getArmor().getName() + "\n");
            }
        }
    }

    public static int move(int x, int y) {
        final int MOVE = 0;
        final int WIN = 1;
        final int FIGHT = 2;

        if (Main.hero == null || Main.map == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.move");
        int pos = Main.hero.getX() + x + (Main.hero.getY() + y) * Main.map_size;
        if (pos < 0 || pos > Main.map_size * Main.map_size ||
                (pos < Main.hero.getY() * Main.map_size && (y == 1 || x == -1)) ||
                (pos >= (Main.hero.getY() + 1) * Main.map_size && (x == 1 ||y == -1)) )
            return WIN;

        if (x == 0 && y == -1)
            Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 10;
        else if (x == 0 && y == 1)
            Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 20;
        else if (x == -1 && y == 0)
            Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 30;
        else
            Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 40;

        Main.hero.setX(Main.hero.getX() + x);
        Main.hero.setY(Main.hero.getY() + y);
        if (Main.map[Main.hero.getX() + Main.hero.getY() * Main.map_size] == 1) {
            Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 3;
            return FIGHT;
        }
        Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 3;
        return MOVE;
    }

    public static int run(Hero hero, Characther enemy, StringBuffer log, int mode) throws NullPointerException {
        if (hero == null || log == null)
            throw new NullPointerException("ERROR: Null object in CharactherController.run");
        double probability = Math.random();
        if (probability > 0.5)
            return fight(hero, enemy, log, mode);
        else
            return 2;
    }

    public static void addEnemytoMap(int level) {
        if (Main.map == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.addEnemytoMap");
        int enemyAmount = (int)(Main.map.length * (level * 0.05 + 0.1));
        for (int i = 0; i < enemyAmount; i++)
            Main.map[(int)(Math.random() * Main.map.length)] = 1;
    }

    public static Characther newEnemy() {
        String[] enemy = {"Orc", "Villain", "Elf",  "BlackMage"};
        int range[] = {0, 3};
        int i = range[0] + (int) (Math.random() * range[1]);
        return CharactherFactory.Factory(enemy[i]);
    }

    public static void initHeroPosition(Hero hero, int map_size) throws NullPointerException{
        if (hero == null)
            throw new NullPointerException("ERROR: Null object in CharactherController.initHeroPosition");
        hero.setX(map_size / 2);
        hero.setY(map_size / 2);
        Main.map[Main.hero.getX() + (Main.hero.getY()) * Main.map_size] = 3;
    }

}
