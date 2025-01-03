package program.connection;

import java.sql.*;

public class ConnectionDemo {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","password");
		     
		    PreparedStatement stmt=con.prepareStatement("insert into employee values(?,?,?)"); 
		    stmt.setInt(1, 10);
		    stmt.setString(2, "sai");
		    stmt.setString(3, "pune");
		    
		    int i=stmt.executeUpdate();  
		    System.out.println(i+" records inserted");  
		    con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
