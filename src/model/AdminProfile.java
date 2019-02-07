package model;
import dao_imp.*;
import java.util.*;
public class AdminProfile {
	EmployeeDao obj = new EmployeeDao();
	int choice;
	Scanner sc = new Scanner(System.in);
	AdminProfile(){
		do{
	System.out.println("------------------------------------");	
	System.out.println("Enter 1 to Add employee");
	System.out.println("Enter 2 to Remove employee");
	System.out.println("Enter 3 to View all employee");
	System.out.println("Enter 4 to  view details of all the accounts");
	System.out.println("Enter 5 to see all loan status");
	System.out.println("Enter 6 for Logout");
	System.out.println("------------------------------------");	
	choice = sc.nextInt();
	sc.nextLine();
	
	switch(choice){
		
	case 1:
		add();
		break;
	case 2:
		remove();
		break;
	case 3:
		viewEmp();
		break;
	case 4:
		Acc();
		break;
	case 5:
		loan();
		break;
	case 6:
		logout();
		break;
	default:
			System.out.println("Enter right Choice");
			break;
	}
		}while(choice!=6);
	
	}
	
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
		int contact = sc.nextInt();
		sc.nextLine();
		System.out.println("Role:");
		String role = sc.nextLine();
		obj.Add_Employee(fname, lname, city, age, contact, role);
	}
	
	public void remove(){
		System.out.println("Search name of Employee to be deleted");
		String name = sc.nextLine();
		obj.search(name);
		System.out.println("Enter id of employee to delete");
		int id = sc.nextInt();
		obj.Remove_Employee(id);		
	}
	
	public void viewEmp(){
		obj.View_Employee();
	}
	public void Acc(){
		obj.Get_All_Acc_Details();
	}
	public void loan(){
		obj.Check_all_loan_status();
	}
	public void logout(){
		System.out.println("Logged Out..!!");
		System.out.println("------------------------------------");	
		Credentials obj = new Credentials();
    	obj.main(null);
	}
}
