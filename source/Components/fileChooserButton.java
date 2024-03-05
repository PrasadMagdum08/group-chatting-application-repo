package Components;

import java.awt.*;
import javax.swing.*;

public class fileChooserButton extends JButton{
    
    public fileChooserButton() {


        ImageIcon image = new ImageIcon("icons\\file-chooser-30.png");
        JLabel label = new JLabel(image);
        this.add(label);

        this.setBounds(5, 663, 90, 40);
        this.setBackground(new Color(242, 242, 242));
        this.setOpaque(true);
        this.setFocusable(true);
        this.setBorder(null);
    }
}
