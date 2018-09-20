package main.view.gui;

import main.Main;
import main.controller.GamePlayController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by skushnir on 12.09.2018.
 */
public class PlayMission extends JPanel {
    private JButton buttonEast =new JButton(new ImageIcon(new ImageIcon("/Users/sergee/projects/swingy/src/resources/right.jpeg").getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
    private JButton buttonWest = new JButton(new ImageIcon(new ImageIcon("/Users/sergee/projects/swingy/src/resources/left.jpg").getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
    private JButton buttonNorth = new JButton(new ImageIcon(new ImageIcon("/Users/sergee/projects/swingy/src/resources/up.jpeg").getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
    private JButton buttonSouth = new JButton(new ImageIcon(new ImageIcon("/Users/sergee/projects/swingy/src/resources/down.jpeg").getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT)));
    private JButton buttonExit = new JButton("EXIT");
    private JButton buttonCNL = new JButton("CNL mode");

    private JTextArea mapArea = new JTextArea();

    public PlayMission(GuiStartGame jFrame) {
        this.setLayout(new BorderLayout());

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
        southPanel.add(buttonExit);
        southPanel.add(buttonCNL);
        movePanel.add(southPanel, BorderLayout.SOUTH);

        //Map_Panel
        StringBuffer log = new StringBuffer();
        GamePlayController.drawMap(log);
        System.out.print(log.toString());
        mapArea.setEditable(false);
        mapArea.append(log.toString());

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
        GamePlayController.startSimulation(jFrame, x,y);
    }
}
