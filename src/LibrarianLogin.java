
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LibrarianLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_id;
	private JPasswordField passwordField;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianLogin frame = new LibrarianLogin();
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
	public LibrarianLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_pwd = new JLabel("Enter Librarian Password");
		label_pwd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_pwd.setBounds(77, 196, 147, 27);
		contentPane.add(label_pwd);
		
		JLabel lblLibrarianLogin = new JLabel("Librarian Login");
		lblLibrarianLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLibrarianLogin.setBounds(185, 20, 163, 34);
		contentPane.add(lblLibrarianLogin);
		
		JLabel label_libId = new JLabel("Enter Librarian ID");
		label_libId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_libId.setBounds(77, 107, 147, 34);
		contentPane.add(label_libId);
		
		txt_id = new JTextField();
		txt_id.setBounds(258, 116, 163, 19);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
        
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int librarianID = Integer.parseInt(txt_id.getText());	               
	            char[] password = passwordField.getPassword();
	            String passwordText = new String(password);
                if (authenticateLibrarian(librarianID, passwordText)) 
                {
	                    // Successful login
	                    JOptionPane.showMessageDialog(null, "Login Successful");
	                    MenuPage mp = new MenuPage();
	                    mp.setVisible(true);
	            } 
	            else {
	                    // Failed login
	                    JOptionPane.showMessageDialog(null, "Login Failed. Please try again.");
	                }

		        } 
		});
		btnSubmit.setBounds(196, 306, 85, 21);
		contentPane.add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(258, 201, 163, 19);
		contentPane.add(passwordField);
	}
	
	private boolean authenticateLibrarian(int librarianID, String password) {
        // Replace with your database connection code and query
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {	          
            String query = "SELECT * FROM librarian WHERE librarian_id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, librarianID);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            // If a matching row is found, authentication is successful
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
