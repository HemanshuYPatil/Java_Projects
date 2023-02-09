package Login_System;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class Return_Book extends JDialog {

    public static void main(String[] args) throws IOException {
        try {
            Return_Book dialog = new Return_Book();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Return_Book ()throws IOException {

        setBounds(0, 100, 450, 360);
        setTitle("Return a Book ");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);


        // Label
        JLabel bookname = new JLabel("Book Name");
        bookname.setBounds(33, 56, 82, 15);
        getContentPane().add(bookname);


//        JLabel book_id_ = new JLabel("Book Id ");
//        book_id_.setBounds(33, 99, 64, 15);
//        getContentPane().add(book_id_);


        JLabel category = new JLabel("Edition ");
        category.setBounds(33, 133, 67, 15);
        getContentPane().add(category);
//
//        JLabel price = new JLabel("Price ");
//        price.setBounds(33, 199, 67, 15);
//        getContentPane().add(price);


        // Input Field

        final JTextField bookn = new JTextField();
        bookn.setBounds(125, 55, 250, 27);
        getContentPane().add(bookn);

//        final JTextField bid = new JTextField();
//        bid.setBounds(125, 130, 250, 27);
//        getContentPane().add(bid);

        final JTextField categoryb = new JTextField();
        categoryb.setBounds(125, 130, 250, 27);
        getContentPane().add(categoryb);
//
//        final JTextField priceb = new JTextField();
//        priceb.setBounds(125, 195, 250, 27);
//        getContentPane().add(priceb);

        JButton reset = new JButton("Reset");
        reset.setBounds(260, 260, 90, 30);
        getContentPane().add(reset);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookn.setText(null);
                categoryb.setText(null);
            }
        });

        JButton sub = new JButton("Submit");
        sub.setBounds(130, 260, 90, 30);
        getContentPane().add(sub);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String bookname = bookn.getText();
                String edition = categoryb.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_System", "root", "");
                    String query = "INSERT INTO java_project_return_book ( Book_Name, Edition,Book_Status)" + "VALUES ('" + bookname + "','" + edition + "','Success')";
                    Statement statement = conn.createStatement();
                    statement.executeUpdate(query);
                    statement.close();
                    try {
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT Book_Name,Edition FROM java_project_issue_book");
                        while (rs.next()) {
                            String bookName = rs.getString("Book_Name");
                            String editions = rs.getString("Edition");
                            if (bookName.equals(bookname) && editions.equals(edition)) {
                                String update = "UPDATE java_project_add_book Set Book_Status = 0 WHERE Book_Name = '" + bookname + "' AND Edition = '" + editions + "'";
                                stmt.executeUpdate(update);
                                JOptionPane.showMessageDialog(null, "Book Successfully Returned");
                            }
////                            if (!bookName.equals(bookname) && !editions.equals(edition)){
////                                JOptionPane.showMessageDialog(null, "Book Not Found");
////                            }
//                            else{
//                                JOptionPane.showMessageDialog(null, "Fields are empty Please enter valid Credentials");
//                            }
                        }
                        stmt.close();
                        rs.close();
                    } catch (Exception el) {
                        System.out.println(el);
                    }

                    conn.close();
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println(ex);
                }

            }

        });

    }

}