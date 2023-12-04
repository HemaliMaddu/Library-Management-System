import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ReturnConstraints 
{
	public double checkConstraints(String isbn)
	{
		 double fine = 0;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
            
            String sql1 = "select issue_date from book_patron where isbn in (?) and returned='No'";
	        PreparedStatement stmt1 = con.prepareStatement(sql1);
	        stmt1.setString(1, isbn);
	        ResultSet resultSet1 = stmt1.executeQuery();	        
	        String issue_date = null;
	        while(resultSet1.next()) 
	        {
	        	issue_date = resultSet1.getString("issue_date");
	        }
	        
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDateTime now = LocalDateTime.now();
	        
	        if(dtf.format(now).equals(issue_date)) 
	        {
	        	fine=-1;
				return fine;
	        }
	        else
	        {
	        	String sql2 = "select position from patron where pat_id in (select pat_id from book_patron where isbn in (?))";
		        PreparedStatement stmt2 = con.prepareStatement(sql2);
		        stmt2.setString(1, isbn);
		        ResultSet resultSet2 = stmt2.executeQuery();	        
		        String position = null;
		        while(resultSet2.next()) 
		        {
		        	position = resultSet2.getString("position");
		        }
		        
		        String sql3 = "select datediff(curdate(),return_date) as days_late from book_patron where isbn in (?) and returned='No'";
		        PreparedStatement stmt3 = con.prepareStatement(sql3);
		        stmt3.setString(1, isbn);
		        ResultSet resultSet3 = stmt3.executeQuery();	        
		        int days_late = 0;
		        while(resultSet3.next()) 
		        {
		        	days_late = resultSet3.getInt("days_late");
		        }
		        
		        if(position.equals("Faculty"))
		        {
		        	if(days_late < 0) fine = 0;
		        	else fine = 0.50 * days_late;
		        }
		        else if(position.equals("Student"))
		        {
		        	if(days_late < 0) fine = 0;
		        	else fine = 1 * days_late;
		        }
		        stmt2.close();
		        stmt3.close();
		        
		        String sql4 = "update book_patron set return_date = curdate() where isbn in (?) and returned='No'";
                PreparedStatement stmt4 = con.prepareStatement(sql4);
                stmt4.setString(1, isbn);
                stmt4.executeUpdate();
                
                String sql5 = "update book set availability = 'Yes' where isbn in (?)";
                PreparedStatement stmt5 = con.prepareStatement(sql5);
                stmt5.setString(1, isbn);
                stmt5.executeUpdate();
                
                String sql6 = "update book_patron set fine = ? where isbn in (?) and returned='No'";
                PreparedStatement stmt6 = con.prepareStatement(sql6);
                stmt6.setDouble(1, fine);
                stmt6.setString(2, isbn);
                stmt6.executeUpdate();
                
                String sql7 = "update book_patron set returned = 'Yes' where isbn in (?) and returned='No'";
                PreparedStatement stmt7 = con.prepareStatement(sql7);    
                stmt7.setString(1, isbn);
                stmt7.executeUpdate();
                
                stmt4.close();
                stmt5.close();
                stmt6.close();
                stmt7.close();
                
	        }
	        stmt1.close();
	        con.close();
		}
		catch(Exception ex)
		{
			//e1.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return fine;
		
	}

}
