package Login_System;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Sign_Up extends JDialog {

    public String sUsername;
    public String sPassword;

    public static void main(String[] args) throws IOException {
        try {
            Sign_Up dialog = new Sign_Up();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Sign_Up() throws IOException {

        setBounds(0, 100, 450, 360);
        setTitle("REGISTER HERE ");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);


        // Label
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(33, 27, 82, 15);
        getContentPane().add(lblUsername);


        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(33, 62, 64, 15);
        getContentPane().add(lblPassword);


        JLabel lblPassword_C = new JLabel("CPassword");
        lblPassword_C.setBounds(33, 95, 67, 15);
        getContentPane().add(lblPassword_C);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(33, 127, 67, 15);
        getContentPane().add(lblemail);

        JLabel lblphone = new JLabel("Mobile No ");
        lblphone.setBounds(33, 159, 67, 15);
        getContentPane().add(lblphone);

        // Input Field

        final JTextField username = new JTextField();
        username.setBounds(125, 22, 250, 27);
        getContentPane().add(username);

        final JPasswordField password = new JPasswordField();
        password.setBounds(125, 57, 250, 27);
        getContentPane().add(password);

        final JPasswordField password_c = new JPasswordField();
        password_c.setBounds(125, 90, 250, 27);
        getContentPane().add(password_c);

        final JTextField email = new JTextField();
        email.setBounds(125, 121, 250, 27);
        getContentPane().add(email);

        final JTextField phone = new JTextField();
        phone.setBounds(125, 155, 250, 27);
        getContentPane().add(phone);

        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });


        login.setBounds(164, 215, 90, 30);
        getContentPane().add(login);


        JButton Signup = new JButton("Register");
        Signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String user = username.getText();
                String pass = password.getText();
                String Cpass = password_c.getText();
                String emails = email.getText();
                String phoneno = phone.getText();

                boolean user_check = user.isEmpty();
                boolean pass_check = pass.isEmpty();
                boolean Cpass_check = Cpass.isEmpty();
                boolean check = false;

                try {
                    if(pass.equals(Cpass)){
                        check = true;
                    }
                    if(check){
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_System","root","");
                        String query = "INSERT INTO java_project_user ( Username, Password, C_Password, Email, Phone_no)"+"VALUES ('"+user+"','"+pass+"','"+Cpass+"','"+emails+"','"+phoneno+"')";
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(query);
                        alert(user,pass,Cpass,emails,phoneno,query);
                        System.out.println("Data Successfully Stored in Database");
                        statement.close();
                        conn.close();
                    }
                    else {
                        password_check();
                    }


                } catch (SQLException | ClassNotFoundException ex) {
                    if (user_check && pass_check){
                        warning();
                    }

                }
            }

        });

        Signup.setBounds(40, 215, 90, 30);
        getContentPane().add(Signup);

        JButton reset = new JButton("Reset");
        reset.setBounds(288, 215, 90, 30);
        getContentPane().add(reset);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(true){
                    username.setText(null);
                    password.setText(null);
                    password_c.setText(null);
                    email.setText(null);
                    phone.setText(null);
                }
            }
        });

        JButton home = new JButton("Go Back");
        home.setBounds(164, 270, 90, 30);
        getContentPane().add(home);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home_page home = new Home_page();
                dispose();
                home.home_page();
            }
        });



    }

    void alert(String pass,String Cpass,String user,String email,String phone,String query) {

        boolean user_check = user.isEmpty();
        boolean pass_check = pass.isEmpty();
        boolean Cpass_check = Cpass.isEmpty();
        boolean email_check = email.isEmpty();
        boolean phone_check = phone.isEmpty();
        boolean query_check = query.isEmpty();

        if (!user_check && !pass_check && !Cpass_check && !email_check && !phone_check){
            JOptionPane.showMessageDialog(this,"Successfully Register ","Success",JOptionPane.INFORMATION_MESSAGE);
            Login log = new Login();
            dispose();
            log.setVisible(true);

        }
        else if (query_check){
            JOptionPane.showMessageDialog(this,"Please Enter Valid Credentials","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this,"Login Failed","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    void warning (){
        JOptionPane.showMessageDialog(this,"Please Enter Valid Credentials","Warning",JOptionPane.WARNING_MESSAGE);
    }
    void password_check(){
        JOptionPane.showMessageDialog(this,"Password Not Matched","Warning",JOptionPane.WARNING_MESSAGE);
    }

}