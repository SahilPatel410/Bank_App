package model;
import model.*;
import java.sql.*;
public class verifyCredentials {
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
		public void Authenticate(String Name, String Pass){
			try{
				connect_db();
				Statement stmt = con.createStatement();
				Statement stmt1 = con.createStatement();
	            ResultSet rs = stmt.executeQuery( "SELECT * FROM admin where (`user_name`='"+Name+"')");
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
			            	AdminProfile obj = new AdminProfile();
			            	
		            	}else{
			            	System.out.println("**Welcome Employee**");
			            	EmployeeProfile obj = new EmployeeProfile(U_id);
		            	}	
		            }else
		            {
		            	System.out.println("Invalid username or password!");
		            	Credentials obj = new Credentials();
		            	obj.main(null);
		            }
			}catch(Exception e){
				System.out.print(e);
			}
			
		}
}
