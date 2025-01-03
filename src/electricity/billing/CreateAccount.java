package electricity.billing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import electricity.billing.*;
public class CreateAccount extends JFrame {
	
	
	JTextField custNameField;
	JTextField usernameField;
    JPasswordField passwordField;
    JTextField meteridField;
    JTextArea addressArea;
    JTextField cityField;
    JTextField stateField;
    JTextField emailField;
    JTextField phoneNoField;
    
    public CreateAccount() {
    	
        // Set the frame properties
    	 new JFrame("Create Account");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocation(500,200);
	        setSize(400, 400);
	        setLayout(null);
    	
	    

        // Create the "Create Account" label
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(170, 10, 200, 30);
        add(titleLabel);
        
      
        
        // Labels and text fields for customer details
        JLabel custNameLabel = new JLabel("Customer Name:");
        custNameLabel.setBounds(50, 70, 150, 25);
        custNameField = new JTextField();
        custNameField.setBounds(200, 70, 230, 25);
        add(custNameLabel);
        add(custNameField);
        
       

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 110, 150, 25);
        usernameField = new JTextField();
        usernameField.setBounds(200, 110, 230, 25);
        add(usernameLabel);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 150, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 230, 25);
        add(passwordLabel);
        add(passwordField);

        JLabel meterNoLabel = new JLabel("Meter Id:");
        meterNoLabel.setBounds(50, 190, 150, 25);
  
        add(meterNoLabel);
       
        JLabel addressLabel = new JLabel("Address -");
        addressLabel.setBounds(50, 230, 150, 25);
         addressArea = new JTextArea();
        addressArea.setBounds(200, 230, 230, 50);
        add(addressLabel);
        add(addressArea);
        
        JLabel houseno=new JLabel("House no:");
        houseno.setBounds(50,250,150,25);
        add(houseno);
        
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(50, 300, 150, 25);
        cityField = new JTextField();
        cityField.setBounds(200, 300, 230, 25);
        add(cityLabel);
        add(cityField);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(50, 340, 150, 25);
        stateField = new JTextField();
        stateField.setBounds(200, 340, 230, 25);
        add(stateLabel);
        add(stateField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 380, 150, 25);
         emailField = new JTextField();
        emailField.setBounds(200, 380, 230, 25);
        add(emailLabel);
        add(emailField);

        JLabel phoneNoLabel = new JLabel("Phone No:");
        phoneNoLabel.setBounds(50, 420, 150, 25);
        phoneNoField = new JTextField();
        phoneNoField.setBounds(200, 420, 230, 25);
        add(phoneNoLabel);
        add(phoneNoField);

        // Create and Back buttons
        JButton createButton = new JButton("Create");
        createButton.setBounds(150, 490, 100, 30);
        add(createButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(290, 490, 100, 30);
        add(backButton);

        // Set background color
        getContentPane().setBackground(new Color(200, 200, 210));

        // Load and add image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/electricity/billing/icons/createacct.jpg"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(400, 50, 500, 500);
        add(imageLabel);
        
       
   
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get field values
                String custname = custNameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String address = addressArea.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String email = emailField.getText();
                String phoneno = phoneNoField.getText();

                // Validation regex patterns
                String phoneRegex = "^(\\d{10}|\\d{3}[-\\s]?\\d{3}[-\\s]?\\d{4})$";
                String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                Pattern phonePattern = Pattern.compile(phoneRegex);
                Matcher phoneMatcher = phonePattern.matcher(phoneno);
                
                Pattern passwordPattern = Pattern.compile(passwordRegex);
                Matcher passwordMatcher = passwordPattern.matcher(password);
                
                Pattern emailPattern = Pattern.compile(emailRegex);
                Matcher emailMatcher = emailPattern.matcher(email);

                // Validations
                if (!passwordMatcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters, including one uppercase letter, one special character, and a number.");
                    return;
                }
                
                if (!phoneMatcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Phone number must be 10 digits.");
                    return;
                }
                
                if (!emailMatcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Invalid email format.");
                    return;
                }

                // Check if any field is empty
                if (custname.isEmpty() || username.isEmpty() || password.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || email.isEmpty() || phoneno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required.");
                    return;
                }

                // If everything is valid, insert into the database
                int meterid = new Random().nextInt(10000);
                CreateaccctDatabase c = new CreateaccctDatabase();
                c.insertValueIntoDatabase(meterid, custname, username, password, address, city, state, email, phoneno);

                // Show success message
                JOptionPane.showMessageDialog(null, "Account Created Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new LoginPage();
				setVisible(false);
				
			}
        	
        	
        	
        });
        
        setVisible(true); 
     }
       
    		
    public static void main(String[] args) {
    	
        
             new CreateAccount();
             
           
       
    }
}
