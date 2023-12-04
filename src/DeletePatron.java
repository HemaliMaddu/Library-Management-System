import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DeletePatron extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePatron frame = new DeletePatron();
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
	public DeletePatron() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterPatronId = new JLabel("Enter Patron ID");
		lblEnterPatronId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterPatronId.setBounds(82, 129, 131, 19);
		contentPane.add(lblEnterPatronId);
		
		JLabel label_deletePat = new JLabel("Delete Patron");
		label_deletePat.setBounds(192, 41, 118, 26);
		label_deletePat.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_deletePat);
		
		txt_id = new JTextField();
		txt_id.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_id.setBounds(252, 130, 157, 19);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String pat_id = txt_id.getText();
			      
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
			            String query = "DELETE FROM Patron WHERE pat_id = ?";
			            PreparedStatement preparedStatement = connection.prepareStatement(query);
			            preparedStatement.setString(1, pat_id);
			            preparedStatement.executeUpdate();

			            String message = "Patron ID: " + pat_id + ". Patron deleted successfully.";
			            JOptionPane.showMessageDialog(null, message);

			        } catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(194, 243, 85, 21);
		contentPane.add(btnSubmit);
		
		
	
	}
}
