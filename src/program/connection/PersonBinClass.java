package program.connection;

import java.util.Scanner;

public class PersonBinClass {
	private int personid;
	 private long contact;
	 private String pname,address;
	
	 public void setPersonid(int personid) 
	 {
		 this.personid=personid;
	 }
	 public void setContact(long contact) 
	 {
		 this.contact=contact;
		 
	 }
	 public void setPname(String pname) 
	 {
		 this.pname=pname;
		 

	 }
	 public void setAddress(String address) 
	 {
		 this.address=address;
		 
			
	 }
	  
	 public int getPersonid()
	 {
		 return personid;
	 }
	 public long getContact() 
	 {
		 return contact;
	 }
	 public String getPname() 
	 {
		 return pname;
	 }
	 public String getAddress() 
	 {
		 return address;
	 }
}
