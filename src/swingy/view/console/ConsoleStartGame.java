package swingy.view.console;

import swingy.Main;
import swingy.controller.CharactherController;
import swingy.controller.GamePlayController;
import swingy.model.artifacts.*;
import swingy.model.characthers.*;
import swingy.util.CharactherFactory;

import java.util.Scanner;

public class ConsoleStartGame {

    public static void Game() {
        int res = startGame();
        while (res != 2)
        {
            switch (res) {
                case 0:
                    playMission();
                case 1:
                    startGame();
            }
        }
        System.exit(1);
    }

    static int startGame (){
        System.out.println("/----------------------Swingy----------------------/");
        System.out.println("/Add number of command:                            /");
        System.out.println("/                                                  /");
        System.out.println("/1.Select a previously created Hero                /");
        System.out.println("/2.Create new Hero                                 /");
        System.out.println("/--------------------------------------------------/");

        System.out.print("command: ");
        Scanner in = new Scanner(System.in);
        int input;
        while ((input = in.nextInt()) != 1 && input != 2);
        if (input == 1)
            selectHero();
        else
            createHero();
        return playMission();
    }

    static private void selectHero() {
        System.out.println("/-------------------SelectHero---------------------/");


        System.out.println("/--------------------------------------------------/");
        System.out.println("Add number of Hero: ");
    }

    static private void createHero() {

        Scanner in = new Scanner(System.in);
        String name;
        System.out.println("/-------------------CreateHero---------------------/");
        System.out.println("Add name: ");
        while ((name = in.nextLine()) == "");

        System.out.println("/Hero type:                                        /");
        System.out.println("/1.ELF                                             /");
        System.out.println("/2.ORC                                             /");
        System.out.println("/3.VILLAIN                                         /");
        System.out.println("/4.BLACKMAGE                                       /");
        System.out.println("Choose type of hero and add appropriate number: ");
        int type;
        while ((type = in.nextInt()) != 1 && type != 2 && type != 3 && type != 4);

        System.out.println("Hero have armor?(1 - yes / 0 - no): ");
        int armor;
        while ((armor = in.nextInt()) != 1 && armor != 0);

        System.out.println("Hero have helm?(1 - yes / 0 - no): ");
        int helm;
        while ((helm = in.nextInt()) != 1 && helm != 0);

        System.out.println("Hero have weapon?(1 - yes / 0 - no): ");
        int weapon;
        while ((weapon = in.nextInt()) != 1 && weapon != 0);

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
            if (map[i] == 0)
                System.out.println(".");
            else if (i == Main.hero.getX() + (Main.hero.getY()) * size)
                System.out.println("H");
            else if (map[i] == 1)
                System.out.println("E");
            else if (map[i] == 2)
                System.out.println("*");
        }
    }

    static private int playMission() {
        GamePlayController.initMap();
        GamePlayController.addEnemytoMap(Main.hero.getLevel());
        CharactherController.initHeroPosition(Main.hero);
        return startSimulation();
    }

    static private int startSimulation() {
        while(1 != 0)
        {
            System.out.println("/----------------------Simulation---------------------/");
            drawMap();

            System.out.println("/-------------------------Way-------------------------/");
            System.out.println("/                       1.North                       /");
            System.out.println("/               2.West           3.East               /");
            System.out.println("/                       4.South                       /");
            System.out.println("/-----------------------------------------------------/");


            System.out.print("Add way: ");
            Scanner in = new Scanner(System.in);
            int way;
            int[][] move = {{0,1},{-1,0},{1,0},{0,-1}};
            while ((way = in.nextInt()) != 1 && way != 2 && way != 3 && way != 4);
            GamePlayController.move(move[way - 1][0],move[way - 1][1]);

            int col = GamePlayController.checkCollision();
            if (col == 1) {
                System.out.println("/---------------------Run or Fight--------------------/");
                System.out.println("/                  You met the enemy                  /");
                System.out.println("/                     Run or Fight?                   /");
                System.out.println("/                       1      0                      /");
                System.out.println("/-----------------------------------------------------/");

                int input;
                while ((input = in.nextInt()) != 0 && input != 1) ;

                int res;
                if (input == 1)
                    res = CharactherController.run(Main.hero, CharactherController.newEnemy());
                else
                    res = CharactherController.fight(Main.hero, CharactherController.newEnemy());

                switch (res) {
                    case 0:
                        System.out.println("/---------------------Run or Fight--------------------/");
                        System.out.println("/                       YOU DIED                      /");
                        System.out.println("/         retry       change hero        exit         /");
                        System.out.println("/           0              1               2          /");
                        System.out.println("/-----------------------------------------------------/");
                        while ((input = in.nextInt()) != 0 && input != 1 && input != 2);
                        return input;
                    case 2:
                        System.out.println("/---------------------Run or Fight--------------------/");
                        System.out.println("/This time you're lucky to run away                   /");
                        System.out.println("/-----------------------------------------------------/");
                        GamePlayController.move(-move[way - 1][0], -move[way - 1][1]);
                }
            }
        }

    }

}
