package ua.unitfactory.swingy.controller;

import ua.unitfactory.swingy.Main;
import ua.unitfactory.swingy.view.console.ConsoleStartGame;
import ua.unitfactory.swingy.view.gui.GuiStartGame;
import ua.unitfactory.swingy.view.gui.SelectHero;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectHeroController {
    private static int selectIndex = 0;
    public static void addSelectHeroListener(SelectHero selectHero, GuiStartGame jFrame) {

        selectHero.addListener(
                // Hero info Textfield
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        selectIndex = selectHero.getHeroList().getSelectedIndex();
                        String info = "Type: " + GuiStartGame.heroDB.get(selectIndex).getType() + "\n" +
                                "Level: " + GuiStartGame.heroDB.get(selectIndex).getLevel() + "\n" +
                                "Experience: " + GuiStartGame.heroDB.get(selectIndex).getExperience() + "\n" +
                                "Hp: " + GuiStartGame.heroDB.get(selectIndex).getHp() + "\n" +
                                "Attack: " + GuiStartGame.heroDB.get(selectIndex).getAttack() + "\n" +
                                "Defense: " + GuiStartGame.heroDB.get(selectIndex).getDefense() + "\n" +
                                "Armor: " + (GuiStartGame.heroDB.get(selectIndex).getArmor() == null ? "empty" : "available") + "\n" +
                                "Weapon: " + (GuiStartGame.heroDB.get(selectIndex).getWeapon() == null ? "empty" : "available") + "\n" +
                                "Helm: " + (GuiStartGame.heroDB.get(selectIndex).getHelm() == null ? "empty" : "available");
                        selectHero.getHeroInfo().setText(info);
                        selectHero.getButtonSelect().setEnabled(true);
                    }
                },
                // NewHero Button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jFrame.showNewHero();
                    }
                },
                // Select Button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main.hero = GuiStartGame.heroDB.get(selectIndex);
                        GamePlayController.initMap();
                        jFrame.showPlayMission();
                    }
                },
                // Console mode Button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jFrame.dispose();
                        new ConsoleStartGame().Game(ConsoleStartGame.CHANGE);
                    }
                });
    }
}
