package Login_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View_Issue_Book extends JFrame{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public View_Issue_Book() {
        JFrame frame = new JFrame("Books Are Issued From Library");
        frame.setResizable(false);
//        frame.setSize(500, 500);
//        frame.setLocation(100,100);
        frame.setBounds(100,100,850,400);
        frame.setLocationRelativeTo(null);


        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        frame.add(scrollPane);


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/login_system", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Id,Book_Name,Edition,Category,Price FROM java_project_add_book where Book_Status = 1 ");

            int columns = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= columns; i++) {
                model.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            View_Issue_Book dialog = new View_Issue_Book();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//            dialog.setVisible(true);
        }
        catch (Exception exa){
            System.out.println(exa);
        }
    }


}
