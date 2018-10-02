package ua.unitfactory.swingy.controller;

import javax.swing.*;

import ua.unitfactory.swingy.view.console.ConsoleStartGame;
import ua.unitfactory.swingy.view.gui.GuiStartGame;
import ua.unitfactory.swingy.view.gui.Hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloController {

    public static void addHelloActionListener(Hello hello, final GuiStartGame jFrame) {

        hello.addActionListener(
                // NewGame Button
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (GuiStartGame.heroDB.size() == 0)
                            jFrame.showNewHero();
                        else
                            jFrame.showSelectHero();
                    }
                },
                // Console mode Button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jFrame.dispose();
                        new ConsoleStartGame().Game(ConsoleStartGame.CHANGE);
                    }
                },
                // Exit Button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
    }


}
