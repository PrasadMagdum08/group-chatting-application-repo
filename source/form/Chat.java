package form;

import Components.fileChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.*;



public class Chat extends JLayeredPane implements ActionListener, Runnable{
    
    Socket socket;
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();    
    BufferedReader reader;
    BufferedWriter writer;
    String userID;
    private JButton fileChooser;
    private JButton send;
    
    
    
    public Chat(String userID) {
        this.userID = userID;

        ImageIcon filechooser = new ImageIcon("icons\\Send.png");
        
        fileChooser = new JButton(filechooser);
        fileChooser.setBounds(25, 660, 50, 40);
        fileChooser.setBackground(Color.white);
        fileChooser.setOpaque(true);
        fileChooser.setFocusable(false);
        fileChooser.addActionListener(this);
        fileChooser.setBorder(null);
        
        this.add(fileChooser);
        
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(6,90,179));
        p1.setBounds(0, 0, 1145, 60);
        p1.setLayout(null);
        this.add(p1);
        
        JLabel name = new JLabel("DYPCET");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);
        
        JLabel status = new JLabel("Shailesh, Kunal, Samarjeet, Prasad");
        status.setBounds(110, 35, 160, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);
        
        JLayeredPane outerLayerChat = new JLayeredPane();
        outerLayerChat.setBounds(0, 62, 1145, 590);
        outerLayerChat.setBackground(Color.white);
        outerLayerChat.setOpaque(true);
        this.add(outerLayerChat);
        
        a1 = new JPanel();
        a1.setBounds(20, 5, 1110, 585);
        a1.setBackground(Color.WHITE);
        outerLayerChat.add(a1);   
        
        text = new JTextField();
        text.setBounds(100, 660, 900, 40);
        text.setFont(new Font("roboto", Font.BOLD, 16));
        text.setBorder(null);
        this.add(text);
        
        ImageIcon sendButton = new ImageIcon(ClassLoader.getSystemResource("icons\\Send.png"));
        Image i1 = sendButton.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        send = new JButton(i2);
        send.setBounds(1050, 660, 50, 40);
        send.setBackground(new Color(229, 229, 229));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFocusable(false);
        send.setBorder(null);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        this.add(send);
        
        
        this.setBounds(210, 5, 1145, 705);
        this.setBackground(new Color(229, 229, 229));
        this.setOpaque(true);
        
//        try {
//            socket = new Socket("localhost", 9999);
//            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == send) {

        try {
            String out = "<html><p>" + userID + "</p><p>" + text.getText() + "</p></html>";

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(Color.WHITE);
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

//            try {
//                writer.write(out);
//                writer.write("\r\n");
//                writer.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            text.setText("");

            this.repaint();
            this.invalidate();
            this.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

        if(ae.getSource() == fileChooser){
            fileChooser fc = new fileChooser();
        }
    }
    
    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel output = new JLabel(out);
        output.setFont(new Font("roboto", Font.BOLD, 14));
        output.setForeground(Color.white);
        output.setBackground(new Color(6,90,179));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(5, 5, 5, 20));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    
    public void run() {

        try {
            String msg = "";
            while(true) {
                msg = reader.readLine();
                if (msg.contains(userID)) {
                    continue;
                }
                
                JPanel panel = formatLabel(msg);
                
                JPanel left = new JPanel(new BorderLayout());
                left.setBackground(Color.WHITE);
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                
                a1.add(vertical, BorderLayout.PAGE_START);
                
                this.repaint();
                this.invalidate();
                this.validate();   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}


