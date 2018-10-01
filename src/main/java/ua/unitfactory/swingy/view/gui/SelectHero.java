package ua.unitfactory.swingy.view.gui;

import ua.unitfactory.swingy.view.console.ConsoleStartGame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by skushnir on 12.09.2018.
 */
public class SelectHero extends JPanel {

    private JButton buttonNew = new JButton("New Hero");
    private JButton buttonSelect = new JButton("Select Hero");
    private JButton buttonCLI = new JButton("CLI mode");
    private JTextArea heroInfo = new JTextArea(10, 20);
    private JList heroList = null;

    public SelectHero(final GuiStartGame jFrame) {

        String[] elements = new String[GuiStartGame.heroDB.size()];
        for (int i = 0; i < GuiStartGame.heroDB.size(); i++)
            elements[i] = (i+1) + ". " + GuiStartGame.heroDB.get(i).getName();

        heroList = new JList(elements);
        JScrollPane scrollList = new JScrollPane(heroList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.setPreferredSize(new Dimension(GuiStartGame.sizeWidth, GuiStartGame.sizeHeight));
        this.setLayout(new BorderLayout());

        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new GridLayout(0, 2, 10,10));

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

        buttonSelect.setForeground(Color.red);
        buttonSelect.setEnabled(false);

        buttonCLI.setForeground(Color.blue);

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

    public void addListener(ListSelectionListener list, ActionListener new_b, ActionListener select_b, ActionListener cli_b) {
        heroList.addListSelectionListener(list);
        buttonNew.addActionListener(new_b);
        buttonSelect.addActionListener(select_b);
        buttonCLI.addActionListener(cli_b);
    }


    // GETTERS

    public JButton getButtonSelect() {
        return buttonSelect;
    }

    public JTextArea getHeroInfo() {
        return heroInfo;
    }

    public JList getHeroList() {
        return heroList;
    }
}
