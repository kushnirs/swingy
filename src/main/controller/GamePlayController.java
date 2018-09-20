package main.controller;

import main.Main;
import main.view.gui.GuiStartGame;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GamePlayController {

    public static void startSimulation(GuiStartGame jFrame, int x, int y) {
        if (GamePlayController.move(x, y) == 1) {
            winDialog(jFrame);
        }
        int col = GamePlayController.checkCollision();
        if (col == 1)
            runOrFightDialog(jFrame, x, y);
//
//
//
//
//        String[] fight = log.toString().split("\n");
//        for (String str : fight) {
//            try {
//                System.out.println(str);
//                Thread.sleep(500);
//            } catch (InterruptedException qq) {
//                qq.printStackTrace();
//            }
//        }
//
//        switch (res) {
//            case 0:
//                // DIALOG DIE
//            case 2:
//                //RUN
//                GamePlayController.move(-x, -y);
//        }
//    }
}


    private static void winDialog(GuiStartGame jFrame) {
        Object[] options = {"Continue",
                "Exit"};
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>YOU WIN</h2><i>Would you like go to the next level?</i>", "WinDialog",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
        if (res == 1) {
            initMap();
            jFrame.showPlayMission();
        }
        else
            System.exit(0);
    }

    private static void runOrFightDialog(GuiStartGame jFrame, int x, int y) {
        Object[] options = {"RUN",
                "FIGHT"};
        StringBuffer log = new StringBuffer();
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>YOU WIN</h2><i>Would you like go to the next level?</i>", "WinDialog",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
        if (res == 1)
            res = CharactherController.run(Main.hero, CharactherController.newEnemy(), log);
        else
            res = CharactherController.fight(Main.hero, CharactherController.newEnemy(), log);

        if (res == 2) {
            JOptionPane.showMessageDialog(jFrame,
                    "This time you're lucky to run away",
                    "RUN", JOptionPane.INFORMATION_MESSAGE);
            GamePlayController.move(-x, -y);
        }
        else
            new FightDialog(jFrame, log);
    }

    public static void initMap() {
        if (Main.hero == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.initMap");
        Main.map_size = (Main.hero.getLevel() - 1) * 5 + 10 - (Main.hero.getLevel() % 2);
        Main.map = new int[Main.map_size * Main.map_size];
        GamePlayController.addEnemytoMap(Main.hero.getLevel());
        CharactherController.initHeroPosition(Main.hero, Main.map_size);
    }

    public static void addEnemytoMap(int level) {
        if (Main.map == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.addEnemytoMap");
        int enemyAmount = (int)(Main.map.length * (level * 0.05 + 0.1));
        for (int i = 0; i < enemyAmount; i++)
            Main.map[(int)(Math.random() * Main.map.length)] = 1;
    }

    public static int move(int x, int y) {
        if (Main.hero == null || Main.map == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.move");
        int pos = Main.hero.getX() + x + (Main.hero.getY() + y) * Main.map_size;
        if (pos < 0 || pos > Main.map_size * Main.map_size || (pos < Main.hero.getY() * Main.map_size && y == 1) || (pos >= (Main.hero.getY() + 1) * Main.map_size && y == -1) )
            return 1;

        Main.map[Main.hero.getX()+ Main.hero.getY() * Main.map_size] = 2;

        Main.hero.setX(Main.hero.getX() + x);
        Main.hero.setY(Main.hero.getY() + y);
        return 0;
    }

    public static int checkCollision() {
        if (Main.hero == null || Main.map == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.checkCollision");
        if (Main.map[Main.hero.getX() + Main.hero.getY() * Main.map_size] == 1)
            return 1;
        return 0;
    }

    public static void drawMap(StringBuffer log) {
        int size = Main.map_size;
        int[] map = Main.map;
        for (int i = 0; i < size * size; i++)
        {
            if ((i % size) == 0)
                log.append("\n");

            if (i == Main.hero.getX() + (Main.hero.getY()) * size)
                log.append("  H");
            else if (map[i] == 0)
                log.append("  .");
            else if (map[i] == 1)
                log.append("  E");
            else if (map[i] == 2)
                log.append("  *");
        }
        log.append("\n\n");
    }

    public static String readStr(String command) {
        Scanner in = new Scanner(System.in);
        String name;
        do {
            System.out.print(command);
            name = in.nextLine().replaceAll("(^\\s+|\\s+$)", "");
        } while (name.length() == 0);
        return name;
    }

    public static int readNbr(int min, int max, String command, String error) {
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

    // CUSTOM DIALOG

    private static class FightDialog extends Dialog {
        JTextArea simulation = new JTextArea(10,10);

        public FightDialog(JFrame jFrame, StringBuffer log) {
            super(jFrame, "FightSimulation", true);
            simulation.setEditable(false);
            this.add(simulation);
            showMessage(log);
        }

        private void showMessage(StringBuffer log)
        {
            String[] fight = log.toString().split("\n");
            for (String str : fight)
            {
                try {
                    simulation.append(str);
                    Thread.sleep(500);
                } catch (InterruptedException qq) {
                    qq.printStackTrace();
                }
            }
        }

    }
}
