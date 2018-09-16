package swingy.model.characthers;

import swingy.model.artifacts.Armor;
import swingy.model.artifacts.Helm;
import swingy.model.artifacts.Weapon;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Hero extends Characther {
    private int x = 0;
    private int y = 0;
    private int experience;
    private Armor armor;
    private Helm helm;
    private Weapon weapon;

    public Hero(String name, Characther unit, Armor armor, Helm helm, Weapon weapon) {
        super(name, unit.getLevel(), unit.getAttack(), unit.getDefense(), 100);
        this.level = 1;
        this.experience = 0;
        this.armor = armor;
        this.helm = helm;
        this.weapon = weapon;
    }

    //Getter
    public int getExperience() {
        return experience;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Setter
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void  checkExperience() {
        int nextlevel = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
        if (experience >= nextlevel);
            this.level++;
    }

}
