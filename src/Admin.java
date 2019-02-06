import java.sql.*;
import java.util.*;
public class Admin {
	public static void main(String args[])
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.1.140:3306/bank_db?user=root&password=root");
			//
			Statement st=con.createStatement();
			String query="Select * from Employee";
			
			
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next())
			{
				System.out.println("ID "+rs.getInt(1));
				System.out.println("Name "+rs.getString(2));
				System.out.println("City "+rs.getString(3));
				System.out.println("Age "+rs.getInt(4));
				System.out.println("Contact "+rs.getInt(5));
			}
			
			
		}
		catch(Exception e)
		{
			
		}
	}
	public void connect(){
		
	}
	public void add_employee(){
		
	}
	public void remove_employee(){
		
	}
	public void view_account_details(){
		
	}
	public void view_loan_status(){
		
	}

}
