package ua.unitfactory.swingy.controller;

import ua.unitfactory.swingy.Main;
import ua.unitfactory.swingy.model.artifacts.Armor;
import ua.unitfactory.swingy.model.artifacts.Helm;
import ua.unitfactory.swingy.model.artifacts.Weapon;
import ua.unitfactory.swingy.util.CharactherFactory;
import ua.unitfactory.swingy.view.console.ConsoleStartGame;
import ua.unitfactory.swingy.view.gui.GuiStartGame;
import ua.unitfactory.swingy.view.gui.NewHero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewHeroController {

    public static void addNewHeroListener(NewHero newHero, GuiStartGame jFrame) {
        addNewHeroActionListener(newHero, jFrame);
        addNewHeroItemListener(newHero);
        addNewHeroKeyListener(newHero);
    }

    public static void addNewHeroKeyListener(NewHero newHero){

        newHero.addKeyListener(
                // Name Textfield
                new KeyListener() {
                    public void keyTyped(KeyEvent e) {   }

                    public void keyPressed(KeyEvent e) {   }

                    public void keyReleased(KeyEvent e) {
                        if (newHero.getNameTextField().getText().replaceAll("(^\\s+|\\s+$)", "").length() > 0 ) {
                            newHero.getButtonCreate().setEnabled(true);
                        } else {
                            newHero.getButtonCreate().setEnabled(false);
                        }
                    }
                });
    }

    public static ActionListener addNewHeroARadioButtonActionListener(NewHero newHero) {
        // Radiobuttons
        ActionListener typeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "Elf")
                    newHero.typeInfo(e.getActionCommand());
                else if (e.getActionCommand() == "Orc")
                    newHero.typeInfo(e.getActionCommand());
                else if (e.getActionCommand() == "BlackMage")
                    newHero.typeInfo(e.getActionCommand());
                else if (e.getActionCommand() == "Villain")
                    newHero.typeInfo(e.getActionCommand());
            }
        };
        return typeButtonListener;
    }

    public static void addNewHeroActionListener(NewHero newHero, GuiStartGame jFrame) {
        newHero.addActionListener(
                // Create button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String radioCommand = newHero.getRadioGroup().getSelection().getActionCommand();
                        Main.hero = CharactherFactory.createNewHero(
                                newHero.getNameTextField().getText(),
                                CharactherFactory.Factory(radioCommand),
                                newHero.getArmorArtifact(),
                                newHero.getHelmArtifact(),
                                newHero.getWeaponArtifact());

                        GamePlayController.initMap();
                        jFrame.showPlayMission();
                    }
                },
                // Console mode button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jFrame.dispose();
                        new ConsoleStartGame().Game(4);
                    }
        });
    }

    // Artifact CheckBox
    public static void addNewHeroItemListener(NewHero newHero) {

        newHero.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED) {
                            String name = NewHeroController.addNameArtifact();
                            if (name != null)
                                newHero.setArmorArtifact(new Armor(name));
                            else
                                newHero.getArmorBox().setSelected(false);
                        } else {
                            newHero.setArmorArtifact(null);
                        };
                    }
        },
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED) {
                            String name = NewHeroController.addNameArtifact();
                            if (name != null)
                                newHero.setWeaponArtifact(new Weapon(name));
                            else
                                newHero.getWeaponBox().setSelected(false);
                        } else {
                            newHero.setWeaponArtifact(null);
                        };
                    }
        },
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED) {
                            String name = NewHeroController.addNameArtifact();
                            if (name != null)
                                newHero.setHelmArtifact(new Helm(name));
                            else
                                newHero.getHelmBox().setSelected(false);
                        } else {
                            newHero.setHelmArtifact(null);
                        };
                    }
        });
    }



    public static String addNameArtifact() {
        String name = JOptionPane.showInputDialog("Add artifact name");
        if (name == null || (name = name.replaceAll("(^\\s+|\\s+$)", "")).length() == 0)
            return null;
        else
            return name;
    }
}
