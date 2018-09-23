package main.view.gui;

import main.Main;
import main.controller.GamePlayController;
import main.view.console.ConsoleStartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by skushnir on 12.09.2018.
 */
public class PlayMission extends JPanel {
    private JButton buttonEast;
    private JButton buttonWest;
    private JButton buttonNorth;
    private JButton buttonSouth;

    private JButton buttonExit = new JButton("EXIT");
    private JButton buttonCNL = new JButton("CLI mode");

    public static ImageIcon stepImg;
    private JLabel[] mapArena;
    private JLabel   arenaPanel;
    ImageIcon[]      arrImg;

    Container infoBox = Box.createHorizontalBox();
    Container artBox = Box.createHorizontalBox();

    public PlayMission(GuiStartGame jFrame) {
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(100,100));


        // MapPanel
        mapArena = new JLabel[Main.map.length];
        arrImg = new ImageIcon[Main.map.length];
        imageDividing();
        for (int i = 0; i < mapArena.length; i++)
            mapArena[i] = new JLabel();
        arenaPanel = new JLabel(new ImageIcon(GuiStartGame.floorImg.getScaledInstance(55 * Main.map_size, 55 * Main.map_size, Image.SCALE_DEFAULT)));
        JScrollPane arenaScroll = new JScrollPane(arenaPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        arenaPanel.setLayout(new GridLayout(Main.map_size, Main.map_size));
        for(JLabel arenaLabel : mapArena)
            arenaPanel.add(arenaLabel);
        GamePlayController.guiDrawMap(mapArena, arrImg);

        // Init Button
        buttonEast = new JButton(GuiStartGame.rightImg);
        buttonWest = new JButton(GuiStartGame.leftImg);
        buttonNorth = new JButton(GuiStartGame.upImg);
        buttonSouth = new JButton(GuiStartGame.downImg);

        // Move_Panel
        JPanel movePanel = new JPanel(new BorderLayout());

        // EAST BUTTON
        buttonEast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepImg = GuiStartGame.stepRImg;
                GamePlayController.startSimulation(jFrame, mapArena, arrImg, 1, 0);
                updateHeroInfo();
            }
        });
        movePanel.add(buttonEast, BorderLayout.EAST);

        // WEST BUTTON
        buttonWest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepImg = GuiStartGame.stepLImg;
                GamePlayController.startSimulation(jFrame, mapArena, arrImg, -1, 0);
                updateHeroInfo();
            }
        });
        movePanel.add(buttonWest, BorderLayout.WEST);

        // NORTH BUTTON
        buttonNorth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepImg = GuiStartGame.stepUImg;
                GamePlayController.startSimulation(jFrame, mapArena, arrImg, 0, -1);
                updateHeroInfo();
            }
        });
        movePanel.add(buttonNorth, BorderLayout.NORTH);

        // SOUTH BUTTON
        buttonSouth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                stepImg = GuiStartGame.stepDImg;
                GamePlayController.startSimulation(jFrame, mapArena, arrImg, 0, 1);
                updateHeroInfo();
            }
        });
        movePanel.add(buttonSouth, BorderLayout.CENTER);

        // SOUTH PANEL
        JPanel southPanel = new JPanel(new GridLayout(2,1,5,5));
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCNL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new ConsoleStartGame().Game(3);
            }
        });
        southPanel.add(buttonExit);
        southPanel.add(buttonCNL);
        movePanel.add(southPanel, BorderLayout.SOUTH);

        // Hero Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(2,1,5,5));

        //Hero_info
        infoBox.add(new JLabel("<html><b>Hero:</b> " + "<font color='red'>" + Main.hero.getName() + "</font></html>"));
        infoBox.add(Box.createHorizontalStrut(2));
        infoBox.add(new JLabel("<html><b>Level:</b> " + "<font color='red'>" + Main.hero.getLevel() + "</font></html>"));
        infoBox.add(Box.createHorizontalStrut(2));
        infoBox.add(new JLabel("<html><b>HP:</b> " + "<font color='red'>" + Main.hero.getHp() + "</font></html>"));
        infoBox.add(Box.createHorizontalStrut(2));
        infoBox.add(new JLabel("<html><b>Experience:</b> " + "<font color='red'>" + Main.hero.getExperience() + "</font></html>"));



        infoPanel.add(infoBox);

        //Artifact_info
        if (Main.hero.getWeapon() != null)
            artBox.add(new JLabel("<html><b>Weapon:</b> " + "<font color='red'>" + Main.hero.getWeapon().getQuality() + "</font></html>"));
        else
            artBox.add(new JLabel("<html><b>Weapon:</b> <font color='red'>empty"));
        artBox.add(Box.createHorizontalStrut(5));

        if (Main.hero.getArmor() != null)
            artBox.add(new JLabel("<html><b>Armor:</b> " + "<font color='red'>" + Main.hero.getArmor().getQuality() + "</font></html>"));
        else
            artBox.add(new JLabel("<html><b>Armor:</b> <font color='red'>empty"));
        artBox.add(Box.createHorizontalStrut(5));

        if (Main.hero.getHelm() != null)
            artBox.add(new JLabel("<html><b>Helm:</b> " + "<font color='red'>" + Main.hero.getHelm().getQuality() + "</font></html>"));
        else
            artBox.add(new JLabel("<html><b>Helm:</b> <font color='red'>empty"));
        infoPanel.add(artBox);



        this.add(infoPanel, BorderLayout.NORTH);
        this.add(arenaScroll, BorderLayout.CENTER);
        this.add(movePanel, BorderLayout.SOUTH);
//        constraints.gridy = 0;
//        this.add(infoPanel, constraints);
//        constraints.gridy = 1;
//        this.add(arenaScroll, constraints);
//        constraints.gridy = 2;
//        this.add(movePanel,constraints);
        this.setVisible(true);
    }

    public void updateHeroInfo() {
        Component[] tmp = infoBox.getComponents();
        ((JLabel) tmp[0]).setText("<html><b>Hero:</b> " + "<font color='red'>" + Main.hero.getName() + "</font></html>");
        ((JLabel) tmp[2]).setText("<html><b>Level:</b> " + "<font color='red'>" + Main.hero.getLevel() + "</font></html>");
        ((JLabel) tmp[4]).setText("<html><b>HP:</b> " + "<font color='red'>" + Main.hero.getHp() + "</font></html>");
        ((JLabel) tmp[6]).setText("<html><b>Experience:</b> " + "<font color='red'>" + Main.hero.getExperience() + "</font></html>");

        tmp = artBox.getComponents();
        if (Main.hero.getWeapon() != null)
            ((JLabel)tmp[0]).setText("<html><b>Weapon:</b> " + "<font color='red'>" + Main.hero.getWeapon().getQuality() + "</font></html>");
        else
            ((JLabel)tmp[0]).setText("<html><b>Weapon:</b> <font color='red'>empty");
        artBox.add(Box.createHorizontalStrut(5));

        if (Main.hero.getArmor() != null)
            ((JLabel)tmp[2]).setText("<html><b>Armor:</b> " + "<font color='red'>" + Main.hero.getArmor().getQuality() + "</font></html>");
        else
            ((JLabel)tmp[2]).setText("<html><b>Armor:</b> <font color='red'>empty");
        artBox.add(Box.createHorizontalStrut(5));

        if (Main.hero.getHelm() != null)
            ((JLabel)tmp[4]).setText("<html><b>Helm:</b> " + "<font color='red'>" + Main.hero.getHelm().getQuality() + "</font></html>");
        else
            ((JLabel)tmp[4]).setText("<html><b>Helm:</b> <font color='red'>empty");

    }

    private void imageDividing() {
        for (int r = 0; r < Main.map_size; r++) {
            for (int c = 0; c < Main.map_size; c++) {
                int w = GuiStartGame.floorImg.getWidth() / Main.map_size;
                int h = GuiStartGame.floorImg.getHeight() / Main.map_size;
                BufferedImage b = GuiStartGame.floorImg.getSubimage(c * w, r * h, w, h);
                arrImg[r * Main.map_size + c] = new ImageIcon(new ImageIcon(b).getImage().getScaledInstance(55,55, Image.SCALE_DEFAULT));
            }
        }
    }
}
