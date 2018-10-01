package ua.unitfactory.swingy.controller;

import ua.unitfactory.swingy.storage.HeroStorage;
import ua.unitfactory.swingy.view.console.ConsoleStartGame;
import ua.unitfactory.swingy.view.gui.GuiStartGame;
import ua.unitfactory.swingy.view.gui.PlayMission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayMissionController {

    public static void addPlayMissionActionListener(PlayMission playMission, GuiStartGame jFrame) {

        playMission.addActionListener(
                // EAST BUTTON
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playMission.setStepImg(GuiStartGame.stepRImg);
                        GamePlayController.startSimulation(jFrame, playMission.getMapArena(), playMission.getArrImg(), 1, 0);
                        playMission.updateHeroInfo();
                    }
                },
                // WEST BUTTON
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playMission.setStepImg(GuiStartGame.stepLImg);
                        GamePlayController.startSimulation(jFrame, playMission.getMapArena(), playMission.getArrImg(), -1, 0);
                        playMission.updateHeroInfo();
                    }
                },
                // NORTH BUTTON
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playMission.setStepImg(GuiStartGame.stepUImg);
                        GamePlayController.startSimulation(jFrame, playMission.getMapArena(), playMission.getArrImg(), 0, -1);
                        playMission.updateHeroInfo();
                    }
                },
                // SOUT BUTTON
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playMission.setStepImg(GuiStartGame.stepDImg);
                        GamePlayController.startSimulation(jFrame, playMission.getMapArena(), playMission.getArrImg(), 0, 1);
                        playMission.updateHeroInfo();
                    }
                },
                // EXIT BUTTON
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        (new HeroStorage()).insertIntoTable();
                        System.exit(0);
                    }
                },
                // CLI BUTTON
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jFrame.dispose();
                        new ConsoleStartGame().Game(3);
                    }
                });
    }
}
