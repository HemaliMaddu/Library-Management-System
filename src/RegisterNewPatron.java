import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterNewPatron extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterNewPatron frame = new RegisterNewPatron();
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
	public RegisterNewPatron() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddPatronDetails = new JLabel("Register New Patron");
		lblAddPatronDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddPatronDetails.setBounds(180, 37, 178, 24);
		contentPane.add(lblAddPatronDetails);
		
		JLabel lblEnterPatronName = new JLabel("Enter Patron Name ");
		lblEnterPatronName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterPatronName.setBounds(83, 124, 127, 24);
		contentPane.add(lblEnterPatronName);
		
		JLabel lblSelectUserType = new JLabel("Select User Type");
		lblSelectUserType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectUserType.setBounds(94, 204, 116, 24);
		contentPane.add(lblSelectUserType);
		
		txt_name = new JTextField();
		txt_name.setBounds(261, 128, 133, 19);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnStudent.setBounds(261, 206, 103, 21);
		contentPane.add(rdbtnStudent);
		
		JRadioButton rdbtnFaculty = new JRadioButton("Faculty");
		rdbtnFaculty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnFaculty.setBounds(261, 248, 103, 21);
		contentPane.add(rdbtnFaculty);
		
		buttonGroup.add(rdbtnStudent);
        buttonGroup.add(rdbtnFaculty);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				 
				    String patron_name = txt_name.getText();
				    String position;
	                 if (rdbtnStudent.isSelected()) {
	                     position = "Student";
	                 } else if (rdbtnFaculty.isSelected()) {
	                     position = "Faculty";
	                 } else {
	                     position = ""; // or set a default value
	                 }
			        String pat_id = generatePatronID(position);
			      
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
			            String query = "INSERT INTO Patron (pat_id, patron_name, position) VALUES (?, ?, ?)";
			            PreparedStatement preparedStatement = connection.prepareStatement(query);
			            preparedStatement.setString(1, pat_id);
			            preparedStatement.setString(2, patron_name);
			            preparedStatement.setString(3, position);
			            preparedStatement.executeUpdate();

			            String message = "Patron ID: " + pat_id + ".\nPatron registered successfully.";
			            JOptionPane.showMessageDialog(null, message);

			        } catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
		});
		btnSubmit.setBounds(204, 354, 85, 21);
		contentPane.add(btnSubmit);
	}
	
	private static String generatePatronID(String position) {
	    if (position.equals("Student")) {
	        int nextStudentNumber = getNextPatronNumber("lib", position);
	        if (nextStudentNumber > 699) {
	            return "";
	        }
	        return "lib" + String.format("%03d", 100 + nextStudentNumber);
	    } else if (position.equals("Faculty")) {
	        int nextFacultyNumber = getNextPatronNumber("lib", position);
	        if (nextFacultyNumber > 999) {
	            return "";
	        }
	        return "lib" + String.format("%03d", 700 + nextFacultyNumber);
	    } else {
	        return "";
	    }
	}

	private static int getNextPatronNumber(String prefix, String position) {
	    int nextPatronNumber = 0;

	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {            
           
	        String query = "SELECT MAX(pat_id) FROM Patron WHERE position = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, position);

	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            String maxPatronID = resultSet.getString(1);
	            if (maxPatronID != null && maxPatronID.startsWith(prefix)) {
	                // Extract the numeric part and increment by 1
	                nextPatronNumber = Integer.parseInt(maxPatronID.substring(5)) + 1;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return nextPatronNumber;
	}

}
