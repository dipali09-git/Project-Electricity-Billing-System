package electricity.billing;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AllUnpaidbill extends JFrame {
    
    // JDBC connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    // Constructor to set up the GUI components
    public AllUnpaidbill() {
        setTitle("Customer UnPaid Bill Details");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Table to display data
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Customer Name", "Meter ID", "Bill ID", "Email", "Phone No", "Pay Status", "Amount Pay"});
        
        // Set the model to the table
        table.setModel(model);
        
        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Fetch and display data
        displayPaidBillDetails(model);
        JFrame frame=new JFrame();
        setBounds(220,200,700,500);
        setVisible(true);
    }

    // Method to fetch and display data in the table
    private void displayPaidBillDetails(DefaultTableModel model) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Establishing connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Query to get paid bill details
            String query = "SELECT c.custname, c.meterid, b.bid, c.email, c.phoneno, b.paystatus, b.amountpay " +
                           "FROM CustomerDetails c " +
                           "JOIN MeterDetails b ON c.meterid = b.meterid " +
                           "WHERE b.paystatus = 'unpaid'";
            
            // Creating prepared statement
            stmt = conn.prepareStatement(query);
            
            // Executing query
            rs = stmt.executeQuery();
            
            // Adding data to table model
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("custname"),
                    rs.getString("meterid"),
                    rs.getInt("bid"),
                    rs.getString("email"),
                    rs.getString("phoneno"),
                    rs.getString("paystatus"),
                    rs.getDouble("amountpay")
                });
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Closing resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    
        // Creating and displaying the GUI
      
    	 new AllUnpaidbill();

       
    }
}
