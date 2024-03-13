package Client;

import form.Chat;
import form.Menu;
import java.awt.Color;
import javax.swing.*;

public class Client {
    
    static private form.Chat one;
    static JFrame frame = new JFrame();

    public Client(String userID) {
        one = new Chat(userID);
        Thread thread = new Thread(one);
        thread.start();

        frame.setLayout(null);

        frame.add(new Menu());

        frame.add(one);
        frame.setSize(1360, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xCCCCCC));
        frame.setTitle("Chat app");
        frame.setVisible(true);
    }

}
