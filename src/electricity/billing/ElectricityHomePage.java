package electricity.billing;



	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class ElectricityHomePage extends JFrame {
		String user;
		String pass;
		
		
	    public ElectricityHomePage(final String username,final String password) {
	        // Set up the frame
	    	
	        setTitle("Electricity Billing System - Home Page");
	        setSize(600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); 
	        setVisible(true);// Center the frame
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/electricity/billing/icons/electricity.jpg"));
			 Image image = imageIcon.getImage();
			     Image scaledImage = image.getScaledInstance(1350, 1000, Image.SCALE_SMOOTH);
			     imageIcon = new ImageIcon(scaledImage);
			     JLabel backgroundLabel = new JLabel(imageIcon);
			     add(backgroundLabel);
	             backgroundLabel.setBounds(-35, -59, 1350, 1000);
	        // Create a menu bar
	        JMenuBar menuBar = new JMenuBar();

	        // Create "Customer Details" menu with submenus
	        JMenu customerDetailsMenu = new JMenu("Customer Details");
	        JMenuItem showDetailsMenuItem = new JMenuItem("Show Details");
	        JMenuItem adddetailsMenuItem =new JMenuItem("Add Meter details");
	        customerDetailsMenu.add(showDetailsMenuItem);
	        customerDetailsMenu.add(adddetailsMenuItem);
		      
	      

	        // Create "Billing Details" menu with submenus
	        JMenu billingDetailsMenu = new JMenu("Billing Details");
	      
	        JMenuItem paidBillMenuItem = new JMenuItem("Paid Bill");
	        JMenuItem unpaidBillMenuItem = new JMenuItem("Unpaid Bill");
	       
	        billingDetailsMenu.add(paidBillMenuItem);
	        billingDetailsMenu.add(unpaidBillMenuItem);

	        // Create "Logout" menu item
	        JMenuItem logoutMenuItem = new JMenuItem("Logout");
	        
	        // Create "Exit" menu item
	        JMenuItem exitMenuItem = new JMenuItem("Exit");

	        // Add menus and items to the menu bar
	        menuBar.add(customerDetailsMenu);
	        menuBar.add(billingDetailsMenu);
	        menuBar.add(logoutMenuItem);
	        menuBar.add(exitMenuItem);

	        // Add the menu bar to the frame
	        setJMenuBar(menuBar);

	        // Create a welcome label and set its properties
	        

	        // Add action listeners for menu items
	        showDetailsMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               new ShowDetails(username, password);
	            	
	            }
	        });
	  adddetailsMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               new AddMeterDetails();
	            	
	            }
	        });
	     

	     

	        paidBillMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               new PaidBill(username,password);
	            }
	        });

	        unpaidBillMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 new UnpaidBillCust(username,password);
	            }
	        });

	        logoutMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout",
	                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	                if (response == JOptionPane.YES_OPTION) {
	                    JOptionPane.showMessageDialog(null, "Logged out successfully.");
	                    // Perform logout actions, e.g., go back to login screen
	                    setVisible(false);
	                    new LoginPage();
	                }
	            }
	        });

	        exitMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
	                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	                if (response == JOptionPane.YES_OPTION) {
	                    System.exit(0);  // Exit the application
	                }
	            }
	        });
	    }

	    public static void main(String[] args) {
	        // Run the electricity billing system application
	       
	           new ElectricityHomePage("rutu", "Rutu#567");
	        
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

