import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPage frame = new MenuPage();
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
	public MenuPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_new_patron = new JButton("Register New Patron");
		button_new_patron.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegisterNewPatron rnp = new RegisterNewPatron();
				rnp.setVisible(true);
			}
		});
		button_new_patron.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_new_patron.setBounds(78, 119, 161, 55);
		contentPane.add(button_new_patron);
		
		JButton button_issue = new JButton("Issue Book");
		button_issue.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				IssueBook issue = new IssueBook();
				issue.setVisible(true);
			}
		});
		button_issue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_issue.setBounds(78, 285, 161, 55);
		contentPane.add(button_issue);
		
		JButton button_return = new JButton("Return Book");
		button_return.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				BookReturn returnBook = new BookReturn();
				returnBook.setVisible(true);
			}
		});
		button_return.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_return.setBounds(276, 285, 161, 55);
		contentPane.add(button_return);
		
		JButton button_new_book = new JButton("Add New Book");
		button_new_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				AddBook ab = new AddBook();
				ab.setVisible(true);
			}
		});
		button_new_book.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_new_book.setBounds(78, 204, 161, 55);
		contentPane.add(button_new_book);
		
		JButton button_delete_book = new JButton("Delete Book");
		button_delete_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeleteBook db = new DeleteBook();
				db.setVisible(true);
			}
		});
		button_delete_book.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_delete_book.setBounds(276, 204, 161, 55);
		contentPane.add(button_delete_book);
		
		JButton button_delete_patron = new JButton("Delete Patron");
		button_delete_patron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeletePatron dp = new DeletePatron();
				dp.setVisible(true);
			}
		});
		button_delete_patron.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_delete_patron.setBounds(276, 119, 161, 55);
		contentPane.add(button_delete_patron);
	}
}
