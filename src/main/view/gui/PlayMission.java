package main.view.gui;

import main.Main;
import main.controller.GamePlayController;
import main.view.console.ConsoleStartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by skushnir on 12.09.2018.
 */
public class PlayMission extends JPanel {
    private JButton buttonEast;
    private JButton buttonWest;
    private JButton buttonNorth;
    private JButton buttonSouth;
    private JButton buttonExit = new JButton("EXIT");
    private JButton buttonCNL = new JButton("CNL mode");

    private JTextArea mapArea = new JTextArea(10,10);

    public PlayMission(GuiStartGame jFrame) {
        this.setLayout(new BorderLayout());

        // Init Button
        try {
            buttonEast = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/resources/right.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
            buttonWest = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/resources/left.jpg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
            buttonNorth = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/resources/up.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
            buttonSouth = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/resources/down.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
        }
        catch (Exception e)
        {
            System.out.println("ERROR: Image not found");
            System.exit(1);
        }

        //Move_Panel
        JPanel movePanel = new JPanel(new BorderLayout());

        // EAST BUTTON
        buttonEast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHandle(jFrame,1, 0);
            }
        });
        movePanel.add(buttonEast, BorderLayout.EAST);

        // WEST BUTTON
        buttonWest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHandle(jFrame,-1,0);
            }
        });
        movePanel.add(buttonWest, BorderLayout.WEST);

        // NORTH BUTTON
        buttonNorth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHandle(jFrame,0,-1);
            }
        });
        movePanel.add(buttonNorth, BorderLayout.NORTH);

        // SOUTH BUTTON
        buttonSouth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHandle(jFrame,0,1);
            }
        });
        movePanel.add(buttonSouth, BorderLayout.CENTER);

        // SOUTH PANEL
        JPanel southPanel = new JPanel(new GridLayout());
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

        //Map_Panel
        mapArea.setEditable(false);
        mapArea.append( GamePlayController.drawMap(1).toString());

        //Hero_info
        Container typeBox = Box.createVerticalBox();

        typeBox.add(new JLabel("<html><b>Hero:</b> " + "<font color='red'>" + Main.hero.getName() + "</font></html>"));
        typeBox.add(Box.createVerticalStrut(5));
        typeBox.add(new JLabel("<html><b>Level:</b> " + "<font color='red'>" + Main.hero.getLevel() + "</font></html>"));
        typeBox.add(Box.createVerticalStrut(5));
        typeBox.add(new JLabel("<html><b>HP:</b> " + "<font color='red'>" + Main.hero.getHp() + "</font></html>"));
        typeBox.add(Box.createVerticalStrut(5));
        typeBox.add(new JLabel("<html><b>Experience:</b> " + "<font color='red'>" + Main.hero.getExperience() + "</font></html>"));

        //Artifact_info
        Container artBox = Box.createVerticalBox();
        if (Main.hero.getWeapon() != null)
            artBox.add(new JLabel("<html><b>Weapon:</b> " + "<font color='red'>" + Main.hero.getWeapon().getQuality() + "</font></html>"));
        else
            artBox.add(new JLabel("<html><b>Weapon:</b> <font color='red'>empty"));
        artBox.add(Box.createVerticalStrut(5));

        if (Main.hero.getArmor() != null)
            artBox.add(new JLabel("<html><b>Armor:</b> " + "<font color='red'>" + Main.hero.getArmor().getQuality() + "</font></html>"));
        else
            artBox.add(new JLabel("<html><b>Armor:</b> <font color='red'>empty"));
        artBox.add(Box.createVerticalStrut(5));

        if (Main.hero.getHelm() != null)
            artBox.add(new JLabel("<html><b>Helm:</b> " + "<font color='red'>" + Main.hero.getHelm().getQuality() + "</font></html>"));
        else
            artBox.add(new JLabel("<html><b>Helm:</b> <font color='red'>empty"));



        this.add(typeBox, BorderLayout.WEST);
        this.add(artBox, BorderLayout.EAST);
        this.add(mapArea, BorderLayout.CENTER);
        this.add(movePanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void moveHandle(GuiStartGame jFrame, int x, int y) {
        GamePlayController.startSimulation(jFrame, mapArea, x, y);
    }
}
