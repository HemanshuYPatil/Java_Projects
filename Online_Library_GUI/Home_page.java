package Login_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;
public class Home_page extends JDialog{
    public static void main(String[] args) {
        Home_page home = new Home_page();
        home.home_page();
//        home.setVisible(true);
    }

    public void home_page(){
//        JFrame frame = new JFrame("Home Page");
        setBounds(100,100,800,700);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setTitle("Home Page");
        setResizable(true);
        setVisible(true);



        JLabel label = new JLabel("Welcome to Our Online Library");
        label.setBounds(190,10,500,100);
        label.setFont(new Font("Arial",Font.BOLD,30));
        add(label);
        label.setVisible(true);

        JLabel label1 = new JLabel("To make a part our Library Please Login ");
        label1.setBounds(250,65,800,100);
        label1.setFont(new Font("Arial",Font.ITALIC,17));
        add(label1);
        label1.setVisible(true);

        JLabel owner = new JLabel("Made by Hemanshu Patil");
        owner.setBounds(309,610,1000,75);
        owner.setFont(new Font("Arial",Font.PLAIN,15));
        add(owner);

        // Buttons
        JButton login = new JButton("Login");
        login.setFont(new Font("Arial",Font.PLAIN,25));
        login.setBounds(315,220,150,45);
        add(login);
        login.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
                log.setVisible(true);
            }
        });

        JButton sign = new JButton("Sign Up");
        sign.setFont(new Font("Arial",Font.PLAIN,25));
        sign.setBounds(315,310,150,45);
        add(sign);
        sign.setVisible(true);

        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    Sign_Up sign = new Sign_Up();
                    sign.setVisible(true);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                sign.setVisible(true);
            }
        });

        JButton exit = new JButton("Exit");
        exit.setFont(new Font("Arial",Font.PLAIN,25));
        exit.setBounds(315,400,150,45);
        add(exit);
//        exit.setVisible(true);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(exit,"Are You Sure to Exit");
                if(a == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }
}