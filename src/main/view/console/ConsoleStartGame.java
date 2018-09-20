package main.view.console;

import main.Main;
import main.controller.CharactherController;
import main.controller.GamePlayController;
import main.model.artifacts.*;
import main.model.characthers.*;
import main.util.CharactherFactory;

//EXIT = 0;
//RETRY = 1;
//CHANGE = 2;
//WIN = 3;

public class ConsoleStartGame {

    public void Game() {
        initHero();
        GamePlayController.initMap();
        int res = startSimulation();
        while (res != 0)
        {
            switch (res) {
                case 1:
                    GamePlayController.initMap();
                    res = startSimulation();
                case 2:
                    initHero();
                    GamePlayController.initMap();
                    res = startSimulation();
            }
        }
        System.out.println("Good bye");
        System.exit(1);
    }

    private void initHero (){
        System.out.println("\u001B[36m/----------------------Swingy----------------------/");
        System.out.println("/Add number of command:                            /");
        System.out.println("/                                                  /");
        System.out.println("/1.Select a previously created Hero                /");
        System.out.println("/2.Create new Hero                                 /");
        System.out.println("/--------------------------------------------------/\u001B[0m");

        int input = GamePlayController.readNbr(1, 2, "Command: ",  "\u001B[31mMust be 1 or 2\u001B[0m");

        if (input == 1)
            selectHero();
        else
            createHero();
    }

    private void selectHero() {
        System.out.println("\u001B[36m/-------------------SelectHero---------------------/");


        System.out.println("/--------------------------------------------------/\u001B[0m");
        System.out.println("Add number of Hero: ");
    }

    private void createHero() {
        System.out.println("\u001B[36m/-------------------CreateHero---------------------/\u001B[0m");

        String name = GamePlayController.readStr("Add name: ");

        System.out.println("\u001B[36m/-------------------CreateHero---------------------/");
        System.out.println("/Hero type:                                        /");
        System.out.println("/TYPE(attack,defense,hp)                           /");
        System.out.println("/1.ELF(30, 10, 135)                                /");
        System.out.println("/2.ORC(25, 5, 130)                                /");
        System.out.println("/3.VILLAIN(5, 6, 100)                             /");
        System.out.println("/4.BLACKMAGE(20, 8, 100)                          /");
        System.out.println("/--------------------------------------------------/\u001B[0m");

        int type = GamePlayController.readNbr(1,4,"Choose type of hero and add appropriate number: ","\u001B[31mMust be from 1 to 4\u001B[0m");

        int armor = GamePlayController.readNbr(0, 1, "Hero have armor?(1 - yes / 0 - no): ", "\u001B[31mMust be 0 or 1\u001B[0m");
        String armor_name = "";
        if (armor == 1)
            armor_name = GamePlayController.readStr("Add armor name: ");

        int helm = GamePlayController.readNbr(0, 1, "Hero have helm?(1 - yes / 0 - no): ", "\u001B[31mMust be 0 or 1\u001B[0m");
        String helm_name = "";
        if (helm == 1)
            helm_name = GamePlayController.readStr("Add helm name: ");

        int weapon = GamePlayController.readNbr(0, 1, "Hero have weapon?(1 - yes / 0 - no): ", "\u001B[31mMust be 0 or 1\u001B[0m");
        String weapon_name = "";
        if (weapon == 1)
            weapon_name = GamePlayController.readStr("Add weapon name: ");

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

    public int startSimulation() {
        if (Main.hero == null || Main.map == null)
            throw new NullPointerException("ERROR: Null object in ConsoleStartGame.startSimulation");
        while(1 != 0)
        {
            StringBuffer log = new StringBuffer("");

            System.out.println("\u001B[36m/----------------------Simulation---------------------/\u001B[0m");
            System.out.println("\u001B[31mHero: " + Main.hero.getName());
            System.out.println("Level: " + Main.hero.getLevel());
            System.out.println("Experience: " + Main.hero.getExperience() + "\u001B[0m");


            GamePlayController.drawMap(log);
            System.out.print(log);
            log = new StringBuffer("");


            System.out.println("\u001B[36m/-------------------------Way-------------------------/");
            System.out.println("/                       1.North                       /");
            System.out.println("/               2.West           3.East               /");
            System.out.println("/                       4.South                       /");
            System.out.println("/-----------------------------------------------------/\u001B[0m");


            int[][] move = {{0,-1},{-1,0},{1,0},{0,1}};
            int way = GamePlayController.readNbr(1, 4, "Add way: ", "\u001B[31mMust be from 1 to 4\u001B[0m");

            if (GamePlayController.move(move[way - 1][0],move[way - 1][1]) == 1)
                break;

            int col = GamePlayController.checkCollision();
            if (col == 1) {
                System.out.println("\u001B[36m/---------------------Run or Fight--------------------/");
                System.out.println("/                  You met the enemy                  /");
                System.out.println("/                     Run or Fight?                   /");
                System.out.println("/                       1      0                      /");
                System.out.println("/-----------------------------------------------------/\u001B[0m");

                int input = GamePlayController.readNbr(0, 1, "Your choose: ", "\u001B[31mMust be 1 or 2\u001B[0m");

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
                        return GamePlayController.readNbr(0, 2, "Your choose: ", "\u001B[31mMust be from 0 to 2\u001B[0m");
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
        return 1;
    }



}
