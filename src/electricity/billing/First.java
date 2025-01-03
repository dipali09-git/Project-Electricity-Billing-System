package electricity.billing;



	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class First {
		public First()
		{
			 JFrame f1 = new JFrame();
		        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        f1.setSize(800, 600); // Set default JFrame size
		        f1.setLayout(null); // Use null layout for absolute positioning

		        // Load the image
		        ImageIcon imageIcon = new ImageIcon(First.class.getResource("/electricity/billing/icons/e6.jpg")); // Replace with your image path
		        
		        // Check if the image was loaded correctly
		        if (imageIcon.getIconWidth() == -1) {
		            System.out.println("Image not found!");
		            return; // Exit if the image is not found
		        }

		        JLabel label = new JLabel(imageIcon);
		       
		        // Center the label in the JFrame
		        int labelX = (f1.getWidth() - imageIcon.getIconWidth()) / 2;
		        int labelY = (f1.getHeight() - imageIcon.getIconHeight()) / 2;
		        label.setBounds(labelX, labelY, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		        
		        // Add the label to the frame
		        f1.add(label);
		        
		        // Make the frame visible
		        f1.setVisible(true);
		        
		        // Animation loop to resize and move the frame
		        int i;
		        int x1 = 1; // Renamed to avoid conflict with variable x used for position
		        for (i = 2; i <= 600; i += 4, x1 += 1) {
		            f1.setLocation(700 - ((i + x1) / 2), 400 - (i / 2));
		            f1.setSize(i + x1, i);
		            
		            try {
		                Thread.sleep(10); // Delay for animation effect
		               
		                
		                
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		           
		        }
		        new LoginPage();
		        f1.setVisible(false);
		}
	    public static void main(String[] args) {
	       new First();
	       
	    }
	}
