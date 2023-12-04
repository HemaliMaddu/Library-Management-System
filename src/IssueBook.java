import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent; 

public class IssueBook extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_pat_id;
	private JTextField text_isbn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
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
	public IssueBook() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Issue");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(209, 33, 96, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Patron ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(94, 101, 118, 14);
		contentPane.add(lblNewLabel_1);
		
		text_pat_id = new JTextField();
		text_pat_id.setBounds(250, 100, 143, 20);
		contentPane.add(text_pat_id);
		text_pat_id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter ISBN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(94, 158, 118, 14);
		contentPane.add(lblNewLabel_2);
		
		text_isbn = new JTextField();
		text_isbn.setColumns(10);
		text_isbn.setBounds(250, 157, 143, 20);
		contentPane.add(text_isbn);
		
		JButton btnNewButton = new JButton("Issue");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String pat_id = text_pat_id.getText();
				String isbn = text_isbn.getText();
				IssueConstraints ic = new IssueConstraints();
				int flag = ic.checkConstraints(isbn, pat_id);
				
				switch(flag)
				{
					case -1:
						 JOptionPane.showMessageDialog(null, "Book Not available");
						 break;
					case 0:
						try
						{
							Class.forName("com.mysql.cj.jdbc.Driver");
				            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
				          
							String sql1 = "INSERT INTO book_patron (isbn , pat_id , issue_date , return_date) VALUES (?, ?, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY))";
		                    PreparedStatement pstmt1 = con.prepareStatement(sql1);
		                    pstmt1.setString(1, text_isbn.getText());
		                    pstmt1.setString(2, text_pat_id.getText());	
		                    pstmt1.executeUpdate();
		                    
		                    String sql2 = "update book set availability = 'No' where isbn in (?)";
		                    PreparedStatement pstmt2 = con.prepareStatement(sql2);
		                    pstmt2.setString(1, text_isbn.getText());
		                    pstmt2.executeUpdate();
		                    
		                    JOptionPane.showMessageDialog(null, "Issued successfully");
		                    
		                    Report rt = new Report();
		                    rt.displayReport(pat_id);
		                    rt.setVisible(true);
		                    
		                    con.close();
		                    pstmt1.close();
		                    pstmt2.close();
						}
						catch(Exception ex)
						{
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
						
						
						break;
						
					case 1:
						 JOptionPane.showMessageDialog(null, "Faculty cannot borrow more than 6 books");
						 break;
					case 2:
						 JOptionPane.showMessageDialog(null, "Student cannot borrow more than 4 books");
						 break;
					case 3:
						 JOptionPane.showMessageDialog(null, "Book in Library use only");
						 break;
					case 4:
						 JOptionPane.showMessageDialog(null, "Patron has a pending fine");
						 break;
					case 5:
						 JOptionPane.showMessageDialog(null, "Book just returned with time gap of less than 1 day");
						 break;
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(192, 229, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("!!Please check Patron ID and Book taken carefully!!");
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(110, 295, 283, 14);
		contentPane.add(lblNewLabel_3);
	}
}
