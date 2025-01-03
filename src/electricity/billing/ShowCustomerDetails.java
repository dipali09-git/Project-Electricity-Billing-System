package electricity.billing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
public class ShowCustomerDetails extends JFrame{
	


	    // JDBC connection variables
	    private static final String URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";  // Replace with your database URL
	    private static final String USER = "root";  // Replace with your database username
	    private static final String PASSWORD = "password";  // Replace with your database password

	    // Table for displaying customer details
	    private JTable customerTable;
	    private DefaultTableModel tableModel;

	    public ShowCustomerDetails () {
	        // Frame settings
	        setTitle("Customer Details");
	        setSize(800, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);  // Center the frame
	        setLayout(new BorderLayout());

	        // Initialize table model with column names
	        String[] columnNames = {"Cust Name", "Meter ID", "Address", "City", "Email", "Phone No"};
	        tableModel = new DefaultTableModel(columnNames, 0);
	        customerTable = new JTable(tableModel);

	        // Add table to scroll pane
	        JScrollPane scrollPane = new JScrollPane(customerTable);
	        add(scrollPane, BorderLayout.CENTER);

	        // Fetch and display customer data from database
	        fetchCustomerDetails();

	        // Set frame visibility
	        setVisible(true);
	    }

	    // Method to fetch customer details from the database and add to table model
	    private void fetchCustomerDetails() {
	        Connection connection = null;
	        Statement statement = null;
	        ResultSet resultSet = null;

	        try {
	            // Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish connection
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);

	            // Create statement
	            statement = connection.createStatement();

	            // Execute query to fetch all customer details
	            String query = "SELECT custname, meterid, address, city, email, phoneno FROM CustomerDetails";
	            resultSet = statement.executeQuery(query);

	            // Add each row of data to the table model
	            while (resultSet.next()) {
	                String custName = resultSet.getString("custname");
	                int meterId = resultSet.getInt("meterid");
	                String address = resultSet.getString("address");
	                String city = resultSet.getString("city");
	                String email = resultSet.getString("email");
	                String phoneNo = resultSet.getString("phoneno");

	                // Add row to table model
	                tableModel.addRow(new Object[]{custName, meterId, address, city, email, phoneNo});
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error fetching customer details from the database.",
	                    "Database Error", JOptionPane.ERROR_MESSAGE);
	        } finally {
	            // Close resources
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void main(String[] args) {
	        // Run the customer details frame application
	      
	            new ShowCustomerDetails();
	       
	    }
	}

