package Client;

import form.*;
import java.awt.Color;
import javax.swing.*;

public class Client extends JFrame {

    
    @SuppressWarnings("resource")
    Client() {
        
        this.setLayout(null);
        
        
        this.add(new Menu());

        this.add(new Chat());
        this.setSize(1360, 768);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xCCCCC));
        this.setTitle("Chat app");
        this.setVisible(true);
       
    }
    
    public static void main(String[] args) {
        new Client();
        Chat chat = new Chat();
        chat.transfer();
    }
}
