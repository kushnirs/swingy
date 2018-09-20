package main.view.gui;

import main.view.console.ConsoleStartGame;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Hello extends JPanel {

    private int sizeWidth = 600;
    private int sizeHeight = 400;

    private JButton buttonNew = new JButton("NEW GAME");
    private JButton buttonCLI = new JButton("CLI mode");
    private JButton buttonExit = new JButton("EXIT");

    public Hello(GuiStartGame jFrame) {
        this.setSize(sizeWidth, sizeHeight);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.showSelectHero();
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCLI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new ConsoleStartGame().Game(2);
            }
        });


        panel.add(buttonNew);
        panel.add(buttonCLI);
        panel.add(buttonExit);

        URL path = getClass().getResource("/resources/3.jpg");
        if (path != null)
            this.add(new JLabel(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(sizeWidth, sizeHeight - 100, Image.SCALE_DEFAULT))), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
