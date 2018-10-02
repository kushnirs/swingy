package ua.unitfactory.swingy.view.gui;

import ua.unitfactory.swingy.view.console.ConsoleStartGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello extends JPanel {

    private JButton buttonNew = new JButton("NEW GAME");
    private JButton buttonCLI = new JButton("CLI mode");
    private JButton buttonExit = new JButton("EXIT");

    public Hello() {
        this.setPreferredSize(new Dimension(GuiStartGame.sizeWidth, GuiStartGame.sizeHeight));
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));

        buttonNew.setForeground(Color.red);

        buttonCLI.setForeground(Color.blue);

        panel.add(buttonNew);
        panel.add(buttonCLI);
        panel.add(buttonExit);

        this.add(new JLabel(GuiStartGame.logoImg), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void addActionListener(ActionListener b_new, ActionListener b_cli, ActionListener b_exit) {
        buttonNew.addActionListener(b_new);
        buttonCLI.addActionListener(b_cli);
        buttonExit.addActionListener(b_exit);
    }
}
