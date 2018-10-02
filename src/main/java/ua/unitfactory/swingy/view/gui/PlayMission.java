package ua.unitfactory.swingy.view.gui;

import ua.unitfactory.swingy.Main;
import ua.unitfactory.swingy.controller.GamePlayController;
import ua.unitfactory.swingy.storage.HeroStorage;
import ua.unitfactory.swingy.view.console.ConsoleStartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by skushnir on 12.09.2018.
 */
public class PlayMission extends JPanel {
    public static final int panel_size = 800;

    private JButton buttonEast;
    private JButton buttonWest;
    private JButton buttonNorth;
    private JButton buttonSouth;

    private JButton buttonExit = new JButton("EXIT");
    private JButton buttonCNL = new JButton("CLI mode");

    public static final Map<Integer, ImageIcon> moveImg = new HashMap<Integer, ImageIcon>();

    private JLabel[] mapArena;
    private JLabel   arenaPanel;
    ImageIcon[]      arrImg;

    Container infoBox = Box.createHorizontalBox();
    Container artBox = Box.createHorizontalBox();

    public PlayMission(final GuiStartGame jFrame) {
        this.setLayout(new BorderLayout());

        int unitImgSize = Main.map_size < 20 ? PlayMission.panel_size / Main.map_size : 50;
        moveImg.put(1, new ImageIcon(GuiStartGame.enemyImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
        moveImg.put(3, new ImageIcon(GuiStartGame.heroImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
        moveImg.put(10, new ImageIcon(GuiStartGame.stepUImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
        moveImg.put(20, new ImageIcon(GuiStartGame.stepDImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
        moveImg.put(30, new ImageIcon(GuiStartGame.stepLImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));
        moveImg.put(40, new ImageIcon(GuiStartGame.stepRImg.getImage().getScaledInstance(unitImgSize, unitImgSize, Image.SCALE_DEFAULT)));

        // MAP PANEL
        mapArena = new JLabel[Main.map.length];
        arrImg = new ImageIcon[Main.map.length];
        imageDividing();
        for (int i = 0; i < mapArena.length; i++)
            mapArena[i] = new JLabel();
        JPanel gavno = new JPanel();
        arenaPanel = new JLabel(new ImageIcon(GuiStartGame.floorImg.getScaledInstance(unitImgSize * Main.map_size, unitImgSize * Main.map_size, Image.SCALE_DEFAULT)));
        gavno.add(arenaPanel);
        JScrollPane arenaScroll = new JScrollPane(gavno, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        arenaScroll.setPreferredSize(new Dimension(panel_size, panel_size));
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

        movePanel.add(buttonEast, BorderLayout.EAST);
        movePanel.add(buttonWest, BorderLayout.WEST);
        movePanel.add(buttonNorth, BorderLayout.NORTH);
        movePanel.add(buttonSouth, BorderLayout.CENTER);
        // SOUTH PANEL
        JPanel southPanel = new JPanel(new GridLayout(2,1));
        buttonExit.setForeground(Color.red);
        buttonCNL.setForeground(Color.blue);
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
        int unitImgSize =  Main.map_size < 20 ? panel_size / Main.map_size : 50;
        for (int r = 0; r < Main.map_size; r++) {
            for (int c = 0; c < Main.map_size; c++) {
                int w = GuiStartGame.floorImg.getWidth() / Main.map_size;
                int h = GuiStartGame.floorImg.getHeight() / Main.map_size;
                BufferedImage b = GuiStartGame.floorImg.getSubimage(c * w, r * h, w, h);
                arrImg[r * Main.map_size + c] = new ImageIcon(new ImageIcon(b).getImage().getScaledInstance(unitImgSize,unitImgSize, Image.SCALE_DEFAULT));
            }
        }
    }

    public void addActionListener(ActionListener east, ActionListener west, ActionListener north, ActionListener south, ActionListener exit, ActionListener cli) {
        buttonEast.addActionListener(east);
        buttonWest.addActionListener(west);
        buttonNorth.addActionListener(north);
        buttonSouth.addActionListener(south);
        buttonExit.addActionListener(exit);
        buttonCNL.addActionListener(cli);
    }

    // GETTERS
    public ImageIcon[] getArrImg() {
        return arrImg;
    }

    public JLabel[] getMapArena() {
        return mapArena;
    }
}
