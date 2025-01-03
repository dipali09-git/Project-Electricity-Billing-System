package electricity.billing;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class UnpaidBillCust extends JFrame {

    // JDBC connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public UnpaidBillCust(String username, String password) {
        setTitle("UnPaid Bill Details");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table to display data
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Meter ID", "Bill ID", "Amount Pay", "Due Date"});

        // Set the model to the table
        table.setModel(model);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and display data
        displayPaidBillDetails(model, username, password);
        setVisible(true);
    }

   

	// Method to fetch and display data in the table
    private void displayPaidBillDetails(DefaultTableModel model, String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Query to get paid bill details for the logged-in customer
            String query = "SELECT m.meterid, m.bid, m.amountpay  " +
                           "FROM MeterDetails m " +
                           "JOIN CustomerDetails c ON m.meterid = c.meterid " +
                           "WHERE c.username = ? AND c.password = ? AND m.paystatus = 'unpaid'";

            // Creating prepared statement
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Executing query
            rs = stmt.executeQuery();

            // Adding data to table model
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("meterid"),
                        rs.getInt("bid"),
                        rs.getDouble("amountpay"),
                        
                });
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
    public static void main(String args[])
    {
    	new UnpaidBillCust("aadu","sjfd");
    	
    }
}
