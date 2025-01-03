package electricity.billing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminHomePage extends JFrame {

	public AdminHomePage() {
		// Frame setup
		setTitle("Admin Home Page - Electricity Billing System");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the frame
		setVisible(true);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/electricity/billing/icons/e.jpg"));
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(1350, 1000, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		JLabel backgroundLabel = new JLabel(imageIcon);
		add(backgroundLabel);
		backgroundLabel.setBounds(-35, -59, 1350, 1000);

		// Create a menu bar
		JMenuBar menuBar = new JMenuBar();

		// "Customers" menu with submenus
		JMenu customersMenu = new JMenu("Customers");
		JMenuItem showCustomerDetailsMenuItem = new JMenuItem("Show Customer Details");
	//	JMenuItem updateCustomerDetailsMenuItem = new JMenuItem("Update Details");
		JMenuItem addCustomerDetailsMenuItem = new JMenuItem("Add Customer");
		// Add submenus to "Customers" menu
		customersMenu.add(showCustomerDetailsMenuItem);
		//customersMenu.add(updateCustomerDetailsMenuItem);
		customersMenu.add(addCustomerDetailsMenuItem);

		// "Payment Status" menu with submenus
		JMenu paymentStatusMenu = new JMenu("Payment Status");
		JMenuItem paidStatusMenuItem = new JMenuItem("Paid");
		JMenuItem unpaidStatusMenuItem = new JMenuItem("Unpaid");

		// Add submenus to "Payment Status" menu
		paymentStatusMenu.add(paidStatusMenuItem);
		paymentStatusMenu.add(unpaidStatusMenuItem);

		// Create "Logout" menu item
		JMenuItem logoutMenuItem = new JMenuItem("Logout");

		// Create "Exit" menu item
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		// Add menus and items to the menu bar
		menuBar.add(customersMenu);
		menuBar.add(paymentStatusMenu);
		menuBar.add(logoutMenuItem);
		menuBar.add(exitMenuItem);

		// Add the menu bar to the frame
		setJMenuBar(menuBar);

		// Action listeners for "Customers" menu items
		showCustomerDetailsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowCustomerDetails();
			}
		});
//
//		updateCustomerDetailsMenuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			new UpdateCustomerDetails();
//			}
//		});
		addCustomerDetailsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCustomer();
			}
		});
		// Action listeners for "Payment Status" menu items
		paidStatusMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             new AllPaidBill();
			}
		});

		unpaidStatusMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             new AllUnpaidbill();
			}
		});

		// Action listener for "Logout" menu item
		logoutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Logged out successfully.");
					// Perform logout actions, e.g., go back to login screen
				}
			}
		});

		// Action listener for "Exit" menu item
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					System.exit(0); // Exit the application
				}
			}
		});
	}

	public static void main(String[] args) {
		// Run the admin home page application

		new AdminHomePage();

	}
}
