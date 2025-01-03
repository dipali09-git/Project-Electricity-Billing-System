package electricity.billing;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCustomerDetails {
    // Database URL, username, and password
    static final String DB_URL = "jdbc:mysql://localhost:3306/electricitybillingsystem";
    static final String USER = "root";
    static final String PASS = "password";

    public  UpdateCustomerDetails() {
        JFrame frame = new JFrame("Electricity Billing System");
        
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        
        final JTextField meterIdField = new JTextField(20);
        JButton okButton = new JButton("OK");
        
        // Add components to the frame
        frame.setLayout(null);
        JLabel meterIdLabel = new JLabel("Enter MeterId :");
        meterIdLabel.setBounds(50, 50, 250, 30);
        meterIdField.setBounds(170, 50, 150, 30);
        okButton.setBounds(170, 100, 100, 30);
        
        frame.add(meterIdLabel);
        frame.add(meterIdField);
        frame.add(okButton);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String meterId = meterIdField.getText();
                try {
                    // Connect to the database
                    final Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String query = "SELECT * FROM MeterDetails WHERE meterid = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, Integer.parseInt(meterId));
                    
                    ResultSet rs = pstmt.executeQuery();
                    
                    if (rs.next()) { // If meter ID exists
                        final JFrame updateFrame = new JFrame("Update Meter Details");
                        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        updateFrame.setSize(600, 500);
                        updateFrame.setLocationRelativeTo(null);
                        updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        
                        JLabel meterReadingLabel = new JLabel("Meter Reading:");
                        final JTextField meterReadingField = new JTextField(20);
                        meterReadingField.setText(rs.getString("metereading"));
                        
                        JLabel rateLabel = new JLabel("Rate:");
                        final JTextField rateField = new JTextField(20);
                        rateField.setText(rs.getString("rate"));
                        
                        
                        JLabel dueDateLabel = new JLabel("Due Date:");
                        final JTextField dueDateField = new JTextField(20);
                        dueDateField.setText(rs.getString("dueDate"));
                        
                        
                        
                        JButton updateButton = new JButton("Update");
                        
                        // Set layout and bounds
                        updateFrame.setLayout(null);
                        meterReadingLabel.setBounds(70, 50, 150, 30);
                        meterReadingField.setBounds(200, 50, 150, 30);
                        rateLabel.setBounds(70, 100, 100, 30);
                        rateField.setBounds(200, 100, 150, 30);
                      
                        dueDateLabel.setBounds(70, 150, 100, 30);
                        dueDateField.setBounds(200, 150, 150, 30);
                       
                        updateButton.setBounds(150, 300, 100, 30);
                        
                        // Add components to the update frame
                        updateFrame.add(meterReadingLabel);
                        updateFrame.add(meterReadingField);
                        updateFrame.add(rateLabel);
                        updateFrame.add(rateField);
                       
                        updateFrame.add(dueDateLabel);
                        updateFrame.add(dueDateField);
                       
                        updateFrame.add(updateButton);
                        
                        updateFrame.setVisible(true);
                        
                        // Update button action listener
                        updateButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String updateQuery = "UPDATE MeterDetails SET metereading = ?, rate = ?,  Duedate = ?,  WHERE meterid = ?";
                                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                                    updateStmt.setInt(1, Integer.parseInt(meterReadingField.getText()));
                                    updateStmt.setInt(2, Integer.parseInt(rateField.getText()));
                                    
                                    updateStmt.setString(4, (dueDateField.getText()));
                                   
                                    updateStmt.setInt(6, Integer.parseInt(meterIdField.getText()));
                                    
                                    int rowsUpdated = updateStmt.executeUpdate();
                                    if (rowsUpdated > 0) {
                                        JOptionPane.showMessageDialog(null, "Record updated successfully!");
                                        updateFrame.setVisible(false);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error updating record!");
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Meter ID not found!");
                    }
                    
                    // Close the result set and prepared statement (not the connection)
                    rs.close();
                    pstmt.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
public static void main(String[] args) {
	new UpdateCustomerDetails();
	
    
}
}