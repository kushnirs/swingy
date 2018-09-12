package swingy.model.characthers;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Characther {
    private String name;
    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hp;

    public Characther(String name, int level, int experience, int attack, int defense, int hp) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }

}
