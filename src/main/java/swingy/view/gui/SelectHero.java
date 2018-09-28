package swingy.view.gui;

import swingy.model.characthers.Hero;
import swingy.storage.HeroStorage;
import swingy.view.console.ConsoleStartGame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by skushnir on 12.09.2018.
 */
public class SelectHero extends JPanel {

    private JButton buttonNew = new JButton("New Hero");
    private JButton buttonSelect = new JButton("Select Hero");
    private JButton buttonCLI = new JButton("CLI mode");
    private JTextArea heroInfo = new JTextArea(10, 20);

    public SelectHero(final GuiStartGame jFrame) {

        String[] elements = new String[GuiStartGame.heroDB.size()];
        for (int i = 0; i < GuiStartGame.heroDB.size(); i++)
            elements[i] = GuiStartGame.heroDB.get(i).getName();

        final JList heroList = new JList(elements);
        heroList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               int i = heroList.getSelectedIndex();
               String info = "Type: " +  GuiStartGame.heroDB.get(i).getType() + "\n" +
                       "Level: " +  GuiStartGame.heroDB.get(i).getLevel() + "\n" +
                       "Experience: " +  GuiStartGame.heroDB.get(i).getExperience() + "\n" +
                       "Hp: " +  GuiStartGame.heroDB.get(i).getHp() + "\n" +
                       "Attack: " +  GuiStartGame.heroDB.get(i).getAttack() + "\n" +
                       "Defense: " +  GuiStartGame.heroDB.get(i).getDefense() + "\n" +
                       "Armor: " +  (GuiStartGame.heroDB.get(i).getArmor() == null ? "empty" : "available")  + "\n" +
                       "Weapon: " +  (GuiStartGame.heroDB.get(i).getWeapon() == null ? "empty" : "available") + "\n" +
                       "Helm: " +  (GuiStartGame.heroDB.get(i).getHelm() == null ? "empty" : "available");
               heroInfo.setText(info);
            }
        });
        JScrollPane scrollList = new JScrollPane(heroList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.setPreferredSize(new Dimension(GuiStartGame.sizeWidth, GuiStartGame.sizeHeight));
        this.setLayout(new BorderLayout());

        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new GridLayout(0, 2, 10,10));
        heroList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                buttonSelect.setEnabled(true);
            }
        });
        // HERO_LIST_PANEL
        JPanel heroListPanel = new JPanel(new GridLayout());
        heroListPanel.setBorder(BorderFactory.createTitledBorder("Hero name:"));
        heroListPanel.add(scrollList);

        // HERO_INFO_PANEL
        heroInfo.setEditable(false);
        JPanel heroInfoPanel = new JPanel(new GridLayout());
        heroInfoPanel.add(heroInfo);
        heroInfoPanel.setBorder(BorderFactory.createTitledBorder("Hero info:"));

        //HERO_PANEL
        heroPanel.add(heroListPanel);
        heroPanel.add(heroInfoPanel);

        //BUTTON_PANEL
        JPanel buttonPanel= new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 10,10));
        buttonNew.setForeground(Color.red);
        buttonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame.showNewHero();
            }
        });

        buttonSelect.setForeground(Color.red);
        buttonSelect.setEnabled(false);
        buttonSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonCLI.setForeground(Color.blue);
        buttonCLI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new ConsoleStartGame().Game(2);
            }
        });

        buttonPanel.add(buttonSelect);
        buttonPanel.add(buttonNew);
        buttonPanel.add(buttonCLI);


        // JLABEL_PANEL
        JPanel selectPanel = new JPanel();
        selectPanel.setBackground(Color.red);
        selectPanel.add(new JLabel("SELECT HERO"));

        // SET MAIN LAYOUT

        this.add(selectPanel, BorderLayout.NORTH);
        this.add(heroPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
