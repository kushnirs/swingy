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

    public static void startSimulation(GuiStartGame jFrame, JTextArea mapArea, int x, int y) {
        if (move(x, y) == 1)
            winDialog(jFrame);
        mapArea.setText("");
        mapArea.append(drawMap(1).toString());
        int col = checkCollision();
        if (col == 1)
            runOrFightDialog(jFrame, x, y);
}


    private static void winDialog(GuiStartGame jFrame) {
        Object[] options = {"Exit",  "Continue"};
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>YOU WIN</h2><i>Would you like go to the next level?</i>", "WinDialog",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[1]);
        if (res == 1) {
            initMap();
            jFrame.showPlayMission();
        }
        else
            System.exit(0);
    }

    private static void deadDialog(GuiStartGame jFrame) {
        Object[] options = {"Exit", "change hero", "restart"};
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>YOU DIE</h2><i>Choose next step</i>", "WinDialog",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[2]);
        System.out.print(res);
        if (res == 2) {
            initMap();
            Main.hero.updateHero();
            jFrame.showPlayMission();
        }
        else if (res == 1)
            jFrame.showSelectHero();
        else
            System.exit(0);
    }

    private static void runOrFightDialog(GuiStartGame jFrame, int x, int y) {
        Object[] options = {"RUN",
                "FIGHT"};
        StringBuffer log = new StringBuffer("");
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>RUN OR FIGHT</h2><i>Would you like kick his ass?</i>", "WinDialog",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
        if (res == 0)
            res = CharactherController.run(Main.hero, CharactherController.newEnemy(), log, 1);
        else
            res = CharactherController.fight(Main.hero, CharactherController.newEnemy(), log, 1);

        if (res == 2) {
            JOptionPane.showMessageDialog(jFrame,
                    "This time you're lucky to run away",
                    "RUN", JOptionPane.INFORMATION_MESSAGE);
            GamePlayController.move(-x, -y);
        }
        else if (res == 1)
           new FightDialog(jFrame, log);
        else {
            new FightDialog(jFrame, log);
            deadDialog(jFrame);
        }
    }

    public static void initMap() {
        if (Main.hero == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.initMap");
        Main.map_size = (Main.hero.getLevel() - 1) * 5 + 10 - (Main.hero.getLevel() % 2);
        System.out.println(Main.map_size);
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
        if (pos < 0 || pos > Main.map_size * Main.map_size || (pos < Main.hero.getY() * Main.map_size && (y == 1 || x == -1)) || (pos >= (Main.hero.getY() + 1) * Main.map_size && (x == 1 ||y == -1)) )
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

    public static StringBuffer drawMap(int mode) {
        StringBuffer log = new StringBuffer("");
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
            {
                if (mode == 0)
                    log.append("\u001B[31m  .\u001B[0m");
                else
                    log.append(" ,");
            }
        }
        log.append("\n\n");
        return log;
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

    private static class FightDialog extends JDialog {
        JTextArea simulation = new JTextArea(10,10);
        private int sizeWidth = 350;
        private int sizeHeight = 400;

        public FightDialog(JFrame jFrame, StringBuffer log) {
            super(jFrame, "FightSimulation", true);
            this.setLayout(new BorderLayout());
            setBounds(jFrame.getX(), jFrame.getY(),sizeWidth, sizeHeight);

            simulation.setEditable(false);
            simulation.append(log.toString());

            JScrollPane sp = new JScrollPane(simulation, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp.getVerticalScrollBar().setValue(1);
            this.add(sp, BorderLayout.CENTER);

            JButton closeButton = new JButton("close");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            this.add(closeButton, BorderLayout.SOUTH);
            this.setVisible(true);
        }
    }
}
