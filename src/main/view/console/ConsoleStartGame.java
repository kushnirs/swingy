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


        Scanner in = new Scanner(System.in);
        int input = 0;
        do {
            try {
                System.out.print("Command: ");
                input = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mMust be 1 or 2\u001B[0m");
            }
        } while (input != 1 && input != 2);

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

        Scanner in = new Scanner(System.in);
        String name;
        System.out.println("\u001B[36m/-------------------CreateHero---------------------/\u001B[0m");

        do {
                System.out.print("Add name: ");
                name = in.nextLine().replaceAll("(^\\s+|\\s+$)", "");
        } while (name.length() == 0);

        System.out.println("\u001B[36m/-------------------CreateHero---------------------/");
        System.out.println("/Hero type:                                        /");
        System.out.println("/TYPE(attack,defense,hp)                           /");
        System.out.println("/1.ELF(30, 20, 135)                                /");
        System.out.println("/2.ORC(25, 15, 130)                                /");
        System.out.println("/3.VILLAIN(5, 10, 100)                             /");
        System.out.println("/4.BLACKMAGE(20, 10, 100)                          /");
        System.out.println("/--------------------------------------------------/\u001B[0m");

        int type = 0;
        do {
            try {
                System.out.print("Choose type of hero and add appropriate number: ");
                type = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mMust be from 1 to 4\u001B[0m");
            }
        } while (type != 1 && type != 2 && type != 3 && type != 4);

        int armor = -1;
        do {
            try {
                System.out.print("Hero have armor?(1 - yes / 0 - no): ");
                armor = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mMust be 1 or 2\u001B[0m");
            }
        } while (armor != 1 && armor != 0);

        int helm = -1;
        do {
            try {
                System.out.print("Hero have helm?(1 - yes / 0 - no): ");
                helm = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mMust be 1 or 2\u001B[0m");
            }
        } while (helm != 1 && helm != 0);

        int weapon = -1;
        do {
            try {
                System.out.print("Hero have weapon?(1 - yes / 0 - no): ");
                weapon = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mMust be 1 or 2\u001B[0m");
            }
        } while (weapon != 1 && weapon != 0);

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
                armor == 1 ? new Armor() : null, helm == 1 ? new Helm() : null, weapon == 1 ? new Weapon() : null);
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

    static private void initMission() {
        GamePlayController.initMap();
        GamePlayController.addEnemytoMap(Main.hero.getLevel());
        CharactherController.initHeroPosition(Main.hero);
    }

    static private int startSimulation() {
        int hero_pos = Main.hero.getX()+ Main.hero.getY() * Main.map_size;
        while(hero_pos >= 0 && hero_pos < Main.map_size * Main.map_size &&
                hero_pos > Main.hero.getY() * Main.map_size && hero_pos < (Main.hero.getY() + 1) * Main.map_size )
        {
            System.out.println("\u001B[36m/----------------------Simulation---------------------/\u001B[0m");
            System.out.println("Hero: " + Main.hero.getName());
            System.out.println("Level: " + Main.hero.getLevel());
            System.out.println("Experience: " + Main.hero.getExperience());
            drawMap();

            System.out.println("\u001B[36m/-------------------------Way-------------------------/");
            System.out.println("/                       1.North                       /");
            System.out.println("/               2.West           3.East               /");
            System.out.println("/                       4.South                       /");
            System.out.println("/-----------------------------------------------------/\u001B[0m");


            Scanner in = new Scanner(System.in);
            int way = 0;
            int[][] move = {{0,-1},{-1,0},{1,0},{0,1}};
            do {
                try {
                    System.out.print("Add way: ");
                    way = Integer.parseInt(in.nextLine());
                } catch (Exception e) {
                    System.out.println("\u001B[31mMust be from 1 to 4\u001B[0m");
                }
            } while (way != 1 && way != 2 && way != 3 && way != 4);
            GamePlayController.move(move[way - 1][0],move[way - 1][1]);

            int col = GamePlayController.checkCollision();
            if (col == 1) {
                System.out.println("\u001B[36m/---------------------Run or Fight--------------------/");
                System.out.println("/                  You met the enemy                  /");
                System.out.println("/                     Run or Fight?                   /");
                System.out.println("/                       1      0                      /");
                System.out.println("/-----------------------------------------------------/\u001B[0m");

                int input = -1;
                do {
                    try {
                        System.out.print("Your choose: ");
                        input = Integer.parseInt(in.nextLine());
                    } catch (Exception e) {
                        System.out.println("\u001B[31mMust be 1 or 2\u001B[0m");
                    }
                } while (input != 0 && input != 1) ;

                int res;
                if (input == 1)
                    res = CharactherController.run(Main.hero, CharactherController.newEnemy());
                else
                    res = CharactherController.fight(Main.hero, CharactherController.newEnemy());

                switch (res) {
                    case 0:
                        System.out.println("\u001B[36m/---------------------Run or Fight--------------------/");
                        System.out.println("/                       YOU DIED                      /");
                        System.out.println("/         exit           retry        change hero     /");
                        System.out.println("/           0              1              2           /");
                        System.out.println("/-----------------------------------------------------/\u001B[0m");
                        input = -1;
                        do {
                            try {
                                System.out.print("Your choose: ");
                                input = Integer.parseInt(in.nextLine());
                            } catch (Exception e) {
                                System.out.println("\u001B[31mMust be from 0 to 2\u001B[0m");
                            }
                        } while (input != 0 && input != 1 && input != 2);
                        return input;
                    case 2:
                        System.out.println("\u001B[36m//---------------------Run or Fight--------------------/");
                        System.out.println("/This time you're lucky to run away                   /");
                        System.out.println("/-----------------------------------------------------/\u001B[0m");
                        GamePlayController.move(-move[way - 1][0], -move[way - 1][1]);
                }
            }
            hero_pos = Main.hero.getX()+ Main.hero.getY() * Main.map_size;
        }

        System.out.println("\u001B[36m/----------------------Simulation---------------------/");
        System.out.println("/                       YOU WIN!                      /");
        System.out.println("/                     NEXT LEVEL " + Main.hero.getLevel() + 1 + "                    /");
        System.out.println("/-----------------------------------------------------/\u001B[0m\n");
        return 3;
    }

}