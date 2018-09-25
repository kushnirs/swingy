package swingy.view.gui;

import swingy.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import swingy.controller.GamePlayController;
import swingy.model.artifacts.Armor;
import swingy.model.artifacts.Helm;
import swingy.model.artifacts.Weapon;
import swingy.util.CharactherFactory;
import swingy.model.characthers.*;
import swingy.view.console.ConsoleStartGame;
import swingy.view.gui.GuiStartGame;

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


    public NewHero(final GuiStartGame jFrame) {
        this.setPreferredSize(new Dimension(GuiStartGame.sizeWidth, GuiStartGame.sizeHeight));
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
            public void keyTyped(KeyEvent e) {   }

            public void keyPressed(KeyEvent e) {   }

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

        final JCheckBox armor=new JCheckBox ("Armor");
        armor.addItemListener(new ItemListener() {
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
        final JCheckBox weapon=new JCheckBox ("Weapon");
        weapon.addItemListener(new ItemListener() {
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
        final JCheckBox helm=new JCheckBox ("Helm");
        helm.addItemListener(new ItemListener() {
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
        final ButtonGroup radioGroup=new ButtonGroup();

        Container typeBox = Box.createVerticalBox();
        typeBox.add(new JLabel("Choose type:"));

        ActionListener typeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "Elf")
                    typeInfo(e.getActionCommand());
                else if (e.getActionCommand() == "Orc")
                    typeInfo(e.getActionCommand());
                else if (e.getActionCommand() == "BlackMage")
                    typeInfo(e.getActionCommand());
                else if (e.getActionCommand() == "Villain")
                    typeInfo(e.getActionCommand());
            }
        };

        typeBox.add(typeRadioButton=new JRadioButton("Elf"));
        typeRadioButton.addActionListener(typeButtonListener);
        typeRadioButton.setSelected(true);
        typeRadioButton.setActionCommand("Elf");
        typeInfo("Elf");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("Orc"));
        typeRadioButton.addActionListener(typeButtonListener);
        typeRadioButton.setActionCommand("Orc");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("BlackMage"));
        typeRadioButton.addActionListener(typeButtonListener);
        typeRadioButton.setActionCommand("BlackMage");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("Villain"));
        typeRadioButton.addActionListener(typeButtonListener);
        typeRadioButton.setActionCommand("Villain");
        radioGroup.add(typeRadioButton);

        // TYPE_INFO_PANEL
        JPanel typeInfoPanel= new JPanel(new GridLayout());
        heroInfo.setEditable(false);
        typeInfoPanel.add(heroInfo);
        typeInfoPanel.setBorder(BorderFactory.createTitledBorder("Type info:"));

        //BUTTON_PANEL
        JPanel buttonPanel= new JPanel();
        buttonCreate.setForeground(Color.red);
        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String radioCommand = radioGroup.getSelection().getActionCommand();
                Main.hero = new Hero(nameTextField.getText(), CharactherFactory.Factory(radioCommand), armorArtifact, helmArtifact, weaponArtifact);
                GamePlayController.initMap();
                jFrame.showPlayMission();
            }
        });

        buttonCLI.setForeground(Color.blue);
        buttonCLI.addActionListener(new ActionListener() {
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

    private void typeInfo(String typename) {
        Characther tmp = CharactherFactory.Factory(typename);
        String info = "Type: " + tmp.getName() +
                "\nHP: " + tmp.getHp() +
                "\nAttack: " +tmp.getAttack() +
                "\nDefense: " + tmp.getDefense();
        heroInfo.setText(info);
    }

    private String addNameArtifact() {
        String name = JOptionPane.showInputDialog("Add artifact name");
        if (name == null || (name = name.replaceAll("(^\\s+|\\s+$)", "")).length() == 0)
            return null;
        else
            return name;
    }
}
