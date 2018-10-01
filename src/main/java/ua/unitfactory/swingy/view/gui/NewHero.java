package ua.unitfactory.swingy.view.gui;

import ua.unitfactory.swingy.controller.GamePlayController;
import ua.unitfactory.swingy.controller.NewHeroController;
import ua.unitfactory.swingy.model.characthers.Characther;
import ua.unitfactory.swingy.view.console.ConsoleStartGame;
import ua.unitfactory.swingy.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ua.unitfactory.swingy.model.artifacts.Armor;
import ua.unitfactory.swingy.model.artifacts.Helm;
import ua.unitfactory.swingy.model.artifacts.Weapon;
import ua.unitfactory.swingy.util.CharactherFactory;

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

    private final ButtonGroup radioGroup=new ButtonGroup();

    private final JCheckBox armorBox = new JCheckBox ("Armor");
    private final JCheckBox weaponBox = new JCheckBox ("Weapon");
    private final JCheckBox helmBox = new JCheckBox ("Helm");

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


        // ARTIFACT_BOX
        Container artifactBox= Box.createVerticalBox();

        artifactBox.add(new JLabel("Choose Artifact:"));
        artifactBox.add(armorBox);
        artifactBox.add(weaponBox);
        artifactBox.add(helmBox);

        // TYPE_BOX

        Container typeBox = Box.createVerticalBox();
        typeBox.add(new JLabel("Choose type:"));

        typeBox.add(typeRadioButton=new JRadioButton("Elf"));
        typeRadioButton.addActionListener(NewHeroController.addNewHeroARadioButtonActionListener(this));
        typeRadioButton.setSelected(true);
        typeRadioButton.setActionCommand("Elf");
        typeInfo("Elf");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("Orc"));
        typeRadioButton.addActionListener(NewHeroController.addNewHeroARadioButtonActionListener(this));
        typeRadioButton.setActionCommand("Orc");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("BlackMage"));
        typeRadioButton.addActionListener(NewHeroController.addNewHeroARadioButtonActionListener(this));
        typeRadioButton.setActionCommand("BlackMage");
        radioGroup.add(typeRadioButton);

        typeBox.add(typeRadioButton=new JRadioButton("Villain"));
        typeRadioButton.addActionListener(NewHeroController.addNewHeroARadioButtonActionListener(this));
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
        buttonCLI.setForeground(Color.blue);

        buttonPanel.add(buttonCreate);
        buttonPanel.add(buttonCLI);

        this.add(namePanel, BorderLayout.NORTH);
        this.add(typeBox, BorderLayout.WEST);
        this.add(typeInfoPanel, BorderLayout.CENTER);
        this.add(artifactBox, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void typeInfo(String typename) {
        Characther tmp = CharactherFactory.Factory(typename);
        String info = "Type: " + tmp.getName() +
                "\nHP: " + tmp.getHp() +
                "\nAttack: " +tmp.getAttack() +
                "\nDefense: " + tmp.getDefense();
        heroInfo.setText(info);
    }

    public void addKeyListener(KeyListener k_list) {
        nameTextField.addKeyListener(k_list);
    }

    public void addItemListener(ItemListener armor_listener, ItemListener weapon_listener, ItemListener helm_listener) {
        armorBox.addItemListener(armor_listener);
        weaponBox.addItemListener(weapon_listener);
        helmBox.addItemListener(helm_listener);
    }


    public void addActionListener(ActionListener create_b, ActionListener cli_b) {
        buttonCreate.addActionListener(create_b);
        buttonCLI.addActionListener(cli_b);
    }


    // GETTERS

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JButton getButtonCreate() {
        return buttonCreate;
    }

    public Armor getArmorArtifact() {
        return armorArtifact;
    }

    public Weapon getWeaponArtifact() {
        return weaponArtifact;
    }

    public Helm getHelmArtifact() {
        return helmArtifact;
    }

    public JCheckBox getArmorBox() {
        return armorBox;
    }

    public JCheckBox getWeaponBox() {
        return weaponBox;
    }

    public JCheckBox getHelmBox() {
        return helmBox;
    }

    public ButtonGroup getRadioGroup() {
        return radioGroup;
    }

    // SETTERS

    public void setArmorArtifact(Armor armorArtifact) {
        this.armorArtifact = armorArtifact;
    }

    public void setWeaponArtifact(Weapon weaponArtifact) {
        this.weaponArtifact = weaponArtifact;
    }

    public void setHelmArtifact(Helm helmArtifact) {
        this.helmArtifact = helmArtifact;
    }
}
