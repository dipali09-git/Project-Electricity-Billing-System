package electricity.billing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateCustomerDatabase {

	
	public static void updateValueIntoDatabase(String meterreading,String rate,String amountpay,String Duedate,String month)
	{
		
		
		
		try 
		{
			int m=Integer.parseInt(meterreading);
			int r=Integer.parseInt(rate);
			int amount=Integer.parseInt(amountpay);
			int date=Integer.parseInt(Duedate);
			
			
			
           Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/electricitybillingsystem","root","password");
		     
		    PreparedStatement pstmt=con.prepareStatement("update MeterDetails set meterreading = ?,rate = ?,amountpay = ?,   duedate = ?, month = ? WHERE meterid =?");
		    pstmt.setInt(1, m);
		    pstmt.setInt(2, r);
		    pstmt.setInt(3, amount);
		    pstmt.setInt(4, date);
		    pstmt.setString(5, month);
		    
		   
		    
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
	
	

