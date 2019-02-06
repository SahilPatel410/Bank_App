import java.sql.*;
public class DB_Connect {
	public static void main(String args[])
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db?user=root&password=root");
		}
		catch(Exception e)
		{
			
		}
	}
}
