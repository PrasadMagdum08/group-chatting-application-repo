package form;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Components.fileChooserButton;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Chat extends JLayeredPane implements ActionListener, KeyListener{

    private JPanel chat;
    private JTextField text;
    private Box vertical = Box.createVerticalBox();
    private String name = "Prasad";

    BufferedReader reader;
    BufferedWriter writer;

    public Chat() {

        this.setBounds(205, 5, 1140, 705);
        this.setOpaque(true);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(6,90,179));
        p1.setBounds(5, 0, 1140, 60);
        p1.setLayout(null);
        this.add(p1);
        
        JLabel name = new JLabel("DYPCET");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);
        
        JLabel status = new JLabel("Prasad, Kunal, Samarjeet, Shailesh....");
        status.setBounds(110, 35, 160, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        JLayeredPane outerChat = new JLayeredPane();
        outerChat.setBounds(5, 65, 1140, 590);
        outerChat.setBackground(Color.white);
        outerChat.setOpaque(true);
        this.add(outerChat);

        chat = new JPanel();
        chat.setBounds(10, 10, 1115, 590);
        chat.setBackground(Color.WHITE);
        outerChat.add(chat);

        JLabel bottom = new JLabel();
        bottom.setBounds(5, 660, 1145, 60);
        bottom.setBackground(new Color(242, 242, 242));
        bottom.setOpaque(true);
        bottom.setLayout(new FlowLayout());
        // this.add(bottom);

        this.add(new fileChooserButton());

        text = new JTextField();
        text.setBounds(100, 663, 900, 40);
        text.setFont(new Font("roboto", Font.PLAIN, 16));
        text.setBackground(new Color(229,229,229));
        text.addKeyListener(this);
        text.setOpaque(true);
        text.setBorder(null);
        text.setFocusable(true);
        this.add(text);
        

        ImageIcon i = new ImageIcon("icons\\Send.png");
        Image i1 = i.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JButton send = new JButton(i2);
        send.setBounds(1110, 670, 25, 25);
        send.setBackground(new Color(0xCCCCCC));
        send.setForeground(Color.WHITE);
        send.setFocusable(false);
        send.addActionListener(this);
        send.setBorder(null);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setOpaque(true);
        this.add(send);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p></html>";

            JPanel p2 = formatLabel(out);

            chat.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(Color.WHITE);
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(10));

            chat.add(vertical, BorderLayout.PAGE_START);

            try {
                writer.write(out);
                writer.write("\r\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            text.setText("");

            this.repaint();
            this.invalidate();
            this.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("key relesed: " + ke.getKeyCode());
        if(ke.getKeyCode() == 16) {

            try {
                String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p></html>";
    
                JPanel p2 = formatLabel(out);
    
                chat.setLayout(new BorderLayout());
    
                JPanel right = new JPanel(new BorderLayout());
                right.setBackground(Color.WHITE);
                right.add(p2, BorderLayout.LINE_END);
                vertical.add(right);
                vertical.add(Box.createVerticalStrut(10));
    
                chat.add(vertical, BorderLayout.PAGE_START);
    
                try {
                    writer.write(out);
                    writer.write("\r\n");
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    
                text.setText("");
    
                this.repaint();
                this.invalidate();
                this.validate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel(out);
        output.setForeground(Color.white);
        output.setFont(new Font("roboto", Font.BOLD, 14));
        output.setBackground(new Color(6,90,179));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(0, 10, 0, 35));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    
    @Override
    public void keyPressed(KeyEvent arg0) {

    }


    @Override
    public void keyTyped(KeyEvent arg0) {

    }
}