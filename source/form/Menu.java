package Compoenets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menuButton extends JButton implements MouseListener {
    public menuButton() {
    
        this.setBounds(5, 5, 60, 50);
        this.setBorder(null);
        this.setBackground(Color.white);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
         JLayeredPane menuList = new JLayeredPane();
         menuList.setBounds(0, 0, 100, 150);
         menuList.setBackground(new Color(0xCCCCCC));
         menuList.setOpaque(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    
}
