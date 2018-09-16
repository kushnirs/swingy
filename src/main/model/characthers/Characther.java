package main.model.characthers;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Characther {
    protected String name;
    protected int level;
    protected int attack;
    protected int defense;
    protected int hp;

    public Characther(String name, int level, int attack, int defense, int hp) {
        this.name = name;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }


    // Getter
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    //Setter
    public void setHealth(int health) {
        this.hp = health;
    }
}
