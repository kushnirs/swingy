package swingy.view.console;

import java.util.Scanner;

public class ConsoleStartGame {

    static void startGame (){
        System.out.println("/----------------------Swingy----------------------/");
        System.out.println("/Add number of command:                            /");
        System.out.println("/                                                  /");
        System.out.println("/1.Select a previously created Hero                /");
        System.out.println("/2.Create new Hero                                 /");
        System.out.println("/--------------------------------------------------/");

        System.out.print("command: ");
        Scanner in = new Scanner(System.in);
        int input;
        while ((input = in.nextInt()) != 1 || input != 2)
        {
            if (input == 1)
                selectHero();
            else
                createHero();
        }
    }

    static private void selectHero() {
        System.out.println("/----------------------SelectHero------------------/");
        System.out.println("/Add number of Hero:                               /");
        System.out.println("/                                                  /");
        System.out.println("/--------------------------------------------------/");
    }

    static private void createHero() {
        System.out.println("/----------------------CreateHero------------------/");
        System.out.println("/                                                  /");
        System.out.println("/--------------------------------------------------/");
    }
}
