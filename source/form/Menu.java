package form;

import javax.swing.*;
import Components.menuButton;
import java.awt.*;

public class Menu extends JLayeredPane{
    
    public Menu() {
        this.setBounds(5, 5, 200, 705);
        this.setBackground(new Color(249,249,249));
        this.setOpaque(true);

        JLayeredPane menuLogo = new JLayeredPane();
        menuLogo.setBounds(0, 0, 200, 60);
        menuLogo.setBackground(new Color(6,90,179));
        menuLogo.setOpaque(true);

        menuLogo.add(new menuButton());

        this.add(menuLogo);

        JPanel peopleList = new JPanel();
        peopleList.setBounds(1, 52, 198, 660);
        peopleList.setBackground(new Color(229,229,229));
        this.add(peopleList);


    }
}
