package swingy.view.gui;

import swingy.storage.HeroStorage;
import swingy.view.console.ConsoleStartGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello extends JPanel {

    private JButton buttonNew = new JButton("NEW GAME");
    private JButton buttonCLI = new JButton("CLI mode");
    private JButton buttonExit = new JButton("EXIT");

    public Hello(final GuiStartGame jFrame) {
        this.setPreferredSize(new Dimension(GuiStartGame.sizeWidth, GuiStartGame.sizeHeight));
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));

        buttonNew.setForeground(Color.red);
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GuiStartGame.heroDB.size() == 0)
                    jFrame.showNewHero();
                else
                    jFrame.showSelectHero();
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCLI.setForeground(Color.blue);
        buttonCLI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new ConsoleStartGame().Game(2);
            }
        });


        panel.add(buttonNew);
        panel.add(buttonCLI);
        panel.add(buttonExit);

        this.add(new JLabel(GuiStartGame.logoImg), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
