package Components;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class fileChooser extends JFrame{
    
    final File[] fileToSend = new File[1];
    
    public fileChooser() {
        
        this.setTitle("File Sharing ");
        this.setSize(600, 300);
        this.setLocation(220, 390);
        this.setUndecorated(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        JLabel fileName = new JLabel("Choose a file");
        fileName.setFont(new Font(null, Font.BOLD, 20));
        fileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        fileName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel button = new JPanel();
        button.setBorder(new EmptyBorder(75, 0, 10, 0));
        
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
//                        Socket socket = new Socket("localhost", 1234);
                        
//                        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                        
                        String filename = fileToSend[0].getName();
                        byte[] fileNameBytes = filename.getBytes();
                        
                        byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                        fileInputStream.read(fileContentBytes);
                        
//                        dout.writeInt(fileNameBytes.length);
//                        dout.write(fileNameBytes);
                        
//                        dout.writeInt(fileContentBytes.length);
//                        dout.write(fileContentBytes);
                        
                        
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

class myFile {
    
    private int id;
    private String name;
    private byte[] data;
    private String fileExtension;
    
    public myFile(int id, String name, byte[] data, String fileExtension) {
        this.id=id;
        this.name=name;
        this.data=data;
        this.fileExtension=fileExtension;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setData(byte[] data){
        this.data=data;
    }
    public void setFileExtension(String fileExtension){
        this.fileExtension=fileExtension;
    }
    
    public int getId() {
        return id;
    }
}

class Server extends JFrame{
    
    static ArrayList<myFile> myfile = new ArrayList<>();
    
    public Server() {
        int fieldID = 0;
        
//        JFrame f = new JFrame("Server");
        this.setSize(400, 400);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
