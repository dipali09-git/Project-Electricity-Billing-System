package electricity.billing;


	import javax.swing.*;
	import javax.swing.table.DefaultTableModel;
	import java.awt.*;
	import java.sql.*;

	public class ShowDetails extends JFrame {

		String user;
		String pass;
		
	    // JDBC connection variables
	    private static final String URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";  // Replace with your database URL
	    private static final String USER = "root";  // Replace with your database username
	    private static final String PASSWORD = "password";  // Replace with your database password

	    public ShowDetails(String username,String password) {
	    	
	    	this.user=username;
	    	this.pass=password;
	        // Frame settings
	        setTitle("Customer Details");
	        setSize(800, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // Table setup
	        String[] columnNames = {
	                "Customer Name", "Address", "Meter ID", "city","state" ,
	                 "Email", "Phone No."
	        };
	        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	        JTable table = new JTable(model);

	        // Fetch customer data from the database and populate the table
	        fetchCustomerData(model);

	        // Add the table to a scroll pane
	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);

	        // Set frame visibility
	        setVisible(true);
	    }

	    
	    private void fetchCustomerData(DefaultTableModel model) {
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

	            // Execute query to get customer details
	            String query = "SELECT custname,address,meterid,city,state,email,phoneno from CustomerDetails where username='"+user+"'";
	            resultSet = statement.executeQuery(query);

	            // Populate the table model with the result set data
	            while (resultSet.next()) {
	                String custName = resultSet.getString("custname");
	                String address = resultSet.getString("address");
	                int meterId = resultSet.getInt("meterid");
	                String city = resultSet.getString("city");
	                String state = resultSet.getString("state");
	               
	                String email = resultSet.getString("email");
	                String phoneNo = resultSet.getString("phoneno");

	                Object[] row = {custName, address, meterId, city, state,  email, phoneNo};
	                model.addRow(row);
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error fetching data from the database.",
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
	        // Run the application
	     
	           new ShowDetails("swapnil", "pass@12");
	       
	    }
	}

	
	
	

