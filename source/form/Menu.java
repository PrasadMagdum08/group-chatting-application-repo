package form;

import javax.swing.*;
import java.awt.*;
import Components.menuButton;

public class Menu extends JLayeredPane {
    public Menu() {
        
        JLayeredPane menuPanel = new JLayeredPane();
        menuPanel.setBounds(0, 0, 200, 60);
        menuPanel.setBackground(new Color(6, 90, 179));
        menuPanel.setOpaque(true);
        this.add(menuPanel);
        
        menuPanel.add(new menuButton());
        
        this.setBounds(5, 5, 200, 705);
        this.setBackground(new Color(249, 249, 249));
        this.setOpaque(true);
    }
}
