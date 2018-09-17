package main.view.console;

import main.Main;
import main.controller.CharactherController;
import main.controller.GamePlayController;
import main.model.artifacts.*;
import main.model.characthers.*;
import main.util.CharactherFactory;
import main.view.gui.PlayMission;

import java.util.Scanner;

//EXIT = 0;
//RETRY = 1;
//CHANGE = 2;
//WIN = 3;

public class ConsoleStartGame {

    public static void Game() {
        initHero();
        initMission();
        int res = startSimulation();
        while (res != 0)
        {
            switch (res) {
                case 1:
                    initMission();
                    res = startSimulation();
                case 2:
                    initHero();
                    initMission();
                    res = startSimulation();
                case 3:
                    Main.hero.setlevel(Main.hero.getLevel() + 1);
                    initMission();
                    res = startSimulation();
            }
        }
        System.out.println("Good bye");
        System.exit(1);
    }

    static void initHero (){
        System.out.println("\u001B[36m/----------------------Swingy----------------------/");
        System.out.println("/Add number of command:                            /");
        System.out.println("/                                                  /");
        System.out.println("/1.Select a previously created Hero                /");
        System.out.println("/2.Create new Hero                                 /");
        System.out.println("/--------------------------------------------------/\u001B[0m");

        int input = readNbr(1, 2, "Command: ",  "\u001B[31mMust be 1 or 2\u001B[0m");

        if (input == 1)
            selectHero();
        else
            createHero();
    }

    static private void selectHero() {
        System.out.println("\u001B[36m/-------------------SelectHero---------------------/");


        System.out.println("/--------------------------------------------------/\u001B[0m");
        System.out.println("Add number of Hero: ");
    }

    static private void createHero() {
        System.out.println("\u001B[36m/-------------------CreateHero---------------------/\u001B[0m");

        String name = readStr("Add name: ");

        System.out.println("\u001B[36m/-------------------CreateHero---------------------/");
        System.out.println("/Hero type:                                        /");
        System.out.println("/TYPE(attack,defense,hp)                           /");
        System.out.println("/1.ELF(30, 10, 135)                                /");
        System.out.println("/2.ORC(25, 5, 130)                                /");
        System.out.println("/3.VILLAIN(5, 6, 100)                             /");
        System.out.println("/4.BLACKMAGE(20, 8, 100)                          /");
        System.out.println("/--------------------------------------------------/\u001B[0m");

        int type = readNbr(1,4,"Choose type of hero and add appropriate number: ","\u001B[31mMust be from 1 to 4\u001B[0m");

        int armor = readNbr(0, 1, "Hero have armor?(1 - yes / 0 - no): ", "\u001B[31mMust be 0 or 1\u001B[0m");
        String armor_name = "";
        if (armor == 1)
            armor_name = readStr("Add armor name: ");

        int helm = readNbr(0, 1, "Hero have helm?(1 - yes / 0 - no): ", "\u001B[31mMust be 0 or 1\u001B[0m");
        String helm_name = "";
        if (helm == 1)
            helm_name = readStr("Add helm name: ");

        int weapon = readNbr(0, 1, "Hero have weapon?(1 - yes / 0 - no): ", "\u001B[31mMust be 0 or 1\u001B[0m");
        String weapon_name = "";
        if (weapon == 1)
            weapon_name = readStr("Add weapon name: ");

        Characther tmp = null;
        switch (type) {
            case 1:
                tmp = CharactherFactory.Factory("Elf");
                break;
            case 2:
                tmp = CharactherFactory.Factory("Orc");
                break;
            case 3:
                tmp = CharactherFactory.Factory("Villain");
                break;
            case 4:
                tmp = CharactherFactory.Factory("BlackMage");
                break;
        }
        Main.hero = new Hero(name, tmp,
                armor == 1 ? new Armor(armor_name) : null, helm == 1 ? new Helm(helm_name) : null, weapon == 1 ? new Weapon(weapon_name) : null);
    }


    static private void initMission() {
        GamePlayController.initMap();
        GamePlayController.addEnemytoMap(Main.hero.getLevel());
        CharactherController.initHeroPosition(Main.hero);
    }

    static private int startSimulation() {
        while(1 != 0)
        {
            StringBuffer log = new StringBuffer("");

            System.out.println("\u001B[36m/----------------------Simulation---------------------/\u001B[0m");
            System.out.println("\u001B[31mHero: " + Main.hero.getName());
            System.out.println("Level: " + Main.hero.getLevel());
            System.out.println("Experience: " + Main.hero.getExperience() + "\u001B[0m");

            drawMap();

            System.out.println("\u001B[36m/-------------------------Way-------------------------/");
            System.out.println("/                       1.North                       /");
            System.out.println("/               2.West           3.East               /");
            System.out.println("/                       4.South                       /");
            System.out.println("/-----------------------------------------------------/\u001B[0m");


            int[][] move = {{0,-1},{-1,0},{1,0},{0,1}};
            int way = readNbr(1, 4, "Add way: ", "\u001B[31mMust be from 1 to 4\u001B[0m");

            if (GamePlayController.move(move[way - 1][0],move[way - 1][1]) == 1)
                break;

            int col = GamePlayController.checkCollision();
            if (col == 1) {
                System.out.println("\u001B[36m/---------------------Run or Fight--------------------/");
                System.out.println("/                  You met the enemy                  /");
                System.out.println("/                     Run or Fight?                   /");
                System.out.println("/                       1      0                      /");
                System.out.println("/-----------------------------------------------------/\u001B[0m");

                int input = readNbr(0, 1, "Your choose: ", "\u001B[31mMust be 1 or 2\u001B[0m");

                int res;
                if (input == 1)
                    res = CharactherController.run(Main.hero, CharactherController.newEnemy(),log);
                else
                    res = CharactherController.fight(Main.hero, CharactherController.newEnemy(),log);

                String[] fight = log.toString().split("\n");
                for (String str : fight)
                {
                    try {
                        System.out.println(str);
                        Thread.sleep(500);
                    } catch (InterruptedException qq) {
                        qq.printStackTrace();
                    }
                }
                switch (res) {
                    case 0:
                        System.out.println("\u001B[36m/---------------------Run or Fight--------------------/");
                        System.out.println("/                       YOU DIED                      /");
                        System.out.println("/         exit           retry        change hero     /");
                        System.out.println("/           0              1              2           /");
                        System.out.println("/-----------------------------------------------------/\u001B[0m");
                        return readNbr(0, 2, "Your choose: ", "\u001B[31mMust be from 0 to 2\u001B[0m");
                    case 2:
                        System.out.println("\u001B[36m/---------------------Run or Fight--------------------/");
                        System.out.println("/This time you're lucky to run away                   /");
                        System.out.println("/-----------------------------------------------------/\u001B[0m\n");
                        GamePlayController.move(-move[way - 1][0], -move[way - 1][1]);
                }
            }
        }

        System.out.println("\u001B[36m/----------------------Simulation---------------------/");
        System.out.println("/                       YOU WIN!                      /");
        System.out.println("/                     NEXT LEVEL " + (Main.hero.getLevel() + 1) + "                    /");
        System.out.println("/-----------------------------------------------------/\u001B[0m\n");
        return 3;
    }

    static private void drawMap() {
        int size = Main.map_size;
        int[] map = Main.map;
        for (int i = 0; i < size * size; i++)
        {
            if ((i % size) == 0)
                System.out.print("\n");

            if (i == Main.hero.getX() + (Main.hero.getY()) * size)
                System.out.print(" H");
            else if (map[i] == 0)
                System.out.print(" .");
            else if (map[i] == 1)
                System.out.print(" E");
            else if (map[i] == 2)
                System.out.print(" *");
        }
//        System.out.println(Main.hero.getX() + (Main.hero.getY()) * size);
//        System.out.println(Main.hero.getY() * Main.map_size);
//        System.out.println((Main.hero.getY() + 1) * Main.map_size);
        System.out.print("\n\n");
    }

    static private String readStr(String command) {
        Scanner in = new Scanner(System.in);
        String name;
        do {
            System.out.print(command);
            name = in.nextLine().replaceAll("(^\\s+|\\s+$)", "");
        } while (name.length() == 0);
        return name;
    }

   static private int readNbr(int min, int max, String command, String error) {
        Scanner in = new Scanner(System.in);
        int input = -1;
        do {
            try {
                System.out.print(command);
                input = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (input < min || input > max);
        return input;
    }

}
