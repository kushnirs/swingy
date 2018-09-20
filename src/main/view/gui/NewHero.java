package main.view.gui;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.controller.GamePlayController;
import main.model.artifacts.Armor;
import main.model.artifacts.Helm;
import main.model.artifacts.Weapon;
import main.util.CharactherFactory;
import main.model.characthers.*;
import main.view.console.ConsoleStartGame;

/**
 * Created by skushnir on 12.09.2018.
 */
public class NewHero extends JPanel {

    private JButton buttonCreate = new JButton("Create Hero");
    private JButton buttonCLI = new JButton("CLI mode");
    private JTextField nameTextField = new JTextField(20);
    private JLabel nameLabel = new JLabel("HERO NAME:");
    private JRadioButton typeRadioButton;
    private JTextArea heroInfo = new JTextArea(10, 20);

    private Armor armorArtifact = null;
    private Weapon weaponArtifact = null;
    private Helm helmArtifact = null;


    public NewHero(GuiStartGame jFrame) {
        this.setLayout(new BorderLayout());

        // JLABEL_PANEL
        JPanel createPanel = new JPanel();
        createPanel.setBackground(Color.red);
        createPanel.add(new JLabel("CREATE HERO"));

        // NAME_PANEL
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(createPanel, BorderLayout.NORTH);
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameTextField, BorderLayout.CENTER);
        if(nameTextField.getText().equals(""))
            buttonCreate.setEnabled(false);

        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {   }

            @Override
            public void keyPressed(KeyEvent e) {   }

            @Override
            public void keyReleased(KeyEvent e) {
                if (nameTextField.getText().replaceAll("(^\\s+|\\s+$)", "").length() > 0 ) {
                    buttonCreate.setEnabled(true);
                } else {
                    buttonCreate.setEnabled(false);
                }
            }
        });

        // ARTIFACT_BOX
        Container artifactBox= Box.createVerticalBox();

        JCheckBox armor=new JCheckBox ("Armor");
        armor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String name = addNameArtifact();
                    if (name != null)
                        armorArtifact = new Armor(name);
                    else
                        armor.setSelected(false);
                } else {
                    armorArtifact = null;
                };
            }
        });
        JCheckBox weapon=new JCheckBox ("Weapon");
        weapon.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String name = addNameArtifact();
                    if (name != null)
                        weaponArtifact = new Weapon(name);
                    else
                        weapon.setSelected(false);
                } else {
                    weaponArtifact = null;
                };
            }
        });
        JCheckBox helm=new JCheckBox ("Helm");
        helm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String name = addNameArtifact();
                    if (name != null)
                        helmArtifact = new Helm(name);
                    else
                        helm.setSelected(false);
                } else {
                    helmArtifact = null;
                };
            }
        });

        artifactBox.add(new JLabel("Choose Artifact:"));
        artifactBox.add(armor);
        artifactBox.add(weapon);
        artifactBox.add(helm);

        // TYPE_BOX
        ButtonGroup radioGroup=new ButtonGroup();

        Container typeBox = Box.createVerticalBox();

        typeBox.add(new JLabel("Choose type:"));
        typeBox.add(typeRadioButton=new JRadioButton("Elf"));
        typeRadioButton.setSelected(true);
        typeRadioButton.setActionCommand("Elf");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("Orc"));
        typeRadioButton.setActionCommand("Orc");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("BlackMage"));
        typeRadioButton.setActionCommand("BlackMage");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("Villain"));
        typeRadioButton.setActionCommand("Villain");
        radioGroup.add(typeRadioButton);

        // TYPE_INFO_PANEL
        JPanel typeInfoPanel= new JPanel(new GridLayout());
        heroInfo.setEditable(false);
        typeInfoPanel.add(heroInfo);
        typeInfoPanel.setBorder(BorderFactory.createTitledBorder("Type info:"));

        //BUTTON_PANEL
        JPanel buttonPanel= new JPanel();
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String radioCommand = radioGroup.getSelection().getActionCommand();
                Main.hero = new Hero(nameTextField.getText(), CharactherFactory.Factory(radioCommand), armorArtifact, helmArtifact, weaponArtifact);
                GamePlayController.initMap();
                jFrame.showPlayMission();
            }
        });

        buttonCLI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new ConsoleStartGame().Game(4);
            }
        });

        buttonPanel.add(buttonCreate);
        buttonPanel.add(buttonCLI);

        this.add(namePanel, BorderLayout.NORTH);
        this.add(typeBox, BorderLayout.WEST);
        this.add(typeInfoPanel, BorderLayout.CENTER);
        this.add(artifactBox, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private String addNameArtifact() {
        String name = JOptionPane.showInputDialog("Add artifact name");
        if (name == null || (name = name.replaceAll("(^\\s+|\\s+$)", "")).length() == 0)
            return null;
        else
            return name;
    }
}
