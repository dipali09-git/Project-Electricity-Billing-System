package electricity.billing;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	public class MeterIdValidator {

	    // JDBC connection variables
	    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";  // Replace with your database URL
	    private static final String USER = "your_username";  // Replace with your database username
	    private static final String PASSWORD = "your_password";  // Replace with your database password

	    public static void main(String[] args) {
	        // Example meter ID to validate
	        String meterIdToCheck = " "; // Replace with the meter ID you want to check

	        if (checkMeterId(meterIdToCheck)) {
	            System.out.println("Meter ID is valid. Proceeding to the next page...");
	            // Code to open the next page can go here
	        } else {
	            System.out.println("Please insert a valid Meter ID.");
	        }
	    }

	    // Method to check if the entered meter ID exists in the database
	    private static boolean checkMeterId(String meterId) {
	        boolean exists = false;
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            // Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish connection
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);

	            // SQL query to check if meterid exists
	            String query = "SELECT * FROM meterdetails WHERE meterid = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, meterId);
	            resultSet = preparedStatement.executeQuery();

	            // Check if the result set is not empty
	            if (resultSet.next()) {
	                exists = true; // Meter ID exists
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            System.out.println("Database error occurred.");
	        } finally {
	            // Close resources
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return exists; // Return whether the meter ID exists
	    }
	}


