package main;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.text.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import form.Chat;
import form.Menu;

public class Client extends JFrame implements Runnable {
    
    JTextField text;
    JPanel chat;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    
    BufferedReader reader;
    BufferedWriter writer;
    String name = "Pooja";
    
    @SuppressWarnings("resource")
    Client() {
        
        this.setLayout(null);

        this.add(new Chat());
        this.add(new Menu());

        this.setSize(1036, 600);
        this.setLocationRelativeTo(null);
        // this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(0xCCCCCC));
        this.setVisible(true);
        
        try {
            System.out.println("\nClient Started!");
            Socket socket = new Socket("localhost", 2003);
            System.out.println("\nClient connected");
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
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
                
                chat.add(vertical, BorderLayout.PAGE_START);
                
                this.repaint();
                this.invalidate();
                this.validate();   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel(out);
        output.setFont(new Font("roboto", Font.BOLD, 14));
        output.setBackground(new Color(92, 188, 255));
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
    
    public static void main(String[] args) {
        Client one = new Client();
        Thread t1 = new Thread(one);
        t1.start();
    }

}
