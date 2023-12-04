import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 

public class IssueConstraints 
{
	public int checkConstraints(String isbn,String pat_id)
	{
		int flag=0;
		try 
		{
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system", "root", "hema1suma2");
	        
	        String sql6 = "select availability from book where isbn in (?)";
	        PreparedStatement stmt6 = con.prepareStatement(sql6);
	        stmt6.setString(1, isbn);
	        ResultSet resultSet6 = stmt6.executeQuery();	        
	        String avail = null;
	        while(resultSet6.next()) 
	        {
	        	avail = resultSet6.getString("availability");
	        }
	        
	        if(avail.equals("Yes")) 
	        {
		        String sql1 = "select count(*) as numIssued from book_patron where pat_id = ? and returned='No'";
		        PreparedStatement stmt1 = con.prepareStatement(sql1);
		        stmt1.setString(1, pat_id);
		        ResultSet resultSet1 = stmt1.executeQuery();	        
		        int numIssued=0;
		        while(resultSet1.next()) 
		        {
		        	numIssued = resultSet1.getInt("numIssued");
		        }
		        
		        String sql2 = "select position from patron where pat_id in (?)";
		        PreparedStatement stmt2 = con.prepareStatement(sql2);
		        stmt2.setString(1, pat_id);
		        ResultSet resultSet2 = stmt2.executeQuery();	        
		        String position = null;
		        while(resultSet2.next()) 
		        {
		        	position = resultSet2.getString("position");
		        }
		        
		        if(position.equals("Faculty") && numIssued >= 6) flag = 1;
		        if(position.equals("Student") && numIssued >= 4) flag = 2;
		        
		        String sql3 = "select category from book where isbn in (?)";
		        PreparedStatement stmt3 = con.prepareStatement(sql3);
		        stmt3.setString(1, isbn);
		        ResultSet resultSet3 = stmt3.executeQuery();	        
		        String category = null;
		        while(resultSet3.next()) 
		        {
		        	category = resultSet3.getString("category");
		        }
		        
		        if(category.equals("Library")) flag=3;
		        
		        String sql4 = "select fine from patron where pat_id in (?)";
		        PreparedStatement stmt4 = con.prepareStatement(sql4);
		        stmt4.setString(1, pat_id);
		        ResultSet resultSet4 = stmt4.executeQuery();	        
		        double fine = 0.0;
		        while(resultSet4.next()) 
		        {
		        	fine = resultSet4.getDouble("fine");
		        }
		        
		        if(fine>0) flag=4;
		        
		        String sql5 = "select return_date,pat_id from book_patron where isbn in (?) and pat_id in (?)";
		        PreparedStatement stmt5 = con.prepareStatement(sql5);
		        stmt5.setString(1, isbn);
		        stmt5.setString(2, pat_id);
		        ResultSet resultSet5 = stmt5.executeQuery();	        
		        String return_date = null;
		        String pat_id_queried=null;
		        while(resultSet5.next()) 
		        {
		        	return_date = resultSet5.getString("return_date");
		        	pat_id_queried = resultSet5.getString("pat_id");
		        }
		        
		        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDateTime now = LocalDateTime.now();
		      
		        if(dtf.format(now).equals(return_date) && pat_id.equals(pat_id_queried)) flag=5;
		        
		        stmt1.close();
		        stmt2.close();
		        stmt3.close();
		        stmt4.close();
		        stmt5.close();
		        
	        }
	        else flag=-1;
	        con.close();
	        stmt6.close();
	        
        		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
		return flag;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
