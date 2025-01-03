package electricity.billing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	public class LoginPage extends JFrame{
		
		 JTextField usernameField;
		 JPasswordField passwordField;
		 JTextField loggininasField;
		 JComboBox<String> loginComboBox ;
	    	public LoginPage()
	    	{
	    		

	        // Create a new frame
	        new JFrame("Login Page");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocation(500,200);
	        setSize(400, 400);
	        setLayout(null);

	        // Create a label for the login heading
	        JLabel loginLabel = new JLabel("Login");
	        loginLabel.setBounds(180, 20, 100, 30);
	        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        loginLabel.setForeground(Color.GREEN);
	        add(loginLabel);

	        // Create a label and text field for username
	        JLabel usernameLabel = new JLabel("Username :");
	        usernameLabel.setBounds(50, 70, 80, 25);
	        usernameLabel.setFont(new Font("Serif", Font.BOLD, 12));
	        usernameLabel.setForeground(Color.WHITE);
	        add(usernameLabel);

	        usernameField = new JTextField();
	        usernameField.setBounds(160, 70, 200, 25);
	        add(usernameField);

	        // Create a label and text field for password
	        JLabel passwordLabel = new JLabel("Password :");
	        passwordLabel.setBounds(50, 110, 80, 25);
	        passwordLabel.setFont(new Font("Serif", Font.BOLD, 12));
	        passwordLabel.setForeground(Color.WHITE);
	        add(passwordLabel);

	        passwordField = new JPasswordField();
	        passwordField.setBounds(160, 110, 200, 25);
	        add(passwordField);
	        
	        JLabel loginLabel1 = new JLabel("Login in as:");
	        loginLabel1.setBounds(50, 150, 80, 25); 
	        loginLabel1.setFont(new Font("Serif", Font.BOLD, 12));
	        loginLabel1.setForeground(Color.WHITE);
	      
	        String[] loginOptions = {"Admin", "User"};
	        loginComboBox = new JComboBox<>(loginOptions);
	        loginComboBox.setBounds(160, 150, 200, 25); 

	     
	        add(loginLabel1);
	        add(loginComboBox);
	       
	      
	        JButton loginButton = new JButton("login" );
	       loginButton.setBounds(110, 200, 90, 30);
	       loginButton.setForeground(Color.WHITE); 
	       loginButton.setBackground(Color.BLACK); 
	       loginButton.setFont(new Font("Arial", Font.BOLD, 16));
	       
	        add(loginButton);

	     
	        JButton clearButton = new JButton("Clear");
	        clearButton.setBounds(230, 200, 90, 30);
	        clearButton.setForeground(Color.WHITE); 
	        clearButton.setBackground(Color.BLACK); 
	        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
	        
	        add(clearButton);
	        
	       
	        JButton signupButton = new JButton("Sign Up");
	        signupButton.setBounds(170, 250, 100, 30);
	        signupButton.setForeground(Color.WHITE); 
	        signupButton.setBackground(Color.BLACK); 
	        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
	       
	        add(signupButton);
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/electricity/billing/icons/bulbs-overlay(3).png"));
			 Image image = imageIcon.getImage();
			     Image scaledImage = image.getScaledInstance(1350, 1000, Image.SCALE_SMOOTH);
			     imageIcon = new ImageIcon(scaledImage);
			     JLabel backgroundLabel = new JLabel(imageIcon);
			     add(backgroundLabel);
	             backgroundLabel.setBounds(-35, -59, 1350, 1000);

	        clearButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                usernameField.setText("");
	                passwordField.setText("");
	                
	            }
	        });
        
		    	
		     loginButton.addActionListener(new ActionListener() {
		    		
		    		
		    		@Override
		    		public void actionPerformed(ActionEvent e) {
		    			
		    			 String username= usernameField.getText(); 
			    	       String password=passwordField.getText();
	    					
		    				  
		    					 if (validateLogin(username, password)) {
			                   
			                    
			    	    	   JOptionPane.showMessageDialog(null, "login Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			    	    	   if(loginComboBox.getSelectedItem().equals("User"))
			    	    	   {
			    	    		   new ElectricityHomePage(usernameField.getText(),passwordField.getText());
			    	    		   setVisible(false);
			    	    		   
			    	    		   
			    	    	   }
			    	    	   else if(loginComboBox.getSelectedItem().equals("Admin"))
			    	    	   {
			    	    		   new AdminHomePage();
			    	    		   setVisible(false);
			    	    		   
			    	    	   }
			                } else {
			                	JOptionPane.showMessageDialog(null, "Invalid username or password!", "unsuccess", JOptionPane.INFORMATION_MESSAGE);
			                }
		    		
		    				
		    			}
		    		public  boolean validateLogin(String username,String password)
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
		    		            if(loginComboBox.getSelectedItem().equals("User"))
		    		            {
		    		            String sql = "SELECT * FROM CustomerDetails WHERE username = ? AND password = ?";
		    		            stmt = conn.prepareStatement(sql);
		    		            stmt.setString(1, username);
		    		            stmt.setString(2, password);
		    		            }
		    		            else if(loginComboBox.getSelectedItem().equals("Admin"))
		    		            {
		    		            	String sql1 = "SELECT * FROM AdminDetails WHERE username = ? AND password = ?";
			    		            stmt = conn.prepareStatement(sql1);
			    		            stmt.setString(1, username);
			    		            stmt.setString(2, password);
		    		            }
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
		    			
		    		
		    			
		    		
		    	     });
		    	       signupButton.addActionListener(new ActionListener(){

		    				@Override
		    				public void actionPerformed(ActionEvent e) {
		    					
		    					new CreateAccount();
		    					setVisible(false);
		    					
		    				}
		    	        	
		    	        	
		    	        	
		    	        });
		    	        
		    	        setVisible(true); 
		    	     }
		    	       
		    	    		
	        public static void main(String[] args) {
		    	new LoginPage();
	    }
	}
	
