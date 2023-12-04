import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class Report extends JFrame {

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
					Report frame = new Report();
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
	public Report() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 10, 493, 442);
		contentPane.add(textArea);
	}
	
	public void displayReport(String pat_id)
	{
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
          
			String sql3 = "select title,return_date from book,book_patron where book.isbn=book_patron.isbn and book_patron.pat_id = ? and book_patron.returned='No'";
	        PreparedStatement stmt3 = con.prepareStatement(sql3);
	        stmt3.setString(1, pat_id);
	        ResultSet resultSet3 = stmt3.executeQuery();	        
	        String title,return_date;
	       
	        textArea.append("\t\tReport\n\n\nTitle\t\treturn date\n\n");
	        while(resultSet3.next()) 
	        {
	        	title = resultSet3.getString("title");
	        	return_date = resultSet3.getString("return_date");
	        	textArea.append(title+"\t\t"+return_date+"\n");
	        }
	        
	        stmt3.close();
		}
		catch(Exception ex)
		{
			//e1.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	}

}
