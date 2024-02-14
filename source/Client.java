import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
// import java.net.*;

public class Client1 implements ActionListener{
    JFrame frame = new JFrame();
    JPanel statusPanel;
    JPanel sideBarPanel;
    static JPanel chatPanel;
    JTextField text;
    JButton Send = new JButton("Send");
    static Box vertical = Box.createVerticalBox();
    static DataOutput dout;
    
    public Client1() {
        frame.setBounds(10, 10, 1000, 700);
        // frame.getContentPane().setBackground(new Color(0xCCCCCC));
        frame.setTitle("Chat with DYPCET");
        ImageIcon dypLogo = new ImageIcon("img\\dypcetlogo.jpg");
        frame.setIconImage(dypLogo.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        statusPanel = new JPanel();
        statusPanel.setBounds(272, 0, 1400, 50);
        statusPanel.setBackground(new Color(255,255,255,255));
        statusPanel.setLayout(new BorderLayout());
        frame.add(statusPanel);
        
        sideBarPanel = new JPanel(); 
        sideBarPanel.setBounds(0, 0, 270, 1400);
        sideBarPanel.setBackground(new Color(255, 255, 255, 255));
        sideBarPanel.setLayout(new BorderLayout());
        frame.add(sideBarPanel);

        // ImageIcon image = new ImageIcon("img\\OIP.jpeg");
        chatPanel = new JPanel();
        chatPanel.setBounds(272, 52, 1060, 610);
        chatPanel.setBackground(new Color(255,255,255,255));
        // chatPanel.setLayout(new BorderLayout());
        // JLabel label = new JLabel(image);
        // chatPanel.add(label);
        frame.add(chatPanel);
    
        text = new JTextField();
        text.setBounds(330, 668, 900, 40);
        text.setFont(new Font("roboto", Font.PLAIN, 16));
        text.setLayout(new BorderLayout());
        frame.add(text);
        
        Send.setBounds(1295, 668, 63, 40);
        Send.addActionListener(this);
        frame.add(Send);

        

        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Send){
            String print = text.getText();

            // JLabel output = new JLabel(print);

            JPanel panel2 = formatLabel(print);
            // panel2.add(output);

            chatPanel.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(panel2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            chatPanel.add(vertical, BorderLayout.PAGE_START);


            frame.repaint();
            frame.validate();

        }
    }


    public static JPanel formatLabel(String print) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel(print);
        output.setFont(new Font("roboto", Font.PLAIN, 12));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(10, 10, 10, 40));

        panel.add(output);

        return panel;

    }

    public static void main(String args[]) throws IOException{
        new Client1();

        }

    }

