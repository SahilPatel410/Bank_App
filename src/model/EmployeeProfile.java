package model;
import java.util.*;
import dao_imp.*;
public class EmployeeProfile {
	Scanner sc = new Scanner(System.in);
	int choice=0;
	int id=0;
	
	EmployeeProfile(int uid)
	{
		do{
		this.id=uid;
		System.out.println("------------------------------------");	
		System.out.println("Enter 1 to Edit Your Profile");
		System.out.println("Enter 2 for Logout");
		System.out.println("------------------------------------");	
		choice = sc.nextInt();
		sc.nextLine();
		switch(choice){
		case 1:
			editProfile();
			break;
		case 2:
			logout();
			break;
		default:
			System.out.println("Enter right choice");
			break;
				
		}
		}while(choice!=2);
	}
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
		int contact = sc.nextInt();
		int ans=obj.Edit_Employee(fname,lname,city,age,contact,id);
		
	}
	
	public void logout(){
		System.out.println("Logged Out..!!");
		System.out.println("------------------------------------");	
		Credentials obj = new Credentials();
    	obj.main(null);
	}
}
