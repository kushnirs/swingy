package main.view.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GuiStartGame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int sizeWidth = 600;
    public static final int sizeHeight = 400;
    private int locationX = (screenSize.width - sizeWidth) / 2;
    private int locationY = (screenSize.height - sizeHeight) / 2;

    public static BufferedImage floorImg;
    public static ImageIcon heroImg;
    public static ImageIcon enemyImg;
    public static ImageIcon stepLImg;
    public static ImageIcon stepRImg;
    public static ImageIcon stepDImg;
    public static ImageIcon stepUImg;
    public static ImageIcon rightImg;
    public static ImageIcon leftImg;
    public static ImageIcon upImg;
    public static ImageIcon downImg;
    public static ImageIcon logoImg;

    public GuiStartGame() {
        super("Swingy");
        initIMG();
        showHello();
        setResizable(false);
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    private void initIMG() {
        try {
            // TEXTURE IMG
//            floorImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/rock.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
            floorImg = ImageIO.read(getClass().getResource("/resources/desert.jpg"));
            heroImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/hero.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            enemyImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/enemy.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

            stepLImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/steps_left.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            stepRImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/steps_right.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            stepDImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/steps_down.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            stepUImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/steps_up.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));


            // DIRECTION IMG

            rightImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/right.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));
            leftImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/left.jpg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));
            upImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/up.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));
            downImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/down.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));

            // LOGO IMG
            logoImg = new ImageIcon(new ImageIcon(getClass().getResource("/resources/logo.jpg")).getImage().getScaledInstance(sizeWidth, sizeHeight - 75, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            System.out.println("ERROR: Image not found");
            System.exit(1);
        }



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

