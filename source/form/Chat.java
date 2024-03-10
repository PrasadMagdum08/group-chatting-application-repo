package form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.EmptyBorder;



public class Chat extends JLayeredPane implements ActionListener, Runnable{
    
    
    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();    
    BufferedReader reader;
    BufferedWriter writer;
    String name = "Kaleen Bhaiya";
    JButton fileChooser;
    
    
    @SuppressWarnings("resource")
    public Chat() {


        fileChooser = new JButton();
        fileChooser.setBounds(25, 660, 50, 40);
        fileChooser.setBackground(Color.white);
        fileChooser.addActionListener(this);
        fileChooser.setBorder(null);
        this.add(fileChooser);
        
        

        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(6,90,179));
        p1.setBounds(0, 0, 1145, 60);
        p1.setLayout(null);
        this.add(p1);
        
        JLabel name = new JLabel("Mirzapur");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);
        
        JLabel status = new JLabel("Kaleen, Guddu, Bablu, Sweety, IG Dubey, Shukla");
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
        a1.setBounds(20, 70, 1110, 585);
        a1.setBackground(Color.WHITE);
        outerLayerChat.add(a1);   
        
        text = new JTextField();
        text.setBounds(100, 660, 900, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        text.setBorder(null);
        this.add(text);
        
        JButton send = new JButton("Send");
        send.setBounds(1050, 660, 50, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFocusable(false);
        send.setBorder(null);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        this.add(send);
        
        
        this.setBounds(210, 5, 1145, 705);
        this.setBackground(new Color(229, 229, 229));
        this.setOpaque(true);
        
        try {
            Socket socket = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p></html>";

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(Color.WHITE);
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            try {
                writer.write(out);
                writer.write("\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            text.setText("");

            this.repaint();
            this.invalidate();
            this.validate();   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(ae.getSource() == fileChooser){
            @SuppressWarnings("unused")
            Components.fileChooser fc = new Components.fileChooser();
        }
    }
    
    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma", Font.BOLD, 12));
        output.setForeground(Color.white);
        output.setBackground(new Color(6,90,179));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(10, 10, 10, 20));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    
    @Override
    public void run() {

        try {
            String msg = "";
            while(true) {
                msg = reader.readLine();
                if (msg.contains(name)) {
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
    
    public void transfer() {
        try {
        
            Chat one = new Chat();
            Thread t1 = new Thread((Runnable) one);
            t1.start();
        
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
   
    
}
