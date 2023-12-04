import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class BookReturnFinePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookReturnFinePage frame = new BookReturnFinePage(0.0,null);
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
	public BookReturnFinePage(double fine, String isbn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_heading = new JLabel("Book Return");
		label_heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_heading.setBounds(215, 63, 115, 33);
		contentPane.add(label_heading);
		
		JButton button_fineCollected = new JButton("Fine Collected");
		button_fineCollected.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null, "Fine Collected Successfully!");
				return;
			}
		});
		button_fineCollected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_fineCollected.setBounds(79, 365, 157, 43);
		contentPane.add(button_fineCollected);
		
		JButton button_fineNotCollected = new JButton("Fine Not Collected");
		button_fineNotCollected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
		            
		            String sql = "update patron set Fine = ? where pat_id in (select pat_id from book_patron where isbn in (?))";
	                PreparedStatement stmt = con.prepareStatement(sql);
	                stmt.setDouble(1, fine);
	                stmt.setString(2, isbn);
	                stmt.executeUpdate();
	                
	                stmt.close();
	                con.close();
	                
	                JOptionPane.showMessageDialog(null, "Fine Pending: "+fine);
					return;
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

				
			}
		});
		button_fineNotCollected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_fineNotCollected.setBounds(289, 365, 157, 43);
		contentPane.add(button_fineNotCollected);
		
		textArea = new JTextArea();
		textArea.setBounds(104, 187, 342, 78);
		contentPane.add(textArea);
	}
	public void collectFine(double fine)
	{
		textArea.append("Fine to be paid: "+fine);
	}
}
