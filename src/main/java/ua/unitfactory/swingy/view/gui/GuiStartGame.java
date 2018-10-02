package ua.unitfactory.swingy.view.gui;

import ua.unitfactory.swingy.controller.HelloController;
import ua.unitfactory.swingy.controller.NewHeroController;
import ua.unitfactory.swingy.controller.PlayMissionController;
import ua.unitfactory.swingy.controller.SelectHeroController;
import ua.unitfactory.swingy.model.characthers.Hero;
import ua.unitfactory.swingy.storage.HeroStorage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class GuiStartGame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int sizeWidth = 600;
    public static final int sizeHeight = 400;
    int locationX = (screenSize.width - sizeWidth) / 2;
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

    public static ArrayList<Hero> heroDB = (new HeroStorage()).selectFromTable();

    {
        initIMG();
    }

    public GuiStartGame() {
        super("Swingy");
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showHello();
        setVisible(true);
    }


    private void initIMG() {
        try {
            // TEXTURE IMG

            floorImg = ImageIO.read(getClass().getResource("/desert.jpg"));
            heroImg = new ImageIcon(new ImageIcon(getClass().getResource("/hero.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            enemyImg = new ImageIcon(new ImageIcon(getClass().getResource("/enemy.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

            stepLImg = new ImageIcon(new ImageIcon(getClass().getResource("/steps_left.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            stepRImg = new ImageIcon(new ImageIcon(getClass().getResource("/steps_right.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            stepDImg = new ImageIcon(new ImageIcon(getClass().getResource("/steps_down.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            stepUImg = new ImageIcon(new ImageIcon(getClass().getResource("/steps_up.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));


            // DIRECTION IMG

            rightImg = new ImageIcon(new ImageIcon(getClass().getResource("/right.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));
            leftImg = new ImageIcon(new ImageIcon(getClass().getResource("/left.jpg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));
            upImg = new ImageIcon(new ImageIcon(getClass().getResource("/up.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));
            downImg = new ImageIcon(new ImageIcon(getClass().getResource("/down.jpeg")).getImage().getScaledInstance(70, 40, Image.SCALE_DEFAULT));

            // LOGO IMG
            logoImg = new ImageIcon(new ImageIcon(getClass().getResource("/logo.jpg")).getImage().getScaledInstance(sizeWidth, sizeHeight - 75, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            System.out.println("ERROR: Image not found");
            System.exit(1);
        }



    }

    public void showHello() {
        Hello hello = new Hello();
        HelloController.addHelloActionListener(hello, this);
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        setContentPane(hello);
        pack();
    }

    public void showNewHero() {
        NewHero newHero = new NewHero(this);
        NewHeroController.addNewHeroListener(newHero, this);
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        setContentPane(newHero);
        pack();
    }

    public void showSelectHero() {
        SelectHero selectHero = new SelectHero(this);
        SelectHeroController.addSelectHeroListener(selectHero, this);
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        setContentPane(selectHero);
        pack();
    }

    public void showPlayMission() {
        PlayMission playMission = new PlayMission(this);
        PlayMissionController.addPlayMissionActionListener(playMission,this);
        this.setBounds(locationX, (screenSize.height - 900) / 2, sizeWidth, 900);
        setContentPane(playMission);
        pack();
    }

}

