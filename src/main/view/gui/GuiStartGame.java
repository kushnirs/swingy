package main.view.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GuiStartGame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int sizeWidth = 600;
    private int sizeHeight = 400;
    private int locationX = (screenSize.width - sizeWidth) / 2;
    private int locationY = (screenSize.height - sizeHeight) / 2;

    public GuiStartGame() {
        super("Swingy");
        showHello();
        setResizable(false);
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void showHello() {
        setContentPane(new Hello(this));
        pack();
    }

    public void showNewHero() {
        setContentPane(new NewHero(this));
        pack();
    }

    public void showSelectHero() {
        setContentPane(new SelectHero(this));
        pack();
    }

    public void showPlayMission() {
        setContentPane(new PlayMission(this));
        pack();
    }

}
//
//    static class ImagePanel extends JPanel {
//
//        private Image img;
//
//        public ImagePanel(String img) {
//            this(new ImageIcon(img).getImage());
//        }
//
//        public ImagePanel(Image img) {
//            this.img = img;
//            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
//            setPreferredSize(size);
//            setMinimumSize(size);
//            setMaximumSize(size);
//            setSize(size);
//            setLayout(null);
//        }
//
//        public void paintComponent(Graphics g) {
//            g.drawImage(img, 0, 0, null);
//        }
//    }



    // IMAGE BACKGROUND// IMAGE BACKGROUND// IMAGE BACKGROUND// IMAGE BACKGROUND// IMAGE BACKGROUND// IMAGE BACKGROUND





//    private JButton buttonNew = new JButton("New Game");
//    private JButton buttonExit = new JButton("Exit");
//    private JLabel logo = new JLabel(new ImageIcon("/Users/sergee/projects/main/src/resources/images.png"), JLabel.CENTER);
//
//    public GuiStartGame() {
//        super("Swingy");
//        this.setBounds(100,100,300,550);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        String[]    colName = {"name"};
//        String[][] lol = {{"lol1"}, {"lol2"}, {"lol3"}};
////        Container container = this.getContentPane();
//
//        this.add(new JTable(lol, colName));
//        logo.setBounds(JLabel.CENTER, 10, 100, 100);
//        this.add(logo);
//
//        buttonNew.addActionListener(new ButtonEventListener());
//        buttonNew.setBounds(JButton.CENTER,200, 100, 20);
//        this.add(buttonNew);
//
//        buttonExit.addActionListener(new CloseListener());
//        buttonExit.setBounds(JButton.CENTER,300, 100, 20);
//        this.add(buttonExit);
//    }
//
//
//
//    }

