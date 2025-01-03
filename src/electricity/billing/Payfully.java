package electricity.billing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Payfully {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public void updatePaymentStatus(String meterId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String updateQuery = "UPDATE MeterDetails SET paystatus = 'paid' WHERE meterid = ?";
            stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, meterId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Payment status updated successfully for Meter ID: " + meterId);
            } else {
                System.out.println("No rows updated. Please check the Meter ID: " + meterId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Call this method after processing the payment
    public void processPayment(String meterId) {
        // Add your payment processing logic here

        // After payment is successful, update payment status
        updatePaymentStatus(meterId);
    }
}


//package electricity.billing;
//
//
//	import java.sql.Connection;
//	import java.sql.DriverManager;
//	import java.sql.PreparedStatement;
//	import java.sql.SQLException;
//
//	public class Payfully {
//
//	    // Database credentials
//	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
//	    private static final String DB_USER = "root";
//	    private static final String DB_PASSWORD = "password";
//
//	    
//	    // Method to update the pay status
//	    public static void payBill(int meterId) {
//	        Connection connection = null;
//	        PreparedStatement preparedStatement = null;
//
//	        String updateQuery = "UPDATE MeterDetails SET paystatus = ? WHERE meterid = ?";
//
//	        try {
//	            // 1. Establish a connection to the database
//	            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
//
//	            // 2. Create a PreparedStatement for the update query
//	            preparedStatement = connection.prepareStatement(updateQuery);
//
//	            // 3. Set parameters: "paid" status and bill ID
//	            preparedStatement.setString(1, "paid");
//	            preparedStatement.setInt(2, meterId);
//
//	            // 4. Execute the update query
//	            int rowsAffected = preparedStatement.executeUpdate();
//
//	            if (rowsAffected > 0) {
//	                System.out.println("Payment successful! ");
//	            } else {
//	                System.out.println("Bill not found or already paid.");
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            // 5. Close the resources
//	            try {
//	                if (preparedStatement != null) preparedStatement.close();
//	                if (connection != null) connection.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//	
//public static void main(String args[])
//{
//	new Payfully();
//}
//
//}