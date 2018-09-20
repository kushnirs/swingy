package main.model.characthers;

import main.model.artifacts.Armor;
import main.model.artifacts.Helm;
import main.model.artifacts.Weapon;

/**
 * Created by skushnir on 12.09.2018.
 */
public class Hero extends Characther {
    private int herohp;
    private int x = 0;
    private int y = 0;
    private int experience;
    private Armor armor;
    private Helm helm;
    private Weapon weapon;

    public Hero(String name, Characther unit, Armor armor, Helm helm, Weapon weapon) {
        super(name, unit.getLevel(), unit.getAttack(), unit.getDefense(), unit.getHp());
        this.level = 1;
        this.experience = 0;
        this.armor = armor;
        this.helm = helm;
        this.weapon = weapon;

        this.herohp = this.hp;
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

    public Armor getArmor() {
        return armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    //Setter
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setlevel(int level) {
        this.level = level;
    }

    public void setArmor (Armor armor) {
        this.armor = armor;
    }

    public void setHelm (Helm helm) {
        this.helm = helm;
    }

    public void setWeapon (Weapon weapon) {
        this.weapon = weapon;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void  checkExperience() {
        int nextlevel = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
        if (experience >= nextlevel);
            this.level++;
    }

    public void updateHero() {
        hp = herohp;
        if (armor != null)
            armor.setQuality(100);
        if (helm != null)
            helm.setQuality(100);
        if (weapon != null)
            weapon.setQuality(100);
    }

}
