package model;

import dao_imp.EmployeeDao;
import service.UserServices;
import java.util.Scanner;

public class AdminProfile {
	EmployeeDao obj = new EmployeeDao();
	Scanner sc = new Scanner(System.in);
	
	public void add(){
		System.out.println("Enter Following Detais to Add");
		System.out.println("*****************************");
		System.out.println("First Name:");
		String fname = sc.nextLine();
		System.out.println("Last Name:");
		String lname = sc.nextLine();
		System.out.println("City:");
		String city = sc.nextLine();
		System.out.println("Age:");
		int age = sc.nextInt();
		System.out.println("Contact Number:");
		Double contact = sc.nextDouble();
		sc.nextLine();
		System.out.println("Role:");
		String role = sc.nextLine();
		
		obj.addEmployee(fname, lname, city, age, contact, role);
<<<<<<< HEAD

		System.out.println("Your Reacord has addedd successfully!");

=======
		System.out.println("Your Record has addedd successfully!");
>>>>>>> c8bd184f4239e738e6ec6b878ff932a0e612aecc
	}
	public void remove(){
		System.out.println("Search name of Employee to be deleted");
		String name = sc.nextLine();
		obj.search(name);
		System.out.println("Enter id of employee to delete");
		int id = sc.nextInt();
<<<<<<< HEAD
		obj.removeEmployee(id);		

=======
		int returnid=obj.removeEmployee(id);		
		if(returnid!=0){System.out.println("Your Record has Removed successfully!");}
>>>>>>> c8bd184f4239e738e6ec6b878ff932a0e612aecc
	}
	public void viewEmp(){
		obj.viewEmployee();
	}
	public void acc(){
		obj.getAllAccDetails();
	}
	public void loan(){
		obj.checkAllLoanStatus();
	}
	public void logout(){
		System.out.println("Logged Out..!!");
		System.out.println("------------------------------------");	
		UserServices obj = new UserServices(null);
    	obj.main(null);
	}
}