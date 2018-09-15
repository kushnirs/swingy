package swingy.util;

import swingy.model.characthers.*;

/**
 * Created by skushnir on 12.09.2018.
 */
public class CharactherFactory {
    static public Characther Factory(String type) {
        if (type.compareTo("BlackMage") == 0)
            return new BlackMage();
        else if (type.compareTo("Elf") == 0)
            return new Elf();
        else if (type.compareTo("Orc") == 0)
            return new Orc();
        else
            return new Villain();
    }
}
