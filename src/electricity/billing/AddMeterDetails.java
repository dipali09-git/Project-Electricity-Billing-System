package electricity.billing;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

 public class AddMeterDetails extends JFrame {
    // JDBC URL, username, and password for the database
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem"; // Example URL
    static final String USER = "root";
    static final String PASSWORD = "password";

    // Declare Swing components
    private JTextField meterIdField, meterReadingField;

    // Constructor to setup the GUI
    public AddMeterDetails() {
        // Create a frame
        setTitle("Add Meter Details");
        setSize(400, 400);
       
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Labels and Text Fields for Inputs
        JLabel meterIdLabel = new JLabel("Meter ID:");
        meterIdLabel.setBounds(20, 20, 100, 30);
        add(meterIdLabel);

        meterIdField = new JTextField();
        meterIdField.setBounds(150, 20, 200, 30);
        add(meterIdField);

        JLabel meterReadingLabel = new JLabel("Meter Reading:");
        meterReadingLabel.setBounds(20, 60, 100, 30);
        add(meterReadingLabel);

        meterReadingField = new JTextField();
        meterReadingField.setBounds(150, 60, 200, 30);
        add(meterReadingField);

        // Button to update the meter details in the database
        JButton updateButton = new JButton("submit");
        updateButton.setBounds(130, 120, 100, 30);
        add(updateButton);

        // Action Listener for the update button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    updateMeterDetails();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    // Method to update meter details and calculate total amount
    private void updateMeterDetails() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmtCharges = null;

        try {
            // Get input values
            int meterId = Integer.parseInt(meterIdField.getText());
            int metereading = Integer.parseInt(meterReadingField.getText());

            // Connect to the database
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Fetch charges from the charges table
            String chargesQuery = "SELECT rate, servicecharge, gst, tax FROM charges";
            stmtCharges = conn.prepareStatement(chargesQuery);
            ResultSet rs = stmtCharges.executeQuery();

            double rate = 0, serviceCharge = 0, gst = 0, tax = 0;

            if (rs.next()) {
                rate = rs.getDouble("rate");
                serviceCharge = rs.getDouble("servicecharge");
                gst = rs.getDouble("gst");
                tax = rs.getDouble("tax");
            }

            // Calculate total amount
            double baseAmount =  (metereading * rate);
            double gstAmount = (gst / 100) * baseAmount;
            double taxAmount = (tax / 100) * baseAmount;
            double amountpay = baseAmount + gstAmount + taxAmount + serviceCharge;

            // Insert the new meter details into the database
            String sql = "INSERT INTO MeterDetails (metereading, amountpay,meterid) VALUES (?, ?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, metereading);
            stmt.setDouble(2, amountpay);
        stmt.setInt(3, meterId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Open the result frame and pass calculated data
               
                JOptionPane.showMessageDialog(this, "Meter details inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new ResultFrame(meterId, metereading, rate, amountpay);
                setVisible(false);
           } 
            else
           {
                JOptionPane.showMessageDialog(this, "Meter ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid details", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close the resources
            if (stmtCharges != null) stmtCharges.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Run the application
        new AddMeterDetails();
    }
}
