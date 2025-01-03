package electricity.billing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PaidBill extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public PaidBill(String username, String password) {
        setTitle("Paid Bill Details");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Meter ID", "Bill ID", "Amount Pay"});

        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Display paid bill details
        displayPaidBillDetails(model, username, password);
        setVisible(true);
    }

    private void displayPaidBillDetails(DefaultTableModel model, String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT m.meterid, m.bid, m.amountpay " +
            		"FROM MeterDetails m " +
            		"JOIN CustomerDetails c ON m.meterid = c.meterid " +
            		"WHERE c.username = ? AND c.password = ? AND m.paystatus = 'paid'";


            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            // Check if any data is returned and add it to the model
            boolean hasData = false;
            while (rs.next()) {
                hasData = true; // Mark that we found data
                System.out.println("Meter ID: " + rs.getString("meterid") +
                                   ", Bill ID: " + rs.getInt("bid") +
                                   ", Amount Pay: " + rs.getDouble("amountpay") 
                                  );
                model.addRow(new Object[]{
                    rs.getString("meterid"),
                    rs.getInt("bid"),
                    rs.getDouble("amountpay"),
                    
                });
            }

            // Notify user if no data was found
            if (!hasData) {
                JOptionPane.showMessageDialog(this, "No paid bills found for the specified user.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new PaidBill("aadu", "sjfd");
    }
}


//package electricity.billing;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.sql.*;
//
//public class Paidbill extends JFrame {
//
//    // JDBC connection details
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "password";
//
//    public Paidbill(String username, String password) {
//        setTitle("Paid Bill Details");
//        setSize(800, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Table to display data
//        JTable table = new JTable();
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(new Object[]{"Meter ID", "Bill ID", "Amount Pay", "Due Date"});
//
//        // Set the model to the table
//        table.setModel(model);
//
//        // Scroll pane for table
//        JScrollPane scrollPane = new JScrollPane(table);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Fetch and display data
//        displayPaidBillDetails(model, username, password);
//        setVisible(true);
//    }
//
//   
//
//	// Method to fetch and display data in the table
//    private void displayPaidBillDetails(DefaultTableModel model, String username, String password) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // Establishing connection to the database
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            // Query to get paid bill details for the logged-in customer
//            String query = "SELECT m.meterid, m.bid, m.amountpay, m.Duedate " +
//                           "FROM MeterDetails m " +
//                           "JOIN CustomerDetails c ON m.meterid = c.meterid " +
//                           "WHERE c.username = ? AND c.password = ? AND m.paystatus = 'paid'";
//
//            // Creating prepared statement
//        stmt = conn.prepareStatement(query);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//
//            // Executing query
//            rs = stmt.executeQuery();
//
//            // Adding data to table model
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getString("meterid"),
//                        rs.getInt("bid"),
//                        rs.getDouble("amountpay"),
//                        rs.getDate("Duedate")
//                });
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//    public static void main(String args[])
//    {
//    	new Paidbill("aadu","sjfd");
//    	
//    }
//}
