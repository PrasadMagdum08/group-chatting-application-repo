package Components;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
// import Components.myFile;

public class fileChooser extends JFrame{
    
    final File[] fileToSend = new File[1];
    
    public fileChooser() {
        
        this.setTitle("File Sharing ");
        this.setSize(600, 300);
        this.setLocation(220, 390);
        this.setUndecorated(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        JPanel button = new JPanel();
        button.setBorder(new EmptyBorder(75, 0, 10, 0));
        
        JLabel fileName = new JLabel("Choose a file name");
        fileName.setBorder(new EmptyBorder(10, 0, 0, 0));
        fileName.setFont(new Font(null, Font.BOLD, 20));
        
        JButton sendFile = new JButton("Send File");
        sendFile.setPreferredSize(new Dimension(150, 75));
        sendFile.setFocusable(false);
        sendFile.setBorder(null);
        sendFile.setBackground(Color.white);
        sendFile.setFont(new Font(null, Font.BOLD, 20));
        
        JButton chooseFile = new JButton("Choose File");
        chooseFile.setPreferredSize(new Dimension(150, 75));
        chooseFile.setFocusable(false);
        chooseFile.setBorder(null);
        chooseFile.setBackground(Color.white);
        chooseFile.setFont(new Font(null, Font.BOLD, 20));
        
        button.add(sendFile);
        button.add(chooseFile);
        
        chooseFile.addActionListener(new ActionListener() {
            
            @Override
              public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a file");
                
                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToSend[0] = fileChooser.getSelectedFile();
                    fileName.setText("The file you want to send is: " + fileToSend[0].getName());
                }
            }
        });
        
        sendFile.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                if(fileToSend[0] == null) {
                    fileName.setText("Please choose a file first");
                }
                else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                        Socket socket = new Socket("localhost", 2003);
                        
                        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                        
                        String filename = fileToSend[0].getName();
                        byte[] fileNameBytes = filename.getBytes();
                        
                        byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                        fileInputStream.read(fileContentBytes);
                        
                        dout.writeInt(fileNameBytes.length);
                        dout.write(fileNameBytes);
                        
                        dout.writeInt(fileContentBytes.length);
                        dout.write(fileContentBytes);
//                        
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }              
                }
            }
        });
        
        this.add(fileName);
        this.add(button);
        this.setVisible(true);
    }
    
}




