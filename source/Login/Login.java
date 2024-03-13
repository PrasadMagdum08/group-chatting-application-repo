package Login;

import Client.Client;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener{

    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel =new JLabel("User ID ");
    JLabel userPasswardLabel =new JLabel("Passward ");
    JLabel messageLabel = new JLabel();
    HashMap<String,String> logininfo = new HashMap<String,String>();
    
    public Login(HashMap<String,String>logininfoOriginal)
    {
        logininfo = logininfoOriginal;

        ImageIcon dypcetCampus = new ImageIcon(ClassLoader.getSystemResource("img/insiiiii.jpg"));
        Image img1 = dypcetCampus.getImage().getScaledInstance(1270, 640, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img1);;
        JLabel BackgroundImage = new JLabel(img2);
        BackgroundImage.setOpaque(true);
        BackgroundImage.setBounds(40, 40, 1270, 640);
        this.add(BackgroundImage);


        JLayeredPane LoginPanel = new JLayeredPane();
        LoginPanel.setBounds(750, 70, 400, 500);
        LoginPanel.setBackground(new Color(229, 229, 229));
        LoginPanel.setOpaque(true);
        BackgroundImage.add(LoginPanel);

        userIDLabel.setBounds(50, 100, 75, 25);
        userIDLabel.setForeground(Color.white);
        userIDLabel.setFont(new Font("roboto", Font.BOLD, 14));
        LoginPanel.add(userIDLabel);

        userPasswardLabel.setBounds(50, 150, 75, 25);
        userPasswardLabel.setFont(new Font("roboto", Font.BOLD, 14));
        userPasswardLabel.setForeground(Color.white);
        LoginPanel.add(userPasswardLabel);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));
        LoginPanel.add(messageLabel);

        userIDField.setBounds(125,100,200,25);
        userIDField.setBorder(BorderFactory.createEmptyBorder(0, 5,0,0));
        userIDField.setFont(new Font("roboto", Font.BOLD, 14));
        LoginPanel.add(userIDField);

        userPasswordField.setBounds(125,150,200,25);
        userPasswordField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        userPasswordField.setFont(new Font("roboto", Font.BOLD, 14));
        LoginPanel.add(userPasswordField);

        loginButton.setBounds(125,300,200,40);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setBackground(new Color(233,49,5));
        loginButton.setBorder(null);
        LoginPanel.add(loginButton);

        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
//        LoginPanel.add(resetButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1366,768);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==resetButton)
        {
            userIDField.setText("");
            userPasswordField.setText("");
        }
        if(e.getSource()==loginButton)
        {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(logininfo.containsKey(userID))
            {
                if(logininfo.get(userID).equals(password))
                {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    this.dispose();
                    Client client = new Client(userID);
                }
                else
                {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Invalid Passward!!\nTry Again.");                    
                }
            }
            else
            {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
        }
    }
    
}
