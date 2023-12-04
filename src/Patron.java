import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Patron extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_title;
	private JTextField text_author;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patron frame = new Patron();
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
	public Patron() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_heading = new JLabel("Search by book title or author");
		label_heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_heading.setBounds(142, 46, 287, 60);
		contentPane.add(label_heading);
		
		JLabel label_title = new JLabel("Title");
		label_title.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_title.setBounds(147, 164, 70, 46);
		contentPane.add(label_title);
		
		JLabel label_author = new JLabel("Author");
		label_author.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_author.setBounds(142, 251, 70, 46);
		contentPane.add(label_author);
		
		JButton button_search = new JButton("Search");
		button_search.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//searchBook();
				String title = text_title.getText();
				String author = text_author.getText();
				
				BookSearch bs = new BookSearch();
				bs.searchAndDisplay(title,author);
				bs.setVisible(true);
			}
		});
		button_search.setBounds(281, 364, 119, 46);
		contentPane.add(button_search);
		
		text_title = new JTextField();
		text_title.setBounds(259, 174, 119, 31);
		contentPane.add(text_title);
		text_title.setColumns(10);
		
		text_author = new JTextField();
		text_author.setBounds(259, 261, 119, 31);
		contentPane.add(text_author);
		text_author.setColumns(10);
		
		JButton button_back = new JButton("Back");
		button_back.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				HomePage homePg = new HomePage();
				homePg.setVisible(true);
			}
		});
		button_back.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_back.setBounds(142, 364, 119, 46);
		contentPane.add(button_back);
	}
	
	public void searchBook()
	{
		String title = text_title.getText();
		String author = text_author.getText();
		
		BookSearch bs = new BookSearch();
		bs.searchAndDisplay(title,author);
		bs.setVisible(true);
	}

}
