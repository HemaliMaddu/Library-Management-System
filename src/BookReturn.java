import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BookReturn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_isbn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookReturn frame = new BookReturn();
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
	public BookReturn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_heading = new JLabel("Book Return");
		label_heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_heading.setBounds(212, 53, 117, 35);
		contentPane.add(label_heading);
		
		JLabel label_isbn = new JLabel("ISBN");
		label_isbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_isbn.setBounds(131, 175, 62, 27);
		contentPane.add(label_isbn);
		
		text_isbn = new JTextField();
		text_isbn.setBounds(239, 173, 255, 35);
		contentPane.add(text_isbn);
		text_isbn.setColumns(10);
		
		//JTextArea textArea = new JTextArea();
		
		JButton button_submit = new JButton("Submit");
		button_submit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String isbn = text_isbn.getText();
				ReturnConstraints rc = new ReturnConstraints();
				double fine = rc.checkConstraints(isbn);
				
				if(fine==-1)
				{
					JOptionPane.showMessageDialog(null, "Book cannot be returned on the same day of issue");
					return;
						 				
				}
				else if (fine==0)
				{
					JOptionPane.showMessageDialog(null, "Need not pay any fine");
					return;
				}
				else if (fine>0)
				{
					BookReturnFinePage fp = new BookReturnFinePage(fine,isbn);
					fp.collectFine(fine);
					fp.setVisible(true);
				}
				
			}

			
		});
		button_submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_submit.setBounds(212, 290, 117, 41);
		contentPane.add(button_submit);
	}

}
