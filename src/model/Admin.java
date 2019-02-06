package model;
import dao_imp.*;
public class Admin {
	
	public static void main(String args[])
	{
		EmployeeDao obj =new EmployeeDao();
		obj.Add_Employee("Mehul","Ahmedabad",22,123456789,"Admin");
	}

}
