package model;

import java.util.Scanner;
import dao_imp.EmployeeDao;
import service.UserServices;

public class EmployeeProfile {
	Scanner sc = new Scanner(System.in);
	int choice=0;
	int id=0;
	public void editProfile(){
		EmployeeDao obj=new EmployeeDao();
		System.out.println("Enter Following Detais to edit");
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
		id=obj.getEmployeeId(fname);
		int ans=obj.editEmployee(fname,lname,city,age,contact,id);
<<<<<<< HEAD

		System.out.println("Your Reacord has edited successfully!");

=======
		System.out.println("Your Record has edited successfully!");
>>>>>>> c8bd184f4239e738e6ec6b878ff932a0e612aecc
	}
	public void logout(){
		System.out.println("Logged Out..!!");
		System.out.println("------------------------------------");	
		UserServices obj = new UserServices(null);
    	obj.main(null);
	}
}
