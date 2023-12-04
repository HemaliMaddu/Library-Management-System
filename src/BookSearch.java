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

public class BookSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch frame = new BookSearch();
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
	public BookSearch() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 1200, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setBounds(10, 10, 1166, 342);
		contentPane.add(textArea);
	}
	
	public void searchAndDisplay(String title, String author)
	{
		try 
		{
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
	        
	        String sql = "select * from book where author = (?) and title = (?) ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, author);
			stmt.setString(2, title);
			ResultSet resultSet1 = stmt.executeQuery();
			boolean foundBooks = false;
			
			/*if(!resultSet1.next())
			{
				//textArea.append("No books available with the given author and title");
				JOptionPane.showMessageDialog(null, "No books available with the given author and title");
				return;
			}
			*/
		
			textArea.append("ISBN\t\tBook Code\t\tTitle\t\tAuthor\t\t\tCategory\t\tAvailability\t\tShelf Number");
			textArea.append("\n");
			textArea.append("\n");
						
			String isbn=null,code=null,bk_author=null,bk_title=null,category=null,avail=null,shelf=null;
			while(resultSet1.next()) 
			{
				isbn = resultSet1.getString("isbn");
				bk_title = resultSet1.getString("title");
				bk_author = resultSet1.getString("author");
				code = resultSet1.getString("code");					
				category = resultSet1.getString("category");
				shelf = resultSet1.getString("shelf_no");
				avail = resultSet1.getString("availability");
				
				
				textArea.append(isbn+"\t"+code+"\t\t"+bk_title+"\t\t"+bk_author+"\t\t"+category+"\t\t"+avail+"\t\t"+shelf);
				
				System.out.println(isbn+"\t"+code+"\t\t"+bk_title+"\t\t"+bk_author+"\t\t"+category+"\t\t"+avail+"\t\t"+shelf);
				textArea.append("\n");
				textArea.append("\n");
				
				foundBooks=true;
				
			}
			if(foundBooks) return;
			
			if (!foundBooks) {
		        textArea.append("No books available with the given author and title");
		        return;
		    }
			
			stmt.close();
			con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	}
	

}
