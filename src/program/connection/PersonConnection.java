package program.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PersonConnection {
	
	
	public static void main(String[] args) {
	
		 Scanner sc=new Scanner(System.in);
		
		 System.out.println("enter person id:");
			int id=sc.nextInt();
		 System.out.println("enter person contact:");
			long c =sc.nextLong();
		 System.out.println("enter person name:");
			String n=sc.next();
		 System.out.println("enter person address:");
			String a=sc.next();
			
		PersonBinClass p=new PersonBinClass();
		p.setPersonid(id);
		p.setContact(c);
		p.setPname(n);
		p.setAddress(a);
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2","root","password");
		    PreparedStatement stmt=con.prepareStatement("insert into PersonInfo values(?,?,?,?)");
		    stmt.setInt(1, p.getPersonid());
		    stmt.setLong(2, p.getContact());
		    stmt.setString(3, p.getPname());
		    stmt.setString(4, p.getAddress());
		
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
