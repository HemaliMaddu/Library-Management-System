import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AddBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_title;
	private JTextField txt_author;
	private JTextField txt_isbn;
	private JTextField txt_shelf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_addBook = new JLabel("Add New Book");
		label_addBook.setBounds(209, 28, 145, 16);
		label_addBook.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_addBook);
		
		JLabel label_title = new JLabel("Enter Book Title");
		label_title.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_title.setBounds(73, 157, 97, 13);
		contentPane.add(label_title);
		
		JLabel label_author = new JLabel("Enter Book Author");
		label_author.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_author.setBounds(73, 211, 118, 13);
		contentPane.add(label_author);
		
		JLabel label_category = new JLabel("Enter Category");
		label_category.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_category.setBounds(73, 312, 97, 26);
		contentPane.add(label_category);
		
		txt_title = new JTextField();
		txt_title.setBounds(243, 155, 194, 19);
		contentPane.add(txt_title);
		txt_title.setColumns(10);
		
		txt_author = new JTextField();
		txt_author.setBounds(243, 209, 194, 19);
		contentPane.add(txt_author);
		txt_author.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnpat = new JRadioButton("Lend to Patron");
		rdbtnpat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnpat.setBounds(222, 315, 127, 21);
		contentPane.add(rdbtnpat);
		
		JRadioButton rdbtnlib = new JRadioButton("In Library Use Only");
		rdbtnlib.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnlib.setBounds(348, 315, 139, 21);
		contentPane.add(rdbtnlib);
		
		buttonGroup.add(rdbtnpat);
		buttonGroup.add(rdbtnlib);
		JLabel label_isbn = new JLabel("Enter ISBN");
		label_isbn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_isbn.setBounds(73, 102, 78, 16);
		contentPane.add(label_isbn);
		
		txt_isbn = new JTextField();
		txt_isbn.setBounds(243, 102, 194, 20);
		contentPane.add(txt_isbn);
		txt_isbn.setColumns(10);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				    long isbn = Long.parseLong(txt_isbn.getText());
				    String title = txt_title.getText();
			        String author = txt_author.getText();
			        String code = generateBookCode(title,author);
			        String shelfNo = txt_shelf.getText();
			        String category;
	                 if (rdbtnpat.isSelected()) {
	                     category = "Patron";
	                 } else if (rdbtnlib.isSelected()) {
	                     category = "Library";
	                 } else {
	                     category = ""; // or set a default value
	                 }
			       
			      
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
			            String query = "INSERT INTO Book (ISBN, title, author,category,code, shelf_no) VALUES (?, ?, ?, ?, ?, ?)";
			            PreparedStatement preparedStatement = connection.prepareStatement(query);
			            preparedStatement.setLong(1, isbn);
			            preparedStatement.setString(2, title);
			            preparedStatement.setString(3, author);	
			            preparedStatement.setString(4, category);
			            preparedStatement.setString(5, code);
			            preparedStatement.setString(6, shelfNo);
			            preparedStatement.executeUpdate();

			            JOptionPane.showMessageDialog( null, "Book added successfully.");
			        } catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			        
				
			
		});
		btnAddBook.setBounds(209, 379, 99, 26);
		contentPane.add(btnAddBook);
		
		JLabel lblEnterShelfNo = new JLabel("Enter Shelf No (Eg: 123.A)");
		lblEnterShelfNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterShelfNo.setBounds(73, 264, 156, 17);
		contentPane.add(lblEnterShelfNo);
		
		txt_shelf = new JTextField();
		txt_shelf.setBounds(243, 264, 194, 19);
		contentPane.add(txt_shelf);
		txt_shelf.setColumns(10);
	  }
	 
	    private static String generateBookCode(String title, String author) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2")) {
	            // Query to check if a book with the same title and author already exists
	            String checkBookQuery = "SELECT code FROM Book WHERE title = ? AND author = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(checkBookQuery);
	            preparedStatement.setString(1, title);
	            preparedStatement.setString(2, author);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                // Book with the same title and author exists, retrieve its code
	                String code = resultSet.getString("code");
	                return code;
	            } else {
	                // Book doesn't exist, generate a new code
	                return generateNewCode(connection);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    private static String generateNewCode(Connection conn) {
	        try {
	            // Query to get the next available code based on existing books
	            String getNextCodeQuery = "SELECT MAX(code) FROM Book";
	            PreparedStatement codeStatement = conn.prepareStatement(getNextCodeQuery);
	            ResultSet resultSet = codeStatement.executeQuery();

	            if (resultSet.next()) {
	                String maxCode = resultSet.getString(1);
	                if (maxCode != null) {
	                    // Increment the code
	                    char codePrefix = maxCode.charAt(0);
	                    int codeDigit = Character.getNumericValue(maxCode.charAt(1));
	                    if (codeDigit < 9) {
	                        codeDigit++;
	                    } else {
	                        codeDigit = 0;
	                        codePrefix++;
	                    }
	                    resultSet.close();
	                    codeStatement.close();
	                    String newCode = String.valueOf(codePrefix) + codeDigit;
	                    return newCode;
	                }
	            }
	            return "A0";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}

	       
	
