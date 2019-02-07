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
	
	public int Edit_Employee(String name,String city,int age,int contact,String role)
	{
		try{
			connect_db();
			String sql = "UPDATE employee SET `emp_name` = ?, `emp_city` =?,`emp_age` =?,`emp_contact` =?,`emp_role` =? WHERE (`emp_name` = ?)";
			 
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, city);
			ps.setInt(3, age);
			ps.setInt(4, contact);
			ps.setString(5, role);
			ps.setString(6, name);
			
			
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
			    return rowsUpdated;
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
	}
	public void View_Employee()
	{
		try{
			connect_db();
			String selectSQL = "SELECT emp_name, emp_age, emp_city, emp_contact,emp_role FROM employee";
			PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery(selectSQL );
			while (rs.next()) {
				System.out.println("Employee Name:: "+rs.getString(1));
				System.out.println("Employee Age:: "+rs.getInt(2));
				System.out.println("Employee City:: "+rs.getString(3));
				System.out.println("Employee Contact:: "+rs.getInt(4));
				System.out.println("Employee Role:: "+rs.getString(5));
			}
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	
	public int Remove_Employee(String name)
	{
		connect_db();
		try
		{
			String sql = "DELETE FROM employee WHERE emp_name=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			 
			int rowsDeleted = ps.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			    return rowsDeleted;
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
		
	}

	

}
