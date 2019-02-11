package service;

import java.util.Scanner;
import dao_imp.EmployeeDao;
import model.AdminProfile;
import model.EmployeeProfile;
import model.LoanDetails;
import service.UserServices;

public class Loan {
	EmployeeDao objEmp = new EmployeeDao();
	LoanDetails objLoan = new LoanDetails();
	public Loan(){
		String choice=null;
		Scanner sc=new Scanner(System.in);
		do{
			System.out.println("------------------------------------");	
			System.out.println("Enter 1 to Give Loan");
			System.out.println("Enter 2 for Check Loan Status");
			System.out.println("Enter 3 for Edit Loan Status");
			System.out.println("Enter 4 for Back to main Menu");
			System.out.println("------------------------------------");	
			choice = sc.nextLine();
			switch(choice){
			case "1":
				objLoan.grantLoan();
				break;
			case "2":
				objEmp.checkAllLoanStatus();
				break;
			case "3":
				objLoan.editLoanStatus();
				break;
			case "4":
				UserServices obj=new UserServices(3);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		}while(!choice.equals("4"));
	}
}
