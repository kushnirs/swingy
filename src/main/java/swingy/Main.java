package swingy;

import swingy.storage.HeroStorage;
import swingy.util.CharactherFactory;
import swingy.view.console.ConsoleStartGame;
import swingy.view.gui.GuiStartGame;
import swingy.model.characthers.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Main {
    public static Hero hero;
    public static int map_size;
    public static int[] map;

    public static void main(String[] args) {

        try {
//            hero = new Hero("lol", CharactherFactory.Factory("ELF"), null, null, null);
//            new HeroStorage();
            if (args.length != 1)
                throw new Exception("Wrong amount of argument(must be 1)");
            else if (args[0].compareTo("gui") != 0 && args[0].compareTo("console") != 0)
                throw new Exception("Wrong argument");
            else if (args[0].compareTo("gui") == 0)
                new GuiStartGame();
            else
                new ConsoleStartGame().Game(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}