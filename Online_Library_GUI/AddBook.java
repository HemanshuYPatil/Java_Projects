package Login_System;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class AddBook extends JDialog {

    public static void main(String[] args) throws IOException {
        try {
            AddBook dialog = new AddBook();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public AddBook() throws IOException {

        setBounds(0, 100, 450, 360);
        setTitle("Add Book ");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);


        // Label
        JLabel bookname = new JLabel("Book Name");
        bookname.setBounds(33, 49, 82, 15);
        getContentPane().add(bookname);


        JLabel edition = new JLabel("Edition ");
        edition.setBounds(33, 99, 64, 15);
        getContentPane().add(edition);


        JLabel category = new JLabel("Category ");
        category.setBounds(33, 149, 67, 15);
        getContentPane().add(category);

        JLabel price = new JLabel("Price ");
        price.setBounds(33, 199, 67, 15);
        getContentPane().add(price);


        // Input Field

        final JTextField bookn = new JTextField();
        bookn.setBounds(125, 45, 250, 27);
        getContentPane().add(bookn);

        final JTextField editionb = new JTextField();
        editionb.setBounds(125, 95, 250, 27);
        getContentPane().add(editionb);

        final JTextField categoryb = new JTextField();
        categoryb.setBounds(125, 145, 250, 27);
        getContentPane().add(categoryb);

        final JTextField priceb = new JTextField();
        priceb.setBounds(125, 195, 250, 27);
        getContentPane().add(priceb);

        JButton reset = new JButton("Reset");
        reset.setBounds(260, 260, 90, 30);
        getContentPane().add(reset);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookn.setText(null);
                editionb.setText(null);
                categoryb.setText(null);
                priceb.setText(null);
            }
        });

        JButton sub = new JButton("Submit");
        sub.setBounds(130, 260, 90, 30);
        getContentPane().add(sub);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String bookname = bookn.getText();
                    String edition = editionb.getText();
                    String category = categoryb.getText();
                    String price = priceb.getText();

                    boolean book_check = bookname.isEmpty();
                    boolean editions = edition.isEmpty();
                    boolean categorys = category.isEmpty();
                    boolean prices = price.isEmpty();

                    if (!book_check && !editions && !categorys && !prices) {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_system", "root", "");
                        String sql = "INSERT INTO java_project_add_book ( Book_Name , Edition , Category , Price) VALUES ('" + bookname + "','" + edition + "','" + category + "','" + price + "')";
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(sql);
//                        alert(bookname, edition, category, price);
                        success();
                        statement.close();
                        conn.close();
                    }
                    else {
                        error();
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error : "+ex);
                } catch (SQLException sqlex) {
                    System.out.println("SQL Error : "+sqlex);
                }
            }
        });


//        JButton home = new JButton("Go Back");
//        home.setBounds(250, 270, 90, 30);
//        getContentPane().add(home);
//        home.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    dispose();
////                    Home_page homepage = new Home_page();
////                    homepage.home_page();
//                }
//                catch (Exception ex){
//                    System.out.println(ex);
//                }
//            }
//        });


    }
//    void alert(String bookname,String edition,String category,String price) {
//
//        boolean user_check = bookname.isEmpty();
//        boolean pass_check = edition.isEmpty();
//        boolean Cpass_check = category.isEmpty();
//        boolean email_check = price.isEmpty();
//
//        if (!user_check && !pass_check && !Cpass_check && !email_check){
//            JOptionPane.showMessageDialog(this,"Successfully Add a Book ","Success",JOptionPane.INFORMATION_MESSAGE);
//        }
//        else {
//            JOptionPane.showMessageDialog(this,"Failed to Add a Book","Error",JOptionPane.ERROR_MESSAGE);
//        }
//    }

    public void success(){
        JOptionPane.showMessageDialog(this,"Successfully Add a Book","Success",JOptionPane.INFORMATION_MESSAGE);
    }
    public void error(){
        JOptionPane.showMessageDialog(this,"Field are Empty Please Fill the Information","Error",JOptionPane.ERROR_MESSAGE);
    }
}