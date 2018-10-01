package swingy.model.characthers;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Characther {
    @NotNull
    @Size(min = 1, message = "Characther Name must be at least 1 character")
    protected String name;
    @Min(value = 1, message = "Characther Level should not be less than 1")
    protected int level;
    protected int attack;
    protected int defense;
    @Min(value = 0, message = "Characther Hp should not be less than 0")
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
    public void setHp(int health) {
        this.hp = health;
    }
}
