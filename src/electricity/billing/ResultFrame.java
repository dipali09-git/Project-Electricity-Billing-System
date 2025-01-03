package electricity.billing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultFrame extends JFrame {
    public ResultFrame(final int meterId, int metereading, double rate, double amountpay) {
        setTitle("Payment Details");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel meterIdLabel = new JLabel("Meter ID: " + meterId);
        meterIdLabel.setBounds(180, 60, 200, 30);
        add(meterIdLabel);

        JLabel meterReadingLabel = new JLabel("Meter Reading: " + metereading);
        meterReadingLabel.setBounds(180, 100, 200, 30);
        add(meterReadingLabel);

        JLabel rateLabel = new JLabel("Rate per Unit: " + rate);
        rateLabel.setBounds(180, 140, 200, 30);
        add(rateLabel);

        JLabel totalAmountLabel = new JLabel("Total Amount: " + amountpay);
        totalAmountLabel.setBounds(180, 180, 200, 30);
        add(totalAmountLabel);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(210, 240, 100, 30);
        add(payButton);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/electricity/billing/icons/f.jpeg"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(400, 40, 500, 500);
        add(imageLabel);
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                Payfully.payBill(meterId); // Update payment status in the database
                JOptionPane.showMessageDialog(null, "Paid Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                //new Payfully();
//                new Paidbill("aadu", "sjfd"); // Show paid bill details (pass username and password)
            }
        });

        setVisible(true);
    }

    public static void main(String args[]) {
        new ResultFrame(12, 34, 54, 4000);
    }
}

//package electricity.billing;
//
//
//	import javax.swing.*;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.event.*;
//
//	public class ResultFrame extends JFrame {
//	    // Constructor to setup the result frame
//	    public ResultFrame(int meterId, int  metereading, double rate, double amountpay) {
//	        // Create a frame
//	        setTitle("Payment Details");
//	        setSize(500, 400);
//	        setLayout(null);
//	        setLocationRelativeTo(null);
//	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/electricity/billing/icons/f.jpeg"));
//	        Image image = imageIcon.getImage();
//	        Image scaledImage = image.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
//	        imageIcon = new ImageIcon(scaledImage);
//
//	        JLabel imageLabel = new JLabel(imageIcon);
//	        imageLabel.setBounds(400, 40, 500, 500);
//	        add(imageLabel);
//	        
//	      
//	        // Labels to display the information
//	        JLabel meterIdLabel = new JLabel("Meter ID:   " + meterId);
//	       Font f=new Font("Monospaced", Font.BOLD , 15);
//	        meterIdLabel.setBounds(180, 60, 200, 30);
//	      
//	        add(meterIdLabel);
//
//	        JLabel meterReadingLabel = new JLabel("Meter Reading:   " + metereading);
//	       
//	        meterReadingLabel.setBounds(180, 100, 200, 30);
//	     
//	        add(meterReadingLabel);
//
//	        JLabel rateLabel = new JLabel("Rate per Unit:   " + rate);
//	       
//	        rateLabel.setBounds(180, 140, 200, 30);
//	       
//	        add(rateLabel);
//
//	        JLabel totalAmountLabel = new JLabel("Total Amount:   " + amountpay);
//	      
//	        totalAmountLabel.setBounds(180, 180, 200, 30);
//	        
//	        add(totalAmountLabel);
//
//	        // Pay button
//	        JButton payButton = new JButton("Pay");
//	        payButton.setBounds(210, 240, 100, 30);
//	        
//	        add(payButton);
//	       
//	        
//	        
//	        // Action Listener for the Pay button
//	        payButton.addActionListener(new ActionListener() {
//	            public void actionPerformed(ActionEvent e) {
//	            	
//	                   
//	            	new Payfully();
//	            	 JOptionPane.showMessageDialog(null, "Paid Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
//	                 setVisible(false);
//	                 
//	                 
//	                 
//	                 
//	                 new ElectricityHomePage("aadu","sjfd");
//	               
//	            }
//	        });
//
//	        setVisible(true);
//	    
//	    }
//	
//	public static void main(String args[])
//	{
//		new ResultFrame(12,34,54,4000);
//		 
//	}
//  }
//
