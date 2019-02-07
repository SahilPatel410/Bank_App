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
	
	public void Add_Employee(String fname,String lname,String city,int age,int contact,String role)
	{
		try
		{
			connect_db();
			PreparedStatement ps=con.prepareStatement("insert into employee(emp_fname,emp_lname,emp_city,emp_age,emp_contact,emp_role) values(?,?,?,?,?,?)");
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3,city);
			ps.setInt(4,age);
			ps.setInt(5,contact);
			ps.setString(6,role);
			
			ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.print(e);
			
		}
	}
	public void search(String fname)
	{
		try{
			connect_db();
			String query = "SELECT emp_id,emp_fname,emp_lname,emp_city,emp_age,emp_contact,emp_role from employee where emp_fname = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fname);
			//ps.setString(2, lname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Employee ID:: "+rs.getInt(1));
				System.out.println("Employee First Name:: "+rs.getString(2));
				System.out.println("Employee Last Name:: "+rs.getString(3));
				System.out.println("Employee City:: "+rs.getString(4));
				System.out.println("Employee Age:: "+rs.getInt(5));
				System.out.println("Employee Contact:: "+rs.getInt(6));
				System.out.println("Employee Role:: "+rs.getString(7));
			//	System.out.println("Employee Role:: "+rs.getString(8));
			}
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	public int Edit_Employee(String fname,String lname, String city,int age,int contact,int id)
	{
		try{
			connect_db();
			String sql = "UPDATE employee SET `emp_fname` = ?,`emp_lname` = ?, `emp_city` =?,`emp_age` =?,`emp_contact` =? WHERE (`emp_id` = ?)";
			 
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, city);
			ps.setInt(4, age);
			ps.setInt(5, contact);
			ps.setInt(6, id);
				
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
			String selectSQL = "SELECT emp_fname,emp_lname, emp_age, emp_city, emp_contact,emp_role FROM employee";
			PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery(selectSQL );
			while (rs.next()) {
				System.out.println("Employee First Name:: "+rs.getString(1));
				System.out.println("Employee Last Name:: "+rs.getString(2));
				System.out.println("Employee Age:: "+rs.getInt(3));
				System.out.println("Employee City:: "+rs.getString(4));
				System.out.println("Employee Contact:: "+rs.getInt(5));
				System.out.println("Employee Role:: "+rs.getString(6));
			}
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	
	public int Remove_Employee(int id)
	{
		connect_db();
		try
		{
			String sql = "DELETE FROM employee WHERE emp_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int rowsDeleted = ps.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("An Employee is deleted successfully!");
			    return rowsDeleted;
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
		
	}

	public void Add_New_Account(int acc_no,String acc_type,String acc_holder_name,int acc_bal)
	{
		try
		{
			connect_db();
			PreparedStatement ps=con.prepareStatement("insert into account(Acc_no,Acc_type,Acc_holder_name,Acc_balance) values(?,?,?,?)");
			ps.setInt(1,acc_no);
			ps.setString(2,acc_type);
			ps.setString(3,acc_holder_name);
			ps.setInt(4,acc_bal);
			
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.print(e);
			
		}
	}
	public int Deposite(int amt, int acc_no)
	{
		try{
			connect_db();
			con.setAutoCommit(false);
			int curr_bal=0;
			String selectbal = "SELECT Acc_balance FROM account where Acc_no = ?";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ps.setInt(1, acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				curr_bal=rs.getInt(1);
			}
			amt=curr_bal+amt;
			
			String sql = "UPDATE account SET Acc_balance = ? WHERE Acc_no = ?";
			 
			PreparedStatement ps1 = con.prepareStatement(sql);
			ps1.setInt(1, amt);
			ps1.setInt(2, acc_no);
			
			
			int rowsUpdated = ps1.executeUpdate();
			con.commit();
			if (rowsUpdated > 0) {
			    return amt;
			}
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
	}
	public int Withdraw(int amt, int acc_no)
	{
		try{
			connect_db();
			con.setAutoCommit(false);
			int curr_bal=0;
			String selectbal = "SELECT Acc_balance FROM account where Acc_no = ?";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ps.setInt(1, acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				curr_bal=rs.getInt(1);
			}
			amt=curr_bal-amt;
			String sql = "UPDATE account SET Acc_balance = ? WHERE Acc_no = ?";
			PreparedStatement ps1 = con.prepareStatement(sql);
			ps1.setInt(1, amt);
			ps1.setInt(2, acc_no);
			
			
			int rowsUpdated = ps1.executeUpdate();
			con.commit();
			if (rowsUpdated > 0) {
			    return amt;
			}
			
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
	}
	public void Check_all_loan_status()
	{
		try{
			connect_db();
			String selectbal = "SELECT id,acc_no,loan_amt,amt_of_installment,loan_type,loan_status FROM bank_db.loan";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Loan ID:: "+rs.getInt(1));
				System.out.println("Account Number:: "+rs.getInt(2));
				System.out.println("Loan Amount:: "+rs.getInt(3));
				System.out.println("Amount of Installment:: "+rs.getInt(4));
				System.out.println("Loan Type:: "+rs.getString(5));
				System.out.println("Loan Status:: "+rs.getString(6));
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	public void Get_All_Acc_Details()
	{
		try{
			connect_db();
			String selectbal = "SELECT id,Acc_no,Acc_type,Acc_holder_name,Acc_balance FROM account";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Account ID:: "+rs.getInt(1));
				System.out.println("Account Number:: "+rs.getInt(2));
				System.out.println("Account Type:: "+rs.getString(3));
				System.out.println("Account Holder Name:: "+rs.getString(4));
				System.out.println("Balance:: "+rs.getInt(5));
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	public int Check_bal(int acc_no)
	{
		try{
			connect_db();
			String selectbal = "SELECT Acc_balance FROM account where Acc_no = ?";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ps.setInt(1, acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
	}
	public void Get_Acc_Details(int acc_no)
	{
		try{
			connect_db();
			String selectbal = "SELECT id,Acc_no,Acc_type,Acc_holder_name,Acc_balance FROM account where Acc_no="+acc_no;
			PreparedStatement ps = con.prepareStatement(selectbal);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Account ID:: "+rs.getInt(1));
				System.out.println("Account Number:: "+rs.getInt(2));
				System.out.println("Account Type:: "+rs.getString(3));
				System.out.println("Account Holder Name:: "+rs.getString(4));
				System.out.println("Balance:: "+rs.getInt(5));
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	
	public int Transfer(int amt, int acc_no)
	{
		try
		{
			connect_db();
			con.setAutoCommit(false);
			int curr_bal=0;
			String selectbal = "SELECT Acc_balance FROM account where Acc_no = ?";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ps.setInt(1, acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				curr_bal=rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return 0;
	}

	
	
	

}
