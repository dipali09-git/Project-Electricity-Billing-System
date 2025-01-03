package electricity.billing;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
import java.util.Random;

	public class AddCustomer extends JFrame {

	    // JDBC connection variables
	    private static final String URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";  // Replace with your database URL
	    private static final String USER = "root";  // Replace with your database username
	    private static final String PASSWORD = "password";  // Replace with your database password

	    // UI Components
	    private JTextField custNameField, addressField, cityField, stateField, emailField, phoneField;
		

	    public AddCustomer() {
	        // Frame settings
	        setTitle("Add Customer");
	        setSize(500, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);  // Center the frame
	        setLayout(new GridLayout(8, 2, 10, 10));  // 8 rows, 2 columns, 10px gaps

	        // Labels and text fields for customer details
	        add(new JLabel("Customer Name:"));
	        custNameField = new JTextField();
	        add(custNameField);

	        

	        add(new JLabel("Address:"));
	        addressField = new JTextField();
	        add(addressField);

	        add(new JLabel("City:"));
	        cityField = new JTextField();
	        add(cityField);

	        add(new JLabel("State:"));
	        stateField = new JTextField();
	        add(stateField);

	        add(new JLabel("Email:"));
	        emailField = new JTextField();
	        add(emailField);

	        add(new JLabel("Phone No:"));
	        phoneField = new JTextField();
	        add(phoneField);

	        // Add button
	        JButton addButton = new JButton("Add");
	        add(new JLabel());  // Empty label for spacing
	        add(addButton);

	        // Add button action listener to insert data into the database
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	 int meterid=new Random().nextInt(10000);
	            	
	                addCustomerToDatabase();
	            }
	        });

	        // Set frame visibility
	        setVisible(true);
	    }

	    // Method to insert customer data into the database
	    private void addCustomerToDatabase() {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            // Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish connection
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);

	            // Insert query
	            String insertQuery = "INSERT INTO CustomerDetails (custname, meterid, address, city, state, email, phoneno) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(insertQuery);

	            // Set parameters from text fields
	            preparedStatement.setString(1, custNameField.getText());
	            preparedStatement.setInt(1,new Random().nextInt(10000));
	            preparedStatement.setString(3, addressField.getText());
	            preparedStatement.setString(4, cityField.getText());
	            preparedStatement.setString(5, stateField.getText());
	            preparedStatement.setString(6, emailField.getText());
	            preparedStatement.setString(7, phoneField.getText());

	            // Execute the update
	            int rowsInserted = preparedStatement.executeUpdate();
	            if (rowsInserted > 0) {
	                JOptionPane.showMessageDialog(this, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	                clearFields();  // Clear the input fields after successful insertion
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error adding customer to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
	        } finally {
	            // Close resources
	            try {
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // Method to clear the input fields after successful addition
	    private void clearFields() {
	        custNameField.setText("");
	        //meterIdField.setText("");
	        addressField.setText("");
	        cityField.setText("");
	        stateField.setText("");
	        emailField.setText("");
	        phoneField.setText("");
	    }

	    public static void main(String[] args) {
	        // Run the add customer frame application
	      
	            new AddCustomer();
	        
	    }
	}


