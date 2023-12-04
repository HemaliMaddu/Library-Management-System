import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class HomePage extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel Admin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setDefaultCloseOperation(HomePage.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		Admin = new JPanel();
		Admin.setBorder(new EmptyBorder(7, 7, 7, 7));

		setContentPane(Admin);
		
		JButton button_patron = new JButton("Patron");
		button_patron.setBounds(106, 128, 151, 47);
		button_patron.setForeground(new Color(0, 0, 0));
		button_patron.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_patron.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Patron pt = new Patron();
				pt.setVisible(true);
			}
		});
		Admin.setLayout(null);
		Admin.add(button_patron);
		
		JButton button_add_librarian = new JButton("Add Librarian");
		button_add_librarian.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AddLibrarian al = new AddLibrarian();
				al.setVisible(true);
				
			}
		});
		button_add_librarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_add_librarian.setBounds(199, 297, 151, 47);
		Admin.add(button_add_librarian);
		
		JLabel label_continueAs = new JLabel("Continue As...");
		label_continueAs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_continueAs.setBounds(199, 37, 151, 64);
		Admin.add(label_continueAs);
		
		JButton button_delete_librarian = new JButton("Delete Librarian");
		button_delete_librarian.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeleteLibrarian dl = new DeleteLibrarian();
				dl.setVisible(true);
			
			}
		});
		button_delete_librarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_delete_librarian.setBounds(199, 355, 151, 47);
		Admin.add(button_delete_librarian);
		
		JButton button_librarian = new JButton("Librarian");
		button_librarian.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LibrarianLogin ll = new LibrarianLogin();
				ll.setVisible(true);
			}
		});
		button_librarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_librarian.setBounds(288, 129, 151, 47);
		Admin.add(button_librarian);
		
		JLabel lblNewLabel = new JLabel("Or Choose a Function...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(166, 234, 232, 25);
		Admin.add(lblNewLabel);
	}
}
