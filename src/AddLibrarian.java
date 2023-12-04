import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class AddLibrarian extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_librarian;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLibrarian frame = new AddLibrarian();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddLibrarian() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterLibrarianName = new JLabel("Enter Librarian Name");
		lblEnterLibrarianName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterLibrarianName.setBounds(70, 104, 145, 26);
		contentPane.add(lblEnterLibrarianName);
		
		JLabel label_register = new JLabel("Register New Librarian");
		label_register.setBounds(171, 20, 176, 19);
		label_register.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_register);
		
		JLabel label_pwd = new JLabel("Enter Librarian Password");
		label_pwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_pwd.setBounds(52, 189, 176, 31);
		contentPane.add(label_pwd);
		
		txt_librarian = new JTextField();
		txt_librarian.setBounds(238, 109, 141, 19);
		contentPane.add(txt_librarian);
		txt_librarian.setColumns(10);
		

		passwordField = new JPasswordField();
		passwordField.setBounds(238, 195, 141, 19);
		contentPane.add(passwordField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String librarian_name = txt_librarian.getText();
				char[] password = passwordField.getPassword();
	            String passwordText = new String(password);
	            int librarian_id = generateLibrarianID();
		      
		        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
		            String query = "INSERT INTO Librarian (librarian_name, password, librarian_id ) VALUES (?, ?, ?)";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setString(1, librarian_name);
		            preparedStatement.setString(2, passwordText);
		            preparedStatement.setInt(3, librarian_id);
		            preparedStatement.executeUpdate();

		            String message = "Librarian ID: "  + librarian_id +".\nLibrarian registered successfully.";
		            JOptionPane.showMessageDialog(null, message);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
			
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(199, 298, 85, 21);
		contentPane.add(btnSubmit);
		
	}
	
	private static int generateLibrarianID() {
	    // Connect to the database and get the last inserted librarian_id
	    int lastLibrarianID = 999; // Initialize with 999 to handle the case of an empty table

	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
	         PreparedStatement statement = connection.prepareStatement("SELECT MAX(librarian_id) FROM Librarian")) {

	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            lastLibrarianID = resultSet.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Increment the last librarian_id and ensure it starts from 1000
	    int newLibrarianID = lastLibrarianID + 1;
	    
	    // Ensure the librarian_id is 4 digits and pad with zeros if needed
	    if (newLibrarianID < 1000) {
	        newLibrarianID = 1000;
	    } else if (newLibrarianID > 9999) {
	       return 0;
	    }

	    return newLibrarianID;
	}

}
