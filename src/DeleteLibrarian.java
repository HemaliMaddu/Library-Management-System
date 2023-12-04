import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DeleteLibrarian extends JFrame {

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
					DeleteLibrarian frame = new DeleteLibrarian();
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
	public DeleteLibrarian() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterLibrarianId = new JLabel("Enter Librarian ID");
		lblEnterLibrarianId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterLibrarianId.setBounds(73, 123, 139, 33);
		contentPane.add(lblEnterLibrarianId);
		
		JLabel lblDeleteLibrarian = new JLabel("Delete Librarian");
		lblDeleteLibrarian.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDeleteLibrarian.setBounds(195, 22, 160, 33);
		contentPane.add(lblDeleteLibrarian);
		
		txt_id = new JTextField();
		txt_id.setBounds(229, 131, 126, 19);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String librarian_id = txt_id.getText();
			      
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
			            String query = "DELETE FROM Librarian WHERE librarian_id = ?";
			            PreparedStatement preparedStatement = connection.prepareStatement(query);
			            preparedStatement.setString(1, librarian_id);
			            preparedStatement.executeUpdate();

			            String message = "Librarian ID: " + librarian_id + ".\nLibrarian deleted successfully.";
			            JOptionPane.showMessageDialog(null, message);

			        } catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(181, 251, 85, 21);
		contentPane.add(btnSubmit);
	}

}
