package main.view.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello extends JPanel {

    private int sizeWidth = 600;
    private int sizeHeight = 400;

    private JButton buttonNew = new JButton("NEW GAME");
    private JButton buttonExit = new JButton("EXIT");

    public Hello(GuiStartGame jFrame) {
        this.setSize(sizeWidth, sizeHeight);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));

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

        panel.add(buttonNew);
        panel.add(buttonExit);
        this.add(new JLabel(new ImageIcon(new ImageIcon("/Users/sergee/projects/swingy/src/resources/3.jpg").getImage().getScaledInstance(sizeWidth, sizeHeight - 100, Image.SCALE_DEFAULT))), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
