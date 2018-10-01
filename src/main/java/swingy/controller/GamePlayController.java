package swingy.controller;

import swingy.Main;
import swingy.storage.HeroStorage;
import swingy.view.gui.GuiStartGame;
import swingy.view.gui.PlayMission;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GamePlayController {

    public static void startSimulation(GuiStartGame jFrame, JLabel[] mapArena, ImageIcon[] arr,  int x, int y) {
        int res = CharactherController.move(x, y);
        guiDrawMap(mapArena, arr);
        if (res == 1)
            winDialog(jFrame);
        else if (res == 2)
            runOrFightDialog(jFrame, x, y);
}

    public static void initMap() {
        if (Main.hero == null)
            throw new NullPointerException("ERROR: Null object in GamePlayController.initMap");
        Main.map_size = (Main.hero.getLevel() - 1) * 5 + 10 - (Main.hero.getLevel() % 2);
        Main.map = new int[Main.map_size * Main.map_size];
        CharactherController.addEnemytoMap(Main.hero.getLevel());
        CharactherController.initHeroPosition(Main.hero, Main.map_size);
    }

    public static void guiDrawMap(JLabel[] arena, ImageIcon[] arr) {
        int unitImgSize =  Main.map_size < 20 ? PlayMission.panel_size / Main.map_size : 50;
        for(int i = 0; i < Main.map.length; i++) {
            switch (Main.map[i]) {
                case 0:
                    arena[i].setIcon(arr[i]);
                    break;
                case 1:
                    arena[i].setIcon(new ImageIcon(GuiStartGame.enemyImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
                    break;
                case 2:
                    arena[i].setIcon(new ImageIcon(PlayMission.stepImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
                    Main.map[i] = 4;
                    break;
                case 3:
                    arena[i].setIcon(new ImageIcon(GuiStartGame.heroImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
                    break;
            }
        }
    }

    public static StringBuffer drawMap() {
        StringBuffer log = new StringBuffer("");
        int[] map = Main.map;
        for (int i = 0; i < Main.map_size * Main.map_size; i++) {
            if ((i % Main.map_size) == 0)
                log.append("\n");

            if (map[i] == 0)
                log.append("  .");
            else if (map[i] == 1)
                log.append("  E");
            else if (map[i] == 2 || map[i] == 4)
                log.append("\u001B[31m  .\u001B[0m");
            else if (map[i] == 3)
                log.append("  H");
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
    private static void winDialog(GuiStartGame jFrame) {
        Object[] options = {"Exit",  "Continue"};
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>YOU WIN</h2><i>Would you like go to the next level?</i>", "WinDialog",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[1]);
        if (res == 1) {
            initMap();
            jFrame.showPlayMission();
        }
        else {
            (new HeroStorage()).insertIntoTable();
            System.exit(0);
        }
    }

    private static void deadDialog(GuiStartGame jFrame) {
        Object[] options = {"Exit", "change hero", "restart"};
        int res = JOptionPane.showOptionDialog(jFrame, "<html><h2>YOU DIE</h2><i>Choose next step</i>", "WinDialog",JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,options, options[2]);
        if (res == 2) {
            initMap();
            Main.hero.updateHero();
            jFrame.showPlayMission();
        }
        else if (res == 1)
            jFrame.showSelectHero();
        else {
            (new HeroStorage()).insertIntoTable();
            System.exit(0);
        }
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
            CharactherController.move(-x, -y);
        }
        else if (res == 1)
            new FightDialog(jFrame, log);
        else {
            new FightDialog(jFrame, log);
            deadDialog(jFrame);
        }
    }
}
