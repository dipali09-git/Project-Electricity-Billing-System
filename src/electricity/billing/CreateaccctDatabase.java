package electricity.billing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateaccctDatabase {
	

		
	
	public static void insertValueIntoDatabase(int meterid,String custname,String username,String password,String address,String city,
			String state,String email,String phoneno)
	{
		
		
		
		try 
		{
			
			long id=Long.parseLong(phoneno);
			
           Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/electricitybillingsystem","root",
					"password");
		     
		    PreparedStatement pstmt=con.prepareStatement("insert into CustomerDetails(meterid,custname,username,"
		    		+ "password ,"
		    		+ "address,city,state,email,phoneno) "
		    		+ "values(?,?,?,?,?,?,?,?,?)"); 
		    pstmt.setInt(1, meterid);
		    pstmt.setString(2, custname);
		    pstmt.setString(3, username);
		    pstmt.setString(4, password);
		    
		    pstmt.setString(5, address);
		    pstmt.setString(6, city);
		    pstmt.setString(7, state);
		    pstmt.setString(8, email);
		    pstmt.setLong(9, id);
		    
		    int i=pstmt.executeUpdate();

		    System.out.println(i+" records inserted"); 
		    con.close();
		    
		    
		    
		    
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
			
		}
		
	}
	
}
	

