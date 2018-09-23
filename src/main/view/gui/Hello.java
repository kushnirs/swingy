package main.view.gui;

import main.view.console.ConsoleStartGame;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Hello extends JPanel {

    private JButton buttonNew = new JButton("NEW GAME");
    private JButton buttonCLI = new JButton("CLI mode");
    private JButton buttonExit = new JButton("EXIT");

    public Hello(GuiStartGame jFrame) {
        this.setPreferredSize(new Dimension(GuiStartGame.sizeWidth, GuiStartGame.sizeHeight));
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));

        buttonNew.setForeground(Color.red);
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
        buttonCLI.setForeground(Color.blue);
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

        this.add(new JLabel(GuiStartGame.logoImg), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private static class JGradientButton extends JButton {
        private JGradientButton() {
            super("Gradient Button");
            setContentAreaFilled(false);
            setFocusPainted(false); // used for demonstration
        }

        @Override
        protected void paintComponent(Graphics g) {
            final Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0),
                    Color.WHITE,
                    new Point(0, getHeight()),
                    Color.PINK.darker()));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();

            super.paintComponent(g);
        }

        public static JGradientButton newInstance() {
            return new JGradientButton();
        }
    }
}
