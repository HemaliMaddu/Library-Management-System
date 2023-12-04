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

public class DeleteBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBook frame = new DeleteBook();
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
	public DeleteBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterCode = new JLabel("Enter Book Code");
		lblEnterCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterCode.setBounds(89, 116, 111, 28);
		contentPane.add(lblEnterCode);
		
		JLabel lblDeleteBook = new JLabel("Delete Book");
		lblDeleteBook.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDeleteBook.setBounds(181, 27, 184, 35);
		contentPane.add(lblDeleteBook);
		
		JLabel label = new JLabel("New label");
		label.setBounds(116, 386, 165, -142);
		contentPane.add(label);
		
		txt_code = new JTextField();
		txt_code.setBounds(210, 122, 138, 19);
		contentPane.add(txt_code);
		txt_code.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = txt_code.getText();
			      
		        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
		            String query = "DELETE FROM Book WHERE code = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setString(1, code);
		            preparedStatement.executeUpdate();

		            String message = "Book deleted successfully.";
		            JOptionPane.showMessageDialog(null, message);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
			
			
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(181, 237, 85, 21);
		contentPane.add(btnSubmit);
	}
}
