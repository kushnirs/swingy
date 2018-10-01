package swingy.storage;

import swingy.Main;
import swingy.model.artifacts.Armor;
import swingy.model.artifacts.Helm;
import swingy.model.artifacts.Weapon;
import swingy.model.characthers.Hero;
import swingy.util.CharactherFactory;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Created by skushnir on 12.09.2018.
 */
public class HeroStorage {
    private Statement stmt = null;
    private Connection conn = null;

    public HeroStorage() {

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/herodb",
                    "sa", "");
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS HERO(id int NOT NULL AUTO_INCREMENT, name text, type text, " +
                    "level int, experience int, armor text, helm text, weapon text)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Hero> selectFromTable() {
        String sql = "SELECT id, name, type, level, experience, armor, helm, weapon FROM HERO";
        ArrayList<Hero> tmp = new ArrayList<Hero>();
        try {
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                Armor armor = rs.getString("armor").isEmpty() ? null : new Armor(rs.getString("armor"));
                Helm helm = rs.getString("helm").isEmpty() ? null : new Helm(rs.getString("helm"));
                Weapon weapon = rs.getString("weapon").isEmpty() ? null : new Weapon(rs.getString("weapon"));
                tmp.add(CharactherFactory.createNewHero(rs.getString("name"), CharactherFactory.Factory(rs.getString("type")), armor, helm, weapon));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public void insertIntoTable() {
        try {
            if (Main.hero == null)
                return;
            String armor = Main.hero.getArmor() == null ? "" : Main.hero.getArmor().getName();
            String helm = Main.hero.getHelm() == null ? "" : Main.hero.getHelm().getName();
            String weapon = Main.hero.getWeapon() == null ? "" : Main.hero.getWeapon().getName();
            String sql = "INSERT INTO HERO(name, type, level, experience, armor, helm, weapon) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Main.hero.getName());
            pstmt.setString(2, Main.hero.getType());
            pstmt.setInt(3, Main.hero.getLevel());
            pstmt.setInt(4, Main.hero.getExperience());
            pstmt.setString(5, armor);
            pstmt.setString(6, helm);
            pstmt.setString(7, weapon);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
