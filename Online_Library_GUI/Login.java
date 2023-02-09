package Login_System;

import jdk.dynalink.beans.StaticClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;

public class Login extends JDialog{

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

    }



    void alert(){
        JOptionPane.showMessageDialog(this,"Successfully Loggined ","Success",JOptionPane.INFORMATION_MESSAGE);
    }
    void  alert1(){
        JOptionPane.showMessageDialog(this,"Invalid Credentials","Error",JOptionPane.ERROR_MESSAGE);
    }


    public Login(){
        Dialog dialogOwner = null;
        JDialog dialog = new JDialog(dialogOwner);
        setBounds(100, 100, 450, 280);
        setTitle("LOGIN HERE ");
        setResizable(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Label

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(33, 28, 82, 15);
        getContentPane().add(lblUsername);


        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(33, 76, 64, 15);
        getContentPane().add(lblPassword);

        // Input Field

        final JTextField username = new JTextField();
        username.setBounds(125, 22, 250, 25);
        getContentPane().add(username);

        final JPasswordField password = new JPasswordField();
        password.setBounds(125, 73, 250, 25);
        getContentPane().add(password);

        // Buttons

        JButton login = new JButton("Login");
        login.setBounds(37, 140, 90, 30);
        getContentPane().add(login);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username_check = username.getText();
                String pass_check =password.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost: 3306/login_system","root","");
                    PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("Select Username,Password from java_project_user where Username = ? and Password = ?");
                    preparedStatement.setString(1,username_check);
                    preparedStatement.setString(2,pass_check);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()){     //IMP
//                      if (true){
                        String user = username_check;
                        alert();
                        System.out.println("Successfully Login");
                          JFrame frame =new JFrame("Welcome");
                          frame.setBounds(100, 100, 780, 600);
                          frame.setResizable(false);
//                          frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                          frame.setLocationRelativeTo(null);
                          frame.getContentPane().setLayout(null);
                          frame.setVisible(true);
                          ImageIcon img = new ImageIcon("C:\\Users\\JYP\\IdeaProjects\\Login_Mysql\\src\\Login_System\\download.png");
                          frame.setIconImage(img.getImage());
                          dispose();
                          
                          // User - page
                          JLabel user_name = new JLabel("Hello "+user);
                          user_name.setBounds(309,-10,1000,75);
                          user_name.setFont(new Font("Arial",Font.PLAIN,18));
                          frame.add(user_name);

                          JLabel lib = new JLabel("Library Section");
                          lib.setBounds(319,45,1000,75);
                          lib.setFont(new Font("Arial",Font.ITALIC,18));
                          frame.add(lib);

                          JLabel owner = new JLabel("Made by Hemanshu Patil");
                          owner.setBounds(310,515,1000,75);
                          owner.setFont(new Font("Arial",Font.PLAIN,12));
                          frame.add(owner);

                          JButton add_book = new JButton("Add Book");
                          add_book.setBounds(285,120,195,40);
                          add_book.setFont(new Font("Arial",Font.PLAIN,16));
                          frame.add(add_book);
                          add_book.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  try {
//                                      frame.dispose();
                                      AddBook addbook = new AddBook();
                                      addbook.setVisible(true);
//                                      dispose();
                                  } catch (IOException ex) {
                                      throw new RuntimeException(ex);
                                  }

                              }
                          });



                          JButton view_book = new JButton("View Book");
                          view_book.setBounds(285,180,195,40);
                          view_book.setFont(new Font("Arial",Font.PLAIN,16));
                          frame.add(view_book);
                          view_book.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  View_book viewBooks = new View_book();
//                                  viewBooks.setVisible(true);
                              }
                          });


                          JButton issue_book = new JButton("Issue Book");
                          issue_book.setBounds(285,240,195,40);
                          issue_book.setFont(new Font("Arial",Font.PLAIN,16));
                          frame.add(issue_book);
                          issue_book.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  try {
                                      IssueBook issue = new IssueBook();
                                      issue.setVisible(true);
                                  } catch (IOException ex) {
                                      System.out.println(ex);
                                  }

                              }
                          });


                          JButton view_issue_book = new JButton("View Issued Book");
                          view_issue_book.setBounds(285,300,195,40);
                          view_issue_book.setFont(new Font("Arial",Font.PLAIN,16));
                          frame.add(view_issue_book);
                          view_issue_book.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  View_Issue_Book view_issue_book = new View_Issue_Book();
//                                  view_issue_book.setVisible(true);
                              }
                          });

                          JButton return_book = new JButton("Return Book");
                          return_book.setBounds(285,360,195,40);
                          return_book.setFont(new Font("Arial",Font.PLAIN,16));
                          frame.add(return_book);
                          return_book.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  Return_Book returnBook = null;
                                  try {
                                      returnBook = new Return_Book();
                                      returnBook.setVisible(true);
                                  } catch (IOException ex) {
                                      System.out.println(ex);
                                  }

                              }
                          });

                          JButton logout = new JButton("Log Out");
                          logout.setBounds(285,420,195,40);
                          logout.setFont(new Font("Arial",Font.PLAIN,16));
                          frame.add(logout);
                          logout.addActionListener(new ActionListener() {
                              public void actionPerformed(ActionEvent e) {
                                  int a = JOptionPane.showConfirmDialog(logout, "Are You Sure to Log Out");
                                  if (a == JOptionPane.YES_OPTION) {
                                      frame.dispose();
                                      System.out.println("Log Out SuccessFully");
                                      Login log = new Login();
                                      log.setVisible(true);
                                  }
                              }
                          });

                    }
                    else{
                        alert1();
                        System.out.println("Failed To login");
                    }
                    resultSet.close();
                    preparedStatement.close();
                    conn.close();


                } catch (SQLException | ClassNotFoundException ex) {
                    alert1();
                    System.out.println("Error : "+ex);
                }
            }
        });



        JButton reset = new JButton("Reset");
        reset.setBounds(279, 140, 90, 30);
        getContentPane().add(reset);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username.setText(null);
                password.setText(null);
            }
        });

        JButton register = new JButton("Register");
        register.setBounds(158, 140, 90, 30);
        getContentPane().add(register);

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Sign_Up sign = new Sign_Up();
                    sign.setVisible(true);
                    dispose();
                } catch (IOException ex) {
                    System.out.println("Failed");
                }

            }
        });

        JButton home = new JButton("Go Back");
        home.setBounds(158, 190, 90, 30);
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
}
