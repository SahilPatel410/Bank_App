package service;

import java.util.Scanner;

import dao_imp.EmployeeDao;
import model.AdminProfile;
import model.EmployeeProfile;

public class UserServices {
	AdminProfile objAdmin = new AdminProfile();
	EmployeeProfile objEmp = new EmployeeProfile();
	Scanner sc = new Scanner(System.in);
	public UserServices(){
		String choice;
		do{
			System.out.println("------------------------------------");	
			System.out.println("Enter 1 to Add employee");
			System.out.println("Enter 2 to Remove employee");
			System.out.println("Enter 3 to View all employee");
			System.out.println("Enter 4 to view details of all the accounts");
			System.out.println("Enter 5 to see all loan status");
			System.out.println("Enter 6 for Logout");
			System.out.println("------------------------------------");	
			choice = sc.next();
			sc.nextLine();
			switch(choice){
				case "1":
					objAdmin.add();
					break;
				case "2":
					objAdmin.remove();
					break;
				case "3":
					objAdmin.viewEmp();
					break;
				case "4":
					objAdmin.acc();
					break;
				case "5":
					objAdmin.loan();
					break;
				case "6":
					objAdmin.logout();
					break;
				default:
					System.out.println("Enter right Choice");
					break;
			}
		}while(!choice.equals("6"));	
	}
	public UserServices(int uid){
		String choice=null;
		do{
			System.out.println("------------------------------------");	
			System.out.println("Enter 1 to Edit Your Profile");
			System.out.println("Enter 2 for Account Operations");
			System.out.println("Enter 3 for Loan Operations");
			System.out.println("Enter 4 for Logout");
			System.out.println("------------------------------------");	
			choice = sc.nextLine();
			switch(choice){
			case "1":
				objEmp.editProfile();
				break;
			case "2":
				Account objAcc = new Account();
				break;
			case "3":
				Loan objLoan = new Loan();
				break;
			case "4":
				objEmp.logout();
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		}while(!choice.equals("4"));
	}
	public UserServices(String s){
		
	}
	public static void main(String args[]){
		String user_in;
		String pass;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Credentials for Login!");
		System.out.println("Enter Username");
		user_in = sc.nextLine();
		System.out.println("Enter Password");
		pass = sc.nextLine();
		EmployeeDao ob = new EmployeeDao();
		ob.authenticate(user_in, pass);
		sc.close();
	}
}
