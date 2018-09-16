package main;

import main.view.console.ConsoleStartGame;
import main.view.gui.GuiStartGame;
import main.model.characthers.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Main {
    public static Hero hero;
    public static int map_size;
    public static int[] map;

    public static void main(String[] args) {

//        GuiStartGame app = new GuiStartGame();
//        app.setVisible(true);

        try {
            if (args.length != 1)
                throw new Exception("Wrong amount of argument(must be 1)");
            else if (args[0].compareTo("gui") != 0 && args[0].compareTo("console") != 0)
                throw new Exception("Wrong argument");
            else if (args[0].compareTo("gui") == 0)
                new GuiStartGame();
            else
                ConsoleStartGame.Game();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}