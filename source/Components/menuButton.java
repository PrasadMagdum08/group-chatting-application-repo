package Components;

import javax.swing.*;
import java.awt.*;

public class menuButton extends JButton{
    
    public menuButton() {

        ImageIcon image = new ImageIcon("icons\\menu-button.png");
        JLabel label = new JLabel(image);
        this.add(label);

        this.setBounds(5, 5, 50, 50);
        this.setBackground(new Color(242, 242, 242));
        this.setBorder(null);
        this.setFocusable(false);
    }
}
