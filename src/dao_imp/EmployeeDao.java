package dao_imp;

import dao.IEmployeeDao;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.DriverManager;
import java.util.*;
import service.UserServices;

public class EmployeeDao implements IEmployeeDao {
	UserServices obj;
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
	
	public void authenticate(String Name, String Pass){
		try{
			connect_db();
			Statement stmt = con.createStatement();
			Statement stmt1 = con.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM credentials where (`user_name`='"+Name+"')");
            int U_id=0;
            String Uname=null;
            String Upass=null;
            String Role = null;
            while(rs.next()){
            	Uname = rs.getString(2);
            	Upass = rs.getString(3);
            	U_id = rs.getInt(4);
            }
            if((Name.equals(Uname)) && (Pass.equals(Upass))){
	            	System.out.println("You are logged in!!");
	            	ResultSet rs1 = stmt1.executeQuery( "SELECT * FROM employee WHERE `emp_id`="+U_id);
	            	while(rs1.next()){
	            	Role = rs1.getString(7);
	            	}
	            	if(Role.equals("Admin")){
		            	System.out.println("***Welcome Admin***");
		            	obj = new UserServices();
		            	
	            	}else{
		            	System.out.println("**Welcome Employee**");
		            	obj = new UserServices(U_id);
	            	}	
	            }else
	            {
	            	System.out.println("Invalid username or password!");
	            	obj = new UserServices(null);
	            	obj.main(null);
	            }
		}catch(Exception e){
			System.out.print("Authentication Failed");
		}
		
	}
	public void addAdmin(String UserName,String UserPass){
		try
		{
			connect_db();
			int empId=getEmployeeId(UserName);
			PreparedStatement ps=con.prepareStatement("insert into credentials(user_name,user_password,emp_id) values(?,?,?)");
				
			ps.setString(1,UserName);
			ps.setString(2,UserPass);
			ps.setInt(3,empId);
			ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.print("Unable to add User");
			
		}
	}
	public void addEmployee(String fname,String lname,String city,int age,double contact,String role){
		try
		{
			connect_db();
			PreparedStatement ps=con.prepareStatement("insert into employee(emp_fname,emp_lname,emp_city,emp_age,emp_contact,emp_role) values(?,?,?,?,?,?)");
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3,city);
			ps.setInt(4,age);
			ps.setDouble(5,contact);
			ps.setString(6,role);
			
			ps.executeUpdate();
			addAdmin(fname,""+fname+"123");
			
		}
		catch(Exception e)
		{
			System.out.print("Unable to add Employee");
			
		}
	}
	public void search(String fname){
		try{
			connect_db();
			String query = "SELECT emp_id,emp_fname,emp_lname,emp_city,emp_age,emp_contact,emp_role from employee where (`emp_fname`=?)";
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
				System.out.println("Employee Contact:: "+rs.getDouble(6));
				System.out.println("Employee Role:: "+rs.getString(7));
				System.out.println("--------------------------------");
				
			}
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.print("Unable to Search Employee");
		}
	}
	public int getEmployeeId(String fname){
		try{
			connect_db();
			String query = "SELECT emp_id from employee where (`emp_fname`=?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
			
		}
		catch(Exception e)
		{
			System.out.print("Error in loading id");
		}
		return 0;
	}
	public int editEmployee(String fname,String lname, String city,int age,double contact,int id){
		try{
			connect_db();
			String sql = "UPDATE employee SET `emp_fname` = ?,`emp_lname` = ?, `emp_city` =?,`emp_age` =?,`emp_contact` =? WHERE (`emp_id` = ?)";
			 
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, city);
			ps.setInt(4, age);
			ps.setDouble(5, contact);
			ps.setInt(6, id);
			
			
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
			    return rowsUpdated;
			}
		}
		catch(Exception e)
		{
			System.out.print("Unable to edit Employee");
		}
		return 0;
	}
	public void viewEmployee(){
		try{
			connect_db();
			String selectSQL = "SELECT emp_fname,emp_lname, emp_age, emp_city, emp_contact,emp_role FROM employee";
			PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery(selectSQL );
			while (rs.next()) {
				System.out.println("Employee Name:: "+rs.getString(1));
				System.out.println("Employee Name:: "+rs.getString(2));
				System.out.println("Employee Age:: "+rs.getInt(3));
				System.out.println("Employee City:: "+rs.getString(4));
				System.out.println("Employee Contact:: "+rs.getDouble(5));
				System.out.println("Employee Role:: "+rs.getString(6));
				System.out.println("--------------------------------");
			}
			System.out.println();
			
		}
		catch(Exception e)
		{
			System.out.println("Unable to view Employees");
		}
	}
	
	public int removeEmployee(int id){
		
		try
		{
			connect_db();
			String sql1 = "DELETE FROM credentials WHERE emp_id=?";
			String sql2 = "DELETE from employee WHERE emp_id=?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps1.setInt(1, id);
			ps2.setInt(1, id);
			int rowsDeleted1 = ps1.executeUpdate();
			int rowsDeleted2 = ps2.executeUpdate();
			if (rowsDeleted2 > 0) {
			    return rowsDeleted2;
			}
		}
		catch(Exception e)
		{
			System.out.println("Unable to Remove Employee");
		}
		return 0;
		
	}

	public void addNewAccount(int acc_no,String acc_type,String acc_holder_name,int acc_bal){
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
			System.out.println("Unable to add Account");
			
		}
	}
	public int deposite(int amt, int acc_no){
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
			System.out.println("Unable to Deposite Account");
		}
		return 0;
	}
	public void editAccountDetails(int acc_no,String accName){
		try{
			connect_db();
			String selectbal = "UPDATE account SET Acc_holder_name = ? WHERE Acc_no = ?";
			PreparedStatement ps = con.prepareStatement(selectbal);
			ps.setString(1,accName);
			ps.setInt(2, acc_no);
			int rowsUpdated= ps.executeUpdate();
			if(rowsUpdated>0){System.out.println("Account Updated Successfully.!!");}
		}
		catch(Exception e)
		{
			System.out.println("Unable for get Account Details");
		}
	}
	public int withdraw(int amt, int acc_no){
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
			if((curr_bal-amt)<2000){
				System.out.println("Your Current balance is Insufficient!");
				return curr_bal;
			}else{
				amt=curr_bal-amt;
			}
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
			System.out.println("Unable to withdraw from Account");
		}
		return 0;
	}
	public int checkBal(int acc_no){
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
			System.out.println("Unable to check Balance");
		}
		return 0;
	}
	public void getAccDetails(int acc_no){
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
				System.out.println("--------------------------------");
			}
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println("Unable for get Account Details");
		}
	}
	public void getAllAccDetails(){
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
				System.out.println("--------------------------------");
			}
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println("Unable for get Account Details");
		}
	}
	public Boolean transfer(int amt, int acc_no_from,int acc_no_to){
		Savepoint s1=null;
		Savepoint s2=null;
		try
		{
			connect_db();
			con.setAutoCommit(false);
			int curr_bal=checkBal(acc_no_from);
			
			if(curr_bal < 2000)
			{
				System.out.println("Insufficient Balance.Your Balance is "+curr_bal);
				return false;
			}
			else
			{
				int amt_after_withdraw=withdraw(amt,acc_no_from);
				if(amt_after_withdraw==curr_bal){
					return false;
				}
				s1=con.setSavepoint("After Deduction");
				con.commit();
				int amt_after_deposite=deposite(amt,acc_no_to);
				s2=con.setSavepoint("After Amount addition");
				con.commit();
				return true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Unable to Transfer");
			if(s1== null)
			{
				try {
					con.rollback(s1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false;
	}
	public void checkAllLoanStatus(){
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
				System.out.println("--------------------------------");
			}
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.print("Unable to check loan status");
		}
	} 		
	public void giveLoan(int acc_no,int loan_amt,int amt_of_installment,String loan_type,String loan_status) {
		try{
			connect_db();
			String add_loan = "insert into loan(acc_no,loan_amt,amt_of_installment,loan_type,loan_status) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(add_loan);
			ps.setInt(1, acc_no);
			ps.setInt(2, loan_amt);
			ps.setInt(3, amt_of_installment);
			ps.setString(4, loan_type);
			ps.setString(5,loan_status);
			ps.executeUpdate();
		} 
		catch(Exception e)
		{
			System.out.print("Unable to give loan");
		}
	}
	public boolean editLoanStatus(String loanStatus,int loanId)
	{
		try{
			connect_db();
			String editLoanStatus = "UPDATE loan SET `loan_status` = ? WHERE (`id` = ?)";
			PreparedStatement ps = con.prepareStatement(editLoanStatus);
			ps.setString(1, loanStatus);
			ps.setInt(2, loanId);
			int noRows=ps.executeUpdate();
			if(noRows>0)
			{
				return true;
			}
			return false;
		} 
		catch(Exception e)
		{
			System.out.print("Unable to Change the Status");
			return false;
		}
	}

}
