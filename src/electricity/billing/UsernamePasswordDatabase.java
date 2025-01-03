package electricity.billing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsernamePasswordDatabase {

	
	public static boolean validateLogin(String username,String password)
	{
		
		 boolean isValid = false;
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            // Load the JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish a connection
	            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/electricitybillingsystem","root","password");

	            // Prepare the SQL query
	            String sql = "SELECT * FROM  WHERE username = ? AND password = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            // Execute the query
	            rs = stmt.executeQuery();

	            // Check if a matching user is found
	            if (rs.next()) {
	                isValid = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return isValid;
	    }
		
	

}
