package dao_imp;
import dao.IEmployeeDao;
import java.sql.*;
import java.util.*;

public class EmployeeDao implements IEmployeeDao {
	
	
	Connection con=null;
	public void connect_db()
	{
		try
		{
			 con = DriverManager.getConnection("jdbc:mysql://192.168.1.140:3306/bank_db?user=root&password=root");
		}
		catch(Exception e)
		{
			System.out.print(e);
			
		}
		
	}
	
	public void Add_Employee(String name,String city,int age,int contact,String role)
	{
		try
		{
			connect_db();
			PreparedStatement ps=con.prepareStatement("insert into employee(emp_name,emp_city,emp_age,emp_contact,emp_role) values(?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,city);
			ps.setInt(3,age);
			ps.setInt(4,contact);
			ps.setString(5,role);
			
			ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.print(e);
			
		}
		
		
	}

}
